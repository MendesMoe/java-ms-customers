package com.postech.mscustomers.usecase;

import com.postech.mscustomers.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerUseCase {

    public static void validarInsertCustomer(Customer customerNew) {
        if (customerNew.getNome() == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo.");
        }
    }

    public static void validarUpdateCliente(String strId, Customer customerToUpdate, Customer customerNew) {

        if (customerToUpdate == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public static void validarDeleteCliente(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }
}
