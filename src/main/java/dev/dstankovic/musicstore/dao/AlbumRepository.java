package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    // sort by Artist name
    List<Album> findAllByOrderByArtistAsc();
}
