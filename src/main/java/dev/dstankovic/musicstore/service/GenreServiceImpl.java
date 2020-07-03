package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.GenreRepository;
import dev.dstankovic.musicstore.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {

        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {

        return genreRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Genre findById(int id) {
        Optional<Genre> result = genreRepository.findById(id);
        Genre genre = null;
        if (result.isPresent()) {
            genre = result.get();
        } else {
            throw new RuntimeException("Did not find genre with id: " + id);
        }
        return genre;
    }

    @Override
    public void save(Genre genre) {

        genreRepository.save(genre);
    }

    @Override
    public void deleteById(int id) {

        genreRepository.deleteById(id);
    }
}
