package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Artist;
import dev.dstankovic.musicstore.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        return "artists/list-artists";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Artist artist = new Artist();
        model.addAttribute("artist", artist);

        return "artists/artist-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("artistId") int id, Model model) {

        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);

        return "artists/artist-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "artists/artist-form";
        } else {
            artistService.save(artist);
        }

        return "redirect:/artists/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("artistId") int id) {

        artistService.deleteById(id);

        return "redirect:/artists/list";
    }
}
