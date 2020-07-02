package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.InvoiceLineRepository;
import dev.dstankovic.musicstore.entity.InvoiceLine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceLineServiceImpl implements InvoiceLineService {

    private InvoiceLineRepository invoiceLineRepository;

    public InvoiceLineServiceImpl(InvoiceLineRepository invoiceLineRepository) {

        this.invoiceLineRepository = invoiceLineRepository;
    }

    @Override
    public List<InvoiceLine> findAll() {

        return invoiceLineRepository.findAll();
    }

    @Override
    public InvoiceLine findById(int id) {
        Optional<InvoiceLine> result = invoiceLineRepository.findById(id);
        InvoiceLine invoiceLine = null;
        if (result.isPresent()) {
            invoiceLine = result.get();
        } else {
            throw new RuntimeException("Did not find invoice line with id: " + id);
        }
        return invoiceLine;
    }

    @Override
    public void save(InvoiceLine invoiceLine) {

        invoiceLineRepository.save(invoiceLine);
    }

    @Override
    public void deleteById(int id) {

        invoiceLineRepository.deleteById(id);
    }
}
