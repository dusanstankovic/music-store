package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.InvoiceLine;

import java.util.List;

public interface InvoiceLineService {

    List<InvoiceLine> findAll();

    InvoiceLine findById(int id);

    void save(InvoiceLine invoiceLine);

    void deleteById(int id);
}
