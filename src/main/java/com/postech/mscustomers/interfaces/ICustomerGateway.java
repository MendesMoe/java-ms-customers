package com.postech.mscustomers.interfaces;

import com.postech.mscustomers.entity.Customer;

import java.util.List;

public interface ICustomerGateway {
    public Customer createCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public boolean deleteCustomer(String strId);

    public Customer findCustomer(String strId);

    public List<Customer> listAllCustomers();
}
