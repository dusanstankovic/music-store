package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> findAll();

    Album findById(int id);

    void save(Album album);

    void deleteById(int id);
}
