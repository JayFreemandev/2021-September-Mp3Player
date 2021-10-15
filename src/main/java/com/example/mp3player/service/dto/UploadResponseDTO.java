package com.example.mp3player.service.dto;

import com.example.mp3player.entity.Upload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class UploadResponseDTO {

    private Long id;

    private Long userId;    // 업로드한 사람

    private String singer;

    private String genre;

   // private String release;

    private String customTitle;


    private String originalMp3Path;

    private String originalFileName;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public UploadResponseDTO(Upload entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.singer = entity.getSinger();
        this.genre = entity.getGenre();
     //   this.release = entity.getRelease();
        this.customTitle = entity.getCustomTitle();
        this.originalMp3Path = entity.getOriginalMp3Path();
        this.originalFileName = entity.getOriginalFileName();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}