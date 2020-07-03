package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Album;
import dev.dstankovic.musicstore.entity.Artist;
import dev.dstankovic.musicstore.service.AlbumService;
import dev.dstankovic.musicstore.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

        return "/albums/list-albums";
    }

    @GetMapping("/addAlbum")
    public String addAlbum(Model model) {

        Album album = new Album();
        model.addAttribute("album", album);

        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);

        return "/albums/album-form";
    }

    @PostMapping("/save")
    public String saveAlbum(@ModelAttribute("album") Album album) {

        albumService.save(album);

        return "redirect:/albums/list";
    }

    @GetMapping("/updateAlbum")
    public String updateAlbum(@RequestParam("albumId") int id, Model model) {

        Album album = albumService.findById(id);
        model.addAttribute("album", album);

        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);

        return "/albums/album-form";
    }

    @GetMapping("/delete")
    public String deleteAlbum(@RequestParam("albumId") int id) {

        albumService.deleteById(id);

        return "redirect:/albums/list";
    }

}
