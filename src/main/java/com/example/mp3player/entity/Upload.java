package com.example.mp3player.entity;


import com.example.mp3player.service.dto.UploadRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Upload extends Base{

    @Id // PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙
    private Long id;

    private Long userId;    // 업로드한 사람

    private String singer;

    private String genre;

   // private String release;

    private String customTitle;

    private String originalMp3Path;

    private String originalFileName;

    @Builder
    public Upload(Long id, Long userId, String singer, String genre, String release,  String customTitle, String originalMp3Path, String originalFileName) {
        this.id = id;
        this.userId = userId;
        this.singer = singer;
        this.genre = genre;
     //   this.release = release;
        this.customTitle = customTitle;
        this.originalMp3Path = originalMp3Path;
        this.originalFileName = originalFileName;
    }

    public void update(UploadRequestDTO dto) {
        // userid는 변경될 수 없음
        this.genre = dto.getGenre() != null ? dto.getGenre() : this.genre;
        this.singer = dto.getSinger() != null ? dto.getSinger() : this.singer;
        this.customTitle = dto.getCustomTitle() != null ? dto.getCustomTitle() : this.customTitle;
        this.originalMp3Path = dto.getOriginalMp3Path() != null ? dto.getOriginalMp3Path() : this.originalMp3Path;
    }
}
