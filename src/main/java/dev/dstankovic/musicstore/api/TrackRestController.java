package dev.dstankovic.musicstore.api;

import com.fasterxml.jackson.annotation.JsonView;
import dev.dstankovic.musicstore.entity.Track;
import dev.dstankovic.musicstore.service.AlbumService;
import dev.dstankovic.musicstore.service.GenreService;
import dev.dstankovic.musicstore.service.MediaTypeService;
import dev.dstankovic.musicstore.service.TrackService;
import dev.dstankovic.musicstore.util.Views;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TrackRestController {

    private TrackService trackService;
    private GenreService genreService;
    private MediaTypeService mediaTypeService;
    private AlbumService albumService;

    public TrackRestController(TrackService trackService,
                               GenreService genreService,
                               MediaTypeService mediaTypeService,
                               AlbumService albumService) {
        this.trackService = trackService;
        this.genreService = genreService;
        this.mediaTypeService = mediaTypeService;
        this.albumService = albumService;
    }

    @GetMapping(value = "/list-tracks", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.External.class)
    public List<Track> getTracksList() {

        return trackService.findAll();
    }

}
