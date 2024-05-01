package com.postech.mscustomers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.mscustomers.dto.CustomerDTO;
import com.postech.mscustomers.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NewEntitiesHelper {

    private static UUID customerID = UUID.fromString("7247101a-9bab-414a-83e5-a61b07e2146a");

    /* ----------------------------- Customer ------------------------------- */
    public static Customer createACustomer(){
        Customer customer = new Customer(customerID, "joe");
        return customer;
    }
    public static CustomerDTO gerarCustomerInsertRequest() {
        return CustomerDTO.builder()
                .nome("John")
                .build();
    }
    public static List<CustomerDTO> gerarCustomerListRequest() {
        List<CustomerDTO>  customerDTOList = new ArrayList<>();
        customerDTOList.add( CustomerDTO.builder()
                .nome("John")
                .build());

        customerDTOList.add( CustomerDTO.builder()
                .nome("Ana")
                .build());

        return customerDTOList;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
