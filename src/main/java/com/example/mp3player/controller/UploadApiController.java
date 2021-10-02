package com.example.mp3player.controller;

import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.UploadRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class UploadApiController {

    private final UploadService uploadService;
    private final String DEFAULT_URI = "/api/v1/midi";

    @GetMapping("/midi")
    public String midiHome() {
        return "midi/midi-main";
    }

    @GetMapping("/midi/upload")
    public ModelAndView upload(ModelAndView modelAndView) {
        modelAndView.setViewName("midi/upload");
        return modelAndView;
    }

    @PostMapping(DEFAULT_URI)
    public Map<String, Object> uploadMultipleMidi(@RequestParam("files") List<MultipartFile> files,
                                                  @RequestParam("singer") List<String> singer,
                                                  @RequestParam("titles") List<String> titles,
                                                  HttpServletRequest request) throws Exception {

        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "/" + "app/midi";
        String ourUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"");

        Map<String, Object> result = new HashMap<>();

        if(files.size() == 0) {
            result.put("status", "NoFile");
            result.put("successList", null);
            result.put("failedList", null);

            return result;
        }

        System.out.println(">>> " + rootPath);
        System.out.println(">>> " + basePath);

        File mp3Dir = new File(basePath + "/mp3");

        // 디렉토리 생성
        if(!mp3Dir.exists()) {
            mp3Dir.mkdirs();
            System.out.println("mkdirs: mp3");
        }

        List<Map<String, String>> successList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        List<String> failedList = new ArrayList<>();
        MultipartFile file = null;

        for(int i = 0; i < files.size(); i++) {

            file = files.get(i);

            String originalName = file.getOriginalFilename();
            String originalExt = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();
            String uuid = UUID.randomUUID().toString();
            String mp3Path = basePath + "/mp3/" + uuid + ".mp3";

            File dest = new File(mp3Path);
            file.transferTo(dest);

            // 변환 성공시 데이터베이스에 정보 입력
            if(mp3Path!=null) {
                Long id = uploadService.save(UploadRequestDTO.builder()
                        .genre(singer.get(i))
                        .customTitle(titles.get(i))
                        .originalMp3Path("/mp3/" + uuid + ".mp3")
                        .originalFileName(originalName)
                        .build());
                Map<String, String> urlPair = new HashMap<>();
                urlPair.put("originalName", originalName);
                urlPair.put("url", ourUrl + "/api/v1/midi/mp3/" + id);
                successList.add(urlPair);
            } else {
                failedList.add(originalName);
            }
        }

        if(successList.size() > 0 && failedList.size() == 0) {
            result.put("status", "AllFileSuccess");
        } else if(successList.size() > 0 && failedList.size() > 0) {
            result.put("status", "SomeFileSuccess");
        } else if (successList.size() > 0){
            result.put("status", "AllFileFailed");
        }
        result.put("successList", successList);
        result.put("failedList", failedList);
        return result;
    }
}
