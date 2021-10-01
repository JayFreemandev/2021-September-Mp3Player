package com.example.mp3player.repository;

import com.example.mp3player.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}
