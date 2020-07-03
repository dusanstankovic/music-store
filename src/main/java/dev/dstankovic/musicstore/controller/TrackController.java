package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tracks")
public class TrackController {

    private TrackService trackService;

    public TrackController(TrackService trackService) {

        this.trackService = trackService;
    }

    @GetMapping("/list")
    public String listTracks(Model model) {

        model.addAttribute("tracks", trackService.findAll());

        return "/tracks/list-tracks";
    }
}
