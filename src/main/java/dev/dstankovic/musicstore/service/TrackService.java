package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.Track;

import java.util.List;

public interface TrackService {

    List<Track> findAll();

    Track findById(int id);

    void save(Track track);

    void deleteById(int id);
}
