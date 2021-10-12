package com.example.mp3player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class UploadIndexController {

    @GetMapping("/")
    public String midiHome() {
        return "midi/midi-main";
    }

    @GetMapping("/midi/upload")
    public ModelAndView upload(ModelAndView modelAndView) {
        modelAndView.setViewName("midi/upload");
        return modelAndView;
    }


}
