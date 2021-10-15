package com.example.mp3player.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mp3player.service.S3Service;
import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.PublicResponseDTO;
import com.example.mp3player.service.dto.UploadRequestDTO;
import com.example.mp3player.service.dto.UploadResponseDTO;
import com.example.mp3player.utill.DeleteUtill;
import com.example.mp3player.utill.UploadUtill;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/midi")
public class UploadApiController {
	private UploadService uploadService;
	private UploadUtill uploadUtill;

	@PostMapping()
	public UploadUtill uploadMultipleMidi(@RequestParam List<MultipartFile> files,
		@RequestParam List<String> singer,
		@RequestParam List<String> titles,
		@RequestParam List<String> genre,
		HttpServletRequest request) throws Exception {

		return uploadUtill.result(files, singer, titles, genre, request);
	}

	@GetMapping("/mp3/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String mp3Play(@PathVariable Long id,
		HttpServletRequest request,
		HttpServletResponse response, Object play) throws IOException {
		UploadResponseDTO midi = uploadService.findById(id);
		return midi.getOriginalMp3Path();
	}

	@GetMapping()
	public List<PublicResponseDTO> findAll() {
		return uploadService.findAll();
	}

	@PutMapping("/{id}")
	public Long updateMidiInfo(@RequestBody UploadRequestDTO RequestDTO,
		@PathVariable Long id) {
		UploadResponseDTO midi = uploadService.findById(id);
		return uploadService.update(id, RequestDTO);
	}

	@DeleteMapping("/{id}")
	public Long deleteMidi(@PathVariable Long id) {
		UploadResponseDTO midi = uploadService.findById(id);
		uploadService.delete(id);
		//파일 delete folder로 이동
		new DeleteUtill().delete(midi);
		return id;
	}

}
