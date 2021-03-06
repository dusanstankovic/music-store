package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    // sort by Genre name ascending
    List<Genre> findAllByOrderByNameAsc();
}
