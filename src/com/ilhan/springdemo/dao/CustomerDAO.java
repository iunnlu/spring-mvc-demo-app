package com.ilhan.springdemo.dao;

import com.ilhan.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomerById(int theId);

    void deleteCustomer(int theId);
}
