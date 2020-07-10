package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Customer;
import dev.dstankovic.musicstore.entity.Invoice;
import dev.dstankovic.musicstore.service.CustomerService;
import dev.dstankovic.musicstore.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ModelAttribute
    public void addCustomersForDropdownList(Model model) {

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
    }
}
