package com.example.mp3player.entity;

import com.example.mp3player.service.dto.UploadRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@ToString(exclude = "nickname")
@Getter
@NoArgsConstructor
@Entity
public class Upload extends Base {

    @Id // PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙
    private Long id;

    private String genre;

    private String customTitle;

    private String originalMp3Path;

    private String originalFileName;

    private Long userId;
/*    @ManyToOne(fetch = FetchType.LAZY)
    private Member nickname;*/
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    @Builder
    public Upload(Long userId, String genre, String customTitle,
                String hash, String originalMidiPath, String originalMp3Path,
                String originalFileName) {
        this.userId = userId;
        this.genre = genre;
        this.customTitle = customTitle;
        this.originalMp3Path = originalMp3Path;
        this.originalFileName = originalFileName;
    }

    public void update(UploadRequestDTO dto) {
        // userid는 변경될 수 없음
        this.genre = dto.getGenre() != null ? dto.getGenre() : this.genre;
        this.customTitle = dto.getCustomTitle() != null ? dto.getCustomTitle() : this.customTitle;
        this.originalMp3Path = dto.getOriginalMp3Path() != null ? dto.getOriginalMp3Path() : this.originalMp3Path;
    }
}