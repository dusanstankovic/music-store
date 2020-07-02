package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {

        this.artistService = artistService;
    }

    @GetMapping("/list")
    public String listArtists(Model model) {

        model.addAttribute("artists", artistService.findAll());

        return "list-artists";
    }
}
