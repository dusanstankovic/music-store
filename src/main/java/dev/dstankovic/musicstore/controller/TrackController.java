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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
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

        return "tracks/list-tracks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Track track = new Track();
        model.addAttribute("track", track);

        return "tracks/track-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("trackId") int id, Model model) {

        Track track = trackService.findById(id);
        model.addAttribute("track", track);

        return "tracks/track-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("track") Track track, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "tracks/track-form";
        }

        trackService.save(track);

        return "redirect:/tracks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("trackId") int id) {

        trackService.deleteById(id);

        return "redirect:/tracks/list";
    }

    @ModelAttribute
    public void addGenresForDropdownList(Model model) {

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
    }

    @ModelAttribute
    public void addMediaTypesForDropdownList(Model model) {

        List<MediaType> mediaTypes = mediaTypeService.findAll();
        model.addAttribute("mediatypes", mediaTypes);
    }

    @ModelAttribute
    public void addAlbumsForDropdownList(Model model) {

        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
    }
}
