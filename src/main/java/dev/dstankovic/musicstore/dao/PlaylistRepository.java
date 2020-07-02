package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}
