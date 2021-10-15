package com.example.mp3player.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mp3player.service.UploadService;
import com.example.mp3player.service.dto.PublicResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Controller
@Log4j2
public class UploadIndexController {

	private final UploadService uploadService;

	@GetMapping("/")
	public String midiHome() {
		return "midi/midi-main";
	}

	@GetMapping("/midi/upload")
	public String upload() {
		return "midi/upload";
	}

	@GetMapping("/midi/update")
	public String updateInfoPage(Model model) {
		List<PublicResponseDTO> list = null;
		list = uploadService.findAll();
		log.info(">>>> " + list);
		model.addAttribute("list", list);
		return "midi/midi-edit";
	}
}
