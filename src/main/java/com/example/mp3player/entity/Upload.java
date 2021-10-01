package com.example.mp3player.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@ToString
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

    @ManyToOne
    private Member nickname;
}