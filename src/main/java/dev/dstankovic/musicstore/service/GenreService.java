package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre findById(int id);

    void save(Genre genre);

    void deleteById(int id);
}
