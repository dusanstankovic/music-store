package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.InvoiceLineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoicelines")
public class InvoiceLineController {

    private InvoiceLineService invoiceLineService;

    public InvoiceLineController(InvoiceLineService invoiceLineService) {

        this.invoiceLineService = invoiceLineService;
    }

    @GetMapping("/list")
    public String listInvoiceLines(Model model) {

        model.addAttribute("invoicelines", invoiceLineService.findAll());

        return "list-invoice-lines";
    }
}
