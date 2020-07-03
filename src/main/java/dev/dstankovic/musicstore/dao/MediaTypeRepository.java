package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaTypeRepository extends JpaRepository<MediaType, Integer> {

    // sort by Media Type name ascending
    List<MediaType> findAllByOrderByNameAsc();
}
