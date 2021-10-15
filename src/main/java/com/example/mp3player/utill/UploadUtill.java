package com.example.mp3player.utill;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.UploadRequestDTO;

import lombok.Data;

@Data
@Component
public class UploadUtill {

	private List<Map<String, String>> successList;
	private List<String> failedList;
	private String status;

	public UploadUtill(List<Map<String, String>> successList, List<String> failedList, String status) {
		this.successList = successList;
		this.failedList = failedList;
		this.status = status;
	}

	public UploadUtill() {

	}

	public UploadUtill result(List<MultipartFile> files, List<String> singer, List<String> titles, List<String> genre,
		HttpServletRequest request, UploadService uploadService) {
		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
		String basePath = rootPath + "/" + "app/midi";
		String ourUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		Map<String, String> success = new HashMap<>();
		success.put("successList", null);

		if (files.size() == 0) {
			status = "noFile";
			successList.add(success);
			failedList.add(null);
			return new UploadUtill(successList, failedList, status);
		}

		File mp3Dir = new File(basePath + "/mp3");

		if (!mp3Dir.exists()) {
			mp3Dir.mkdirs();
			System.out.println("mkdirs: mp3");
		}

		List<Map<String, String>> successList = new ArrayList<>();
		List<String> urlList = new ArrayList<>();
		List<String> failedList = new ArrayList<>();
		MultipartFile file = null;

		for (int i = 0; i < files.size(); i++) {
			file = files.get(i);
			String originalName = file.getOriginalFilename();
			String originalExt = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();
			String uuid = UUID.randomUUID().toString();
			String mp3Path = basePath + "/mp3/" + uuid + ".mp3";
			File dest = new File(mp3Path);
			try {
				file.transferTo(dest);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 변환 성공시 데이터베이스에 정보 입력
			if (mp3Path != null) {
				Long id = uploadService.save(UploadRequestDTO.builder()
					.genre(genre.get(i))
					.singer(singer.get(i))
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

		if (successList.size() > 0 && failedList.size() == 0) {
			status = "AllFileSuccess";
		} else if (successList.size() > 0 && failedList.size() > 0) {
			status = "SomeFileSuccess";
		} else if (successList.size() > 0) {
			status = "AllFileFailed";
		}

		return new UploadUtill(successList, failedList, status);
	}
}
