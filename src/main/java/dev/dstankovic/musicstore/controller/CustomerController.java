package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Customer;
import dev.dstankovic.musicstore.entity.Employee;
import dev.dstankovic.musicstore.service.CustomerService;
import dev.dstankovic.musicstore.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return "customers/list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "customers/customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "customers/customer-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customers/customer-form";
        } else {
            customerService.save(customer);
        }

        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id) {

        customerService.deleteById(id);

        return "redirect:/customers/list";
    }
}
