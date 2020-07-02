package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.InvoiceRepository;
import dev.dstankovic.musicstore.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {

        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {

        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(int id) {
        Optional<Invoice> result = invoiceRepository.findById(id);
        Invoice invoice = null;
        if (result.isPresent()) {
            invoice = result.get();
        } else {
            throw new RuntimeException("Did not find invoice id: " + id);
        }
        return invoice;
    }

    @Override
    public void save(Invoice invoice) {

        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(int id) {

        invoiceRepository.deleteById(id);
    }
}
