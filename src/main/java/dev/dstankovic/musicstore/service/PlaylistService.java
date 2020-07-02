package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.Playlist;

import java.util.List;

public interface PlaylistService {

    List<Playlist> findAll();

    Playlist findById(int id);

    void save(Playlist playlist);

    void deleteById(int id);
}
