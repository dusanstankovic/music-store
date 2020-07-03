package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // sort by Customer last name ascending
    List<Customer> findAllByOrderByLastNameAsc();
}
