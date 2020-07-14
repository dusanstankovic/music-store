package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Artist;
import dev.dstankovic.musicstore.report.GenerateArtistsListReport;
import dev.dstankovic.musicstore.service.ArtistService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

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

    @GetMapping("/delete")
    public String delete(@RequestParam("artistId") int id) {

        artistService.deleteById(id);

        return "redirect:/artists/list";
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> artistsReport() {

        List<Artist> artists = artistService.findAll();

        ByteArrayInputStream bis = GenerateArtistsListReport.artistsReport(artists);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=artists_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
