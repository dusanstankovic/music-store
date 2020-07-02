package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaTypeRepository extends JpaRepository<MediaType, Integer> {
}
