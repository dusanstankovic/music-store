package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
