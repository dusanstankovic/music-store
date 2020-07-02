package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.ArtistRepository;
import dev.dstankovic.musicstore.entity.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {

        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {

        return artistRepository.findAll();
    }

    @Override
    public Artist findById(int id) {
        Optional<Artist> result = artistRepository.findById(id);
        Artist artist = null;
        if (result.isPresent()) {
            artist = result.get();
        } else {
            throw new RuntimeException("Did not find artist with id: " + id);
        }
        return artist;
    }

    @Override
    public void save(Artist artist) {

        artistRepository.save(artist);
    }

    @Override
    public void deleteById(int id) {

        artistRepository.deleteById(id);
    }
}
