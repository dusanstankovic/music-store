package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    // sort by Playlist name ascending
    List<Playlist> findAllByOrderByNameAsc();
}
