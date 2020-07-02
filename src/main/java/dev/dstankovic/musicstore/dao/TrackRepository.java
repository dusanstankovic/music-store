package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Integer> {
}
