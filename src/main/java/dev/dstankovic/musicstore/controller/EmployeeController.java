package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Employee;
import dev.dstankovic.musicstore.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {

        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {

        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "employees/employee-form";
        }

        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {

        employeeService.deleteById(employeeId);

        return "redirect:/employees/list";
    }

    @ModelAttribute
    public void addEmployeesForDropdownList(Model model) {

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
    }
}
