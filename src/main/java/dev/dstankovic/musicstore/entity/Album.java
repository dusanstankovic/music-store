package dev.dstankovic.musicstore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import dev.dstankovic.musicstore.util.Views;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Album", schema = "Chinook")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "albumId", nullable = false)
    private int id;

    @NotBlank(message = "Album title is required")
    @Column(name = "Title", length = 160, nullable = false)
    @JsonView(Views.External.class)
    private String title;

    @NotNull(message = "Artist is required")
    @ManyToOne
    @JoinColumn(name = "ArtistId")
    private Artist artist;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Track> tracks;

    public Album() {
    }

    public Album(@NotNull(message = "Album title is required") String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

