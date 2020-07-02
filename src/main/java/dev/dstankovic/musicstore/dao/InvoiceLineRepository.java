package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Integer> {
}
