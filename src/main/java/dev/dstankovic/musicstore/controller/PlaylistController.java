package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Playlist;
import dev.dstankovic.musicstore.entity.Track;
import dev.dstankovic.musicstore.service.PlaylistService;
import dev.dstankovic.musicstore.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {

    private PlaylistService playlistService;
    private TrackService trackService;

    public PlaylistController(PlaylistService playlistService, TrackService trackService) {
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @GetMapping("/list")
    public String listPlaylists(Model model) {

        model.addAttribute("playlists", playlistService.findAll());

        return "/playlists/list-playlists";
    }

    @GetMapping("/addPlaylist")
    public String addPlaylist(Model model) {

        Playlist playlist = new Playlist();
        model.addAttribute("playlist", playlist);

        List<Track> tracks = trackService.findAll();
        model.addAttribute("tracks", tracks);

        return "/playlists/playlist-form";
    }

    @PostMapping("/save")
    public String savePlaylist(@ModelAttribute("playlist") Playlist playlist) {

        playlistService.save(playlist);

        return "redirect:/playlists/list";
    }

    @GetMapping("/updatePlaylist")
    public String updatePlaylist(@RequestParam("playlistId") int id, Model model) {

        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);

        List<Track> tracks = trackService.findAll();
        model.addAttribute("tracks", tracks);

        return "/playlists/playlist-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("playlistId") int id) {

        playlistService.deleteById(id);

        return "redirect:/playlists/list";
    }
}
