package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Employee;
import dev.dstankovic.musicstore.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {

        model.addAttribute("employees", employeeService.findAll());

        return "/employees/list-employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "/employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/updateEmployee")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {

        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "/employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {

        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
