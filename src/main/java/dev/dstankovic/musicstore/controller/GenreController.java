package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private GenreService genreService;

    public GenreController(GenreService genreService) {

        this.genreService = genreService;
    }

    @GetMapping("/list")
    public String listGenres(Model model) {

        model.addAttribute("genres", genreService.findAll());

        return "/genres/list-genres";
    }
}
