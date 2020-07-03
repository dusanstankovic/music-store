package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.AlbumRepository;
import dev.dstankovic.musicstore.entity.Album;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {

        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {

        return albumRepository.findAllByOrderByArtistAsc();
    }

    @Override
    public Album findById(int id) {
        Optional<Album> result = albumRepository.findById(id);
        Album album = null;
        if (result.isPresent()) {
            album = result.get();
        } else {
            throw new RuntimeException("Did not find album with id: " + id);
        }
        return album;
    }

    @Override
    public void save(Album album) {

        albumRepository.save(album);
    }

    @Override
    public void deleteById(int id) {

        albumRepository.deleteById(id);
    }
}
