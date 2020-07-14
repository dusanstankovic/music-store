package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Customer;
import dev.dstankovic.musicstore.entity.Invoice;
import dev.dstankovic.musicstore.report.GenerateInvoicesListReport;
import dev.dstankovic.musicstore.service.CustomerService;
import dev.dstankovic.musicstore.service.InvoiceService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@ControllerAdvice
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

        return "invoices/list-invoices";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Invoice invoice = new Invoice();
        model.addAttribute("invoice", invoice);

        return "invoices/invoice-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("invoiceId") int id, Model model) {

        Invoice invoice = invoiceService.findById(id);
        model.addAttribute("invoice", invoice);

        return "invoices/invoice-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("invoice") Invoice invoice, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "invoices/invoice-form";
        }

        invoiceService.save(invoice);

        return "redirect:/invoices/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("invoiceId") int id) {

        invoiceService.deleteById(id);

        return "redirect:/invoices/list";
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> invoicesReport() {

        List<Invoice> invoices = invoiceService.findAll();

        ByteArrayInputStream bis = GenerateInvoicesListReport.invoicesReport(invoices);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoices_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @ModelAttribute
    public void addCustomersForDropdownList(Model model) {

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
    }
}
