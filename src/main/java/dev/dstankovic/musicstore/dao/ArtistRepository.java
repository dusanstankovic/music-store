package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
