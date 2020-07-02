package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
