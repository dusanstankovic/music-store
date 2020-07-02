package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.EmployeeRepository;
import dev.dstankovic.musicstore.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Did not find employee with id: " + id);
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {

        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {

        employeeRepository.deleteById(id);
    }
}
