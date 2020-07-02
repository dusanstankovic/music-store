package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {

    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {

        this.playlistService = playlistService;
    }

    @GetMapping("/list")
    public String listPlaylists(Model model) {

        model.addAttribute("playlists", playlistService.findAll());

        return "list-playlists";
    }
}
