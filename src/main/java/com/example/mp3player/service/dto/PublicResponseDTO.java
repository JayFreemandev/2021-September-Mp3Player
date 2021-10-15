package com.example.mp3player.service.dto;

import com.example.mp3player.entity.Upload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class PublicResponseDTO {

    private Long id;

    private Long userId;    // 업로드한 사람

    private String singer;

    private String genre;

  //  private String release;

    private String customTitle;

    private String originalFileName;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public PublicResponseDTO(Upload upload) {
        this.id = upload.getId();
        this.userId = upload.getUserId();
        this.singer = upload.getSinger();
        this.genre = upload.getGenre();
  //      this.release = upload.getRelease();
        this.customTitle = upload.getCustomTitle();
        this.originalFileName = upload.getOriginalFileName();
        this.createdDate = upload.getCreatedDate();
        this.modifiedDate = upload.getModifiedDate();
    }
}
