package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Album;
import dev.dstankovic.musicstore.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {

        this.albumService = albumService;
    }

    @GetMapping("list")
    public String listAlbums(Model model) {

        model.addAttribute("albums", albumService.findAll());

        return "albums/list-albums";
    }

    @GetMapping("addAlbum")
    public String addAlbum(Model model) {

        Album album = new Album();
        model.addAttribute("album", album);

        return "albums/album-form";
    }
}
