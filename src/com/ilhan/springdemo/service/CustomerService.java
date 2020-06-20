package com.ilhan.springdemo.service;

import com.ilhan.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    void saveCustomer(Customer customer);

    Customer getCustomerById(int theId);

    void deleteCustomer(int theId);
}
