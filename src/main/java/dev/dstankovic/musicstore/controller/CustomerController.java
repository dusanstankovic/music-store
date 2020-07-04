package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Customer;
import dev.dstankovic.musicstore.entity.Employee;
import dev.dstankovic.musicstore.service.CustomerService;
import dev.dstankovic.musicstore.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private EmployeeService employeeService;

    public CustomerController(CustomerService customerService, EmployeeService employeeService) {

        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {

        model.addAttribute("customers", customerService.findAll());

        return "/customers/list-customers";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "/customers/customer-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.save(customer);

        return "redirect:/customers/list";
    }

    @GetMapping("/updateCustomer")
    public String updateCustomer(@RequestParam("customerId") int id, Model model) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "/customers/customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id) {

        customerService.deleteById(id);

        return "redirect:/customers/list";
    }
}
