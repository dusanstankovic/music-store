package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.MediaTypeRepository;
import dev.dstankovic.musicstore.entity.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaTypeServiceImpl implements MediaTypeService {

    private MediaTypeRepository mediaTypeRepository;

    public MediaTypeServiceImpl(MediaTypeRepository mediaTypeRepository) {

        this.mediaTypeRepository = mediaTypeRepository;
    }

    @Override
    public List<MediaType> findAll() {

        return mediaTypeRepository.findAll();
    }

    @Override
    public MediaType findById(int id) {
        Optional<MediaType> result = mediaTypeRepository.findById(id);
        MediaType mediaType = null;
        if (result.isPresent()) {
            mediaType = result.get();
        } else {
            throw new RuntimeException("Did not find media type with id: " + id);
        }
        return mediaType;
    }

    @Override
    public void save(MediaType mediaType) {

        mediaTypeRepository.save(mediaType);
    }

    @Override
    public void deleteById(int id) {

        mediaTypeRepository.deleteById(id);
    }
}
