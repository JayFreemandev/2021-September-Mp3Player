package com.example.mp3player.repository;

import com.example.mp3player.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Long> {
    List<Upload> findByUserId(Long userId);
}
