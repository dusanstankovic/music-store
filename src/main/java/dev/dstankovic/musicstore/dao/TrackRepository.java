package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {

    // sort by Track name ascending
    List<Track> findAllByOrderByNameAsc();
}
