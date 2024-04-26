package com.postech.mscustomers.gateway;

import com.postech.mscustomers.entity.Customer;
import com.postech.mscustomers.interfaces.ICustomerGateway;
import com.postech.mscustomers.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerGateway implements ICustomerGateway {
    private final CustomerRepository customerRepository;

    public CustomerGateway(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(String strId) {
        try {
            UUID uuid = UUID.fromString(strId);
            customerRepository.deleteById(uuid);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Customer findCustomer(String strId) {
        try {
            UUID uuid = UUID.fromString(strId);
            return customerRepository.findById(uuid).orElseThrow();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }
}
