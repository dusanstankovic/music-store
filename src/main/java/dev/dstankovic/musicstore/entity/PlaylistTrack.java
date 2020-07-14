package dev.dstankovic.musicstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "PlaylistTrack", schema = "Chinook")
public class PlaylistTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlaylistId", nullable = false)
    private int id;

    @Column(name = "TrackId", nullable = false)
    private int trackId;

    public PlaylistTrack() {
    }

    public PlaylistTrack(int trackId) {
        this.trackId = trackId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public String toString() {
        return "PlaylistTrack{" +
                "id=" + id +
                ", trackId=" + trackId +
                '}';
    }
}
