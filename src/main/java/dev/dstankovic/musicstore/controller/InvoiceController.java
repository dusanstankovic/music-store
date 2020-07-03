package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {

        this.invoiceService = invoiceService;
    }

    @GetMapping("/list")
    public String listInvoices(Model model) {

        model.addAttribute("invoices", invoiceService.findAll());

        return "/invoices/list-invoices";
    }
}
