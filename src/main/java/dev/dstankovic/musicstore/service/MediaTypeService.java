package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.MediaType;

import java.util.List;

public interface MediaTypeService {

    List<MediaType> findAll();

    MediaType findById(int id);

    void save(MediaType mediaType);

    void deleteById(int id);
}
