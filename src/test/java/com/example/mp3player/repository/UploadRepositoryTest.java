package com.example.mp3player.repository;

import com.example.mp3player.entity.Member;
import com.example.mp3player.entity.Upload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class UploadRepositoryTest {

    @Autowired
    private UploadRepository uploadRepository;

    @Test
     public void addUpload(){
         IntStream.range(1, 10).forEach(i -> {
             Member member = Member.builder().nickname("USER"+i).build();
             Upload upload = Upload.builder()
                     .nickname(member)
                     .genre("genre"+i)
                     .customTitle("free title"+i)
                     .originalFileName(i+".mp3")
                     .originalMp3Path("C:/upload/")
                     .build();

             uploadRepository.save(upload);
         });
     }

    @Transactional
    @Test
    public void testRead() {
        Optional<Upload> result = uploadRepository.findById(1L);

        Upload upload = result.get();
        System.out.println(upload);
        System.out.println(upload.getNickname());
    }
}
