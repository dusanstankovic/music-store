package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    void save(Customer customer);

    void deleteById(int id);
}
