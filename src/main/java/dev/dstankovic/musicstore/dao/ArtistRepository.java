package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    // sort by Artist name ascending
    List<Artist> findAllByOrderByNameAsc();
}
