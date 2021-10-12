package com.example.mp3player.service.dto;

import com.example.mp3player.entity.Upload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class UploadRequestDTO {

    private Long userId;

    private String singer;

    private String genre;

 //   private String release;

    private String customTitle;

    @Setter
    private String originalMp3Path;

    @Setter
    private String originalFileName;

    @Builder
    public UploadRequestDTO(Long userId, String singer, String genre, String release, String customTitle, String originalMp3Path, String originalFileName) {
        this.userId = userId;
        this.singer = singer;
        this.genre = genre;
    //    this.release = release;
        this.customTitle = customTitle;
        this.originalMp3Path = originalMp3Path;
        this.originalFileName = originalFileName;
    }


    public Upload toEnity() {
        return Upload.builder()
                .userId(userId)
                .genre(genre)
                .singer(singer)
       //         .release(release)
                .customTitle(customTitle)
                .originalMp3Path(originalMp3Path)
                .originalFileName(originalFileName)
                .build();
    }

}

