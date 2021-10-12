package com.example.mp3player.utill;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.UploadResponseDTO;

public class DeleteUtill {
	public Long delete(Long id, UploadService uploadService) {
		UploadResponseDTO midi = uploadService.findById(id);
		uploadService.delete(id);

		// 파일 삭제
		// mp3 파일 경로 지정
		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
		String basePath = rootPath + "/" + "app/midi";
		String mp3Path = basePath + midi.getOriginalMp3Path();

		File mp3File = new File(mp3Path);
		File toDeleteDir = new File(basePath + "/to_delete");

		if (!toDeleteDir.exists()) {
			toDeleteDir.mkdirs();
			System.out.println("mkdirs: /to_delete");
		}

		// 파일 to_delete 디렉토리로 이동
		mp3File.renameTo(new File(basePath + "/to_delete/" + mp3File.getName()));

		return id;
	}
}
