package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.PlaylistRepository;
import dev.dstankovic.musicstore.entity.Playlist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {

        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<Playlist> findAll() {

        return playlistRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Playlist findById(int id) {
        Optional<Playlist> result = playlistRepository.findById(id);
        Playlist playlist = null;
        if (result.isPresent()) {
            playlist = result.get();
        } else {
            throw new RuntimeException("Did not find playlist with id: " + id);
        }
        return playlist;
    }

    @Override
    public void save(Playlist playlist) {

        playlistRepository.save(playlist);
    }

    @Override
    public void deleteById(int id) {

        playlistRepository.deleteById(id);
    }
}
