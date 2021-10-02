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

    private String genre;

    private String customTitle;

    @Setter
    private String originalMp3Path;

    @Setter
    private String originalFileName;

    @Builder
    public UploadRequestDTO(Long userId, String genre, String customTitle,
                          String originalMp3Path,
                          String originalFileName) {
        this.userId = userId;
        this.genre = genre;
        this.customTitle = customTitle;
        this.originalMp3Path = originalMp3Path;
        this.originalFileName = originalFileName;
    }

    public Upload toEnity() {
        return Upload.builder()
                .id(userId)
                .genre(genre)
                .customTitle(customTitle)
                .originalMp3Path(originalMp3Path)
                .originalFileName(originalFileName)
                .build();
    }

}
