package com.postech.mscustomers.entity;

import com.postech.mscustomers.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "tb_Customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    public Customer(CustomerDTO CustomerDTO) {
        this.id = UUID.randomUUID();
        this.nome = CustomerDTO.getNome();
    }
    public List<Customer> toList(List<CustomerDTO> customerList) {
        return customerList
                .stream()
                .map(Customer::new)
                .collect(Collectors.toList());
    }

}
