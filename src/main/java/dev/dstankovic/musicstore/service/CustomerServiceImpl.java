package dev.dstankovic.musicstore.service;

import dev.dstankovic.musicstore.dao.CustomerRepository;
import dev.dstankovic.musicstore.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if (result.isPresent()) {
            customer = result.get();
        } else {
            throw new RuntimeException("Did not find customer with id: " + id);
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {

        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {

        customerRepository.deleteById(id);
    }
}
