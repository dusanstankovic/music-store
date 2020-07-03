package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // sort by Employee last name ascending
    List<Employee> findAllByOrderByLastNameAsc();
}
