package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
