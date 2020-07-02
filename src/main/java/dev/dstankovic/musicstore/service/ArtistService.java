package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> findAll();

    Artist findById(int id);

    void save(Artist artist);

    void deleteById(int id);
}
