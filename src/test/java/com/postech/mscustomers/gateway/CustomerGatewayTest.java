package com.postech.mscustomers.gateway;

import com.postech.mscustomers.entity.Customer;
import com.postech.mscustomers.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerGatewayTest {
    @InjectMocks
    private CustomerGateway customerGateway;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCustomer_ValidInput_ReturnsCustomer() {
        // Arrange
        Customer customer = new Customer();
        // Mock the behavior of customerRepository
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer result = customerGateway.createCustomer(customer);

        // Assert
        assertEquals(customer, result);
    }

    @Test
    void testUpdateCustomer_ValidInput_ReturnsCustomer() {
        // Arrange
        Customer customer = new Customer();
        // Mock the behavior of customerRepository
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer result = customerGateway.updateCustomer(customer);

        // Assert
        assertEquals(customer, result);
    }

    @Test
    void testDeleteCustomer_ValidInput_ReturnsTrue() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        // Mock the behavior of customerRepository
        doNothing().when(customerRepository).deleteById(customerId);

        // Act
        boolean result = customerGateway.deleteCustomer(String.valueOf(customerId));

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteCustomer_InvalidInput_ReturnsFalse() {
        // Arrange
        String invalidCustomerId = "invalid-id";

        // Act
        boolean result = customerGateway.deleteCustomer(invalidCustomerId);

        // Assert
        assertFalse(result);
    }

    @Test
    void testFindCustomer_ValidInput_ReturnsCustomer() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        Customer expectedCustomer = new Customer();
        // Mock the behavior of customerRepository
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Customer result = customerGateway.findCustomer(String.valueOf(customerId));

        // Assert
        assertEquals(expectedCustomer, result);
    }

    @Test
    void testFindCustomer_InvalidInput_ReturnsNull() {
        // Arrange
        String invalidCustomerId = "invalid-id";

        // Act
        Customer result = customerGateway.findCustomer(invalidCustomerId);

        // Assert
        assertNull(result);
    }

    @Test
    void testListAllCustomers_ReturnsListOfCustomers() {
        // Arrange
        List<Customer> expectedCustomers = List.of(new Customer(), new Customer());
        // Mock the behavior of customerRepository
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // Act
        List<Customer> result = customerGateway.listAllCustomers();

        // Assert
        assertEquals(expectedCustomers, result);
    }
}
