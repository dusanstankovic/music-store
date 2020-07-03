package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {

        model.addAttribute("customers", customerService.findAll());

        return "/customers/list-customers";
    }
}
