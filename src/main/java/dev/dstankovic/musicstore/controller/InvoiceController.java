package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Customer;
import dev.dstankovic.musicstore.entity.Invoice;
import dev.dstankovic.musicstore.service.CustomerService;
import dev.dstankovic.musicstore.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;
    private CustomerService customerService;

    public InvoiceController(InvoiceService invoiceService, CustomerService customerService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listInvoices(Model model) {

        model.addAttribute("invoices", invoiceService.findAll());

        return "/invoices/list-invoices";
    }

    @GetMapping("/addInvoice")
    public String addInvoice(Model model) {

        Invoice invoice = new Invoice();
        model.addAttribute("invoice", invoice);

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "/invoices/invoice-form";
    }

    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute("invoice") Invoice invoice) {

        invoiceService.save(invoice);

        return "redirect:/invoices/list";
    }

    @GetMapping("/updateInvoice")
    public String updateInvoice(@RequestParam("invoiceId") int id, Model model) {

        Invoice invoice = invoiceService.findById(id);
        model.addAttribute("invoice", invoice);

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "/invoices/invoice-form";
    }

    @GetMapping("/delete")
    public String deleteInvoice(@RequestParam("invoiceId") int id) {

        invoiceService.deleteById(id);

        return "redirect:/invoices/list";
    }
}
