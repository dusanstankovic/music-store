package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.Employee;
import dev.dstankovic.musicstore.report.GenerateEmployeesListReport;
import dev.dstankovic.musicstore.service.EmployeeService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private ModelAndView modelAndView = new ModelAndView();

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

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeesReport() {

        List<Employee> employees = employeeService.findAll();

        ByteArrayInputStream bis = GenerateEmployeesListReport.employeesReport(employees);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees_report.pdf");

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
