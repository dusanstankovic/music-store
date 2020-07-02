package dev.dstankovic.musicstore.dao;

import dev.dstankovic.musicstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
