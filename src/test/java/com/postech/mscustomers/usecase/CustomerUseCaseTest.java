package com.postech.mscustomers.usecase;

import com.postech.mscustomers.entity.Customer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CustomerUseCaseTest {
    @Test
    void testValidarInsertCustomer_ValidInput() {
        // Arrange
        Customer customer = new Customer();
        customer.setNome("John Doe");

        // Act & Assert
        assertDoesNotThrow(() -> CustomerUseCase.validarInsertCustomer(customer));
    }

    @Test
    void testValidarInsertCustomer_NullName_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CustomerUseCase.validarInsertCustomer(customer));
    }

    @Test
    void testValidarUpdateCliente_ValidInput() {
        // Arrange
        String customerId = UUID.randomUUID().toString();
        Customer customerToUpdate = new Customer();
        customerToUpdate.setId(UUID.fromString(customerId));
        Customer customerNew = new Customer();
        customerNew.setId(UUID.fromString(customerId));

        // Act & Assert
        assertDoesNotThrow(() -> CustomerUseCase.validarUpdateCliente(customerId, customerToUpdate, customerNew));
    }

    @Test
    void testValidarUpdateCliente_CustomerNotFound_ThrowsException() {
        // Arrange
        String customerId = UUID.randomUUID().toString();
        Customer customerNew = new Customer();
        customerNew.setId(UUID.fromString(customerId));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CustomerUseCase.validarUpdateCliente(customerId, null, customerNew));
    }

    @Test
    void testValidarDeleteCliente_ValidInput() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertDoesNotThrow(() -> CustomerUseCase.validarDeleteCliente(customer));
    }

    @Test
    void testValidarDeleteCliente_CustomerNotFound_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> CustomerUseCase.validarDeleteCliente(null));
    }
}
