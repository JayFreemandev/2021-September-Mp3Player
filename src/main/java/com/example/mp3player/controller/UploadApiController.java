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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.PublicResponseDTO;
import com.example.mp3player.service.dto.UploadRequestDTO;
import com.example.mp3player.service.dto.UploadResponseDTO;
import com.example.mp3player.utill.DeleteUtill;
import com.example.mp3player.utill.PlayUtill;
import com.example.mp3player.utill.UploadUtill;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
public class UploadApiController {

	private final UploadService uploadService;
	private final String DEFAULT_URI = "/api/v1/midi";

	@PostMapping(DEFAULT_URI)
	public UploadUtill uploadMultipleMidi(@RequestParam List<MultipartFile> files,
		@RequestParam List<String> singer,
		@RequestParam List<String> titles,
		@RequestParam List<String> genre,
		HttpServletRequest request) throws Exception {

		return new UploadUtill().result(files, singer, titles, genre, request, uploadService);
	}

	@GetMapping(DEFAULT_URI + "/mp3/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void mp3Play(@PathVariable Long id,
		HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		PlayUtill play = new PlayUtill().play(id, request, response, uploadService);
	}

	@GetMapping(DEFAULT_URI)
	public List<PublicResponseDTO> findAll() {
		return uploadService.findAll();
	}

	@PutMapping(DEFAULT_URI + "/{id}")
	public Long updateMidiInfo(@RequestBody UploadRequestDTO RequestDTO,
		@PathVariable Long id) {
		UploadResponseDTO midi = uploadService.findById(id);
		return uploadService.update(id, RequestDTO);
	}

	@DeleteMapping(DEFAULT_URI + "/{id}")
	public Long deleteMidi(@PathVariable Long id) {
		return new DeleteUtill().delete(id, uploadService);
	}

}
