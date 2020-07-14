package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Album;
import dev.dstankovic.musicstore.entity.Artist;
import dev.dstankovic.musicstore.report.GenerateAlbumsListReport;
import dev.dstankovic.musicstore.service.AlbumService;
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
@ControllerAdvice
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService albumService;
    private ArtistService artistService;

    public AlbumController(AlbumService albumService, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/list")
    public String listAlbums(Model model) {

        model.addAttribute("albums", albumService.findAll());

        return "albums/list-albums";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Album album = new Album();
        model.addAttribute("album", album);

        return "albums/album-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("albumId") int id, Model model) {

        Album album = albumService.findById(id);
        model.addAttribute("album", album);

        return "albums/album-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("album") Album album, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "albums/album-form";
        }

        albumService.save(album);

        return "redirect:/albums/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("albumId") int id) {

        albumService.deleteById(id);

        return "redirect:/albums/list";
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeesReport() {

        List<Album> albums = albumService.findAll();

        ByteArrayInputStream bis = GenerateAlbumsListReport.albumsReport(albums);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=albums_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @ModelAttribute
    public void addArtistsForDropdownList(Model model) {

        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
    }
}
