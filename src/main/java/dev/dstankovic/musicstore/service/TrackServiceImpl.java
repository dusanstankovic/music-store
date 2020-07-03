package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.TrackRepository;
import dev.dstankovic.musicstore.entity.Track;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> findAll() {

        return trackRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Track findById(int id) {
        Optional<Track> result = trackRepository.findById(id);
        Track track = null;
        if (result.isPresent()) {
            track = result.get();
        } else {
            throw new RuntimeException("Did not find track with id: " + id);
        }
        return track;
    }

    @Override
    public void save(Track track) {

        trackRepository.save(track);
    }

    @Override
    public void deleteById(int id) {

        trackRepository.deleteById(id);
    }
}
