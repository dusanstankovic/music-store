package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
