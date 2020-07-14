package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Customer;
import dev.dstankovic.musicstore.entity.Employee;
import dev.dstankovic.musicstore.report.GenerateCustomersListReport;
import dev.dstankovic.musicstore.service.CustomerService;
import dev.dstankovic.musicstore.service.EmployeeService;
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

        return "customers/customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        return "customers/customer-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "customers/customer-form";
        }

        customerService.save(customer);

        return "redirect:/customers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id) {

        customerService.deleteById(id);

        return "redirect:/customers/list";
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customersReport() {

        List<Customer> customers = customerService.findAll();

        ByteArrayInputStream bis = GenerateCustomersListReport.customersReport(customers);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @ModelAttribute
    public void addEmployeesForDropdownList(Model model) {

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
    }
}
