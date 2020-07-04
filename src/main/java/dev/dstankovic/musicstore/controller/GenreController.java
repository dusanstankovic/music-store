package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Genre;
import dev.dstankovic.musicstore.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/addGenre")
    public String addGenre(Model model) {

        Genre genre = new Genre();
        model.addAttribute("genre", genre);

        return "/genres/genre-form";
    }

    @PostMapping("/save")
    public String saveGenre(@ModelAttribute("genre") Genre genre) {

        genreService.save(genre);

        return "redirect:/genres/list";
    }

    @GetMapping("/updateGenre")
    public String updateGenre(@RequestParam("genreId") int id, Model model) {

        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);

        return "/genres/genre-form";
    }

    @GetMapping("/delete")
    public String deleteGenre(@RequestParam("genreId") int id) {

        genreService.deleteById(id);

        return "redirect:/genres/list";
    }
}
