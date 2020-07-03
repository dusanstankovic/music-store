package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Album;
import dev.dstankovic.musicstore.entity.Genre;
import dev.dstankovic.musicstore.entity.MediaType;
import dev.dstankovic.musicstore.entity.Track;
import dev.dstankovic.musicstore.service.AlbumService;
import dev.dstankovic.musicstore.service.GenreService;
import dev.dstankovic.musicstore.service.MediaTypeService;
import dev.dstankovic.musicstore.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tracks")
public class TrackController {

    private TrackService trackService;
    private GenreService genreService;
    private MediaTypeService mediaTypeService;
    private AlbumService albumService;

    public TrackController(TrackService trackService, GenreService genreService, MediaTypeService mediaTypeService, AlbumService albumService) {
        this.trackService = trackService;
        this.genreService = genreService;
        this.mediaTypeService = mediaTypeService;
        this.albumService = albumService;
    }

    @GetMapping("/list")
    public String listTracks(Model model) {

        model.addAttribute("tracks", trackService.findAll());

        return "/tracks/list-tracks";
    }

    @GetMapping("/addTrack")
    public String addTrack(Model model) {

        Track track = new Track();
        model.addAttribute("track", track);

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        List<MediaType> mediaTypes = mediaTypeService.findAll();
        model.addAttribute("mediatypes", mediaTypes);

        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);

        return "/tracks/track-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("track") Track track) {

        trackService.save(track);

        return "redirect:/tracks/list";
    }
}
