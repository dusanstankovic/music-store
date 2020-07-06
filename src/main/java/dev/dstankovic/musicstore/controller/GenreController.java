package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Genre;
import dev.dstankovic.musicstore.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        return "genres/list-genres";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Genre genre = new Genre();
        model.addAttribute("genre", genre);

        return "genres/genre-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("genreId") int id, Model model) {

        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);

        return "genres/genre-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "genres/genre-form";
        } else {
            genreService.save(genre);
        }

        return "redirect:/genres/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("genreId") int id) {

        genreService.deleteById(id);

        return "redirect:/genres/list";
    }
}
