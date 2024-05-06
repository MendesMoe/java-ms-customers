package com.postech.mscustomers.controller;

import com.postech.mscustomers.dto.CustomerDTO;
import com.postech.mscustomers.entity.Customer;
import com.postech.mscustomers.gateway.CustomerGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreatetCustomer {
        @Test
        void devePermitirRegistrarCliente() throws Exception {
            // Arrange
            CustomerDTO customerDTO = new CustomerDTO(
                    "John Doe",
                    "123.456.789-00",
                    "john.doe@mail.com",
                    "Rua Legal,123",
                    "Sao Paulo",
                    "SP",
                    "12345-000"
            );

            when(customerGateway.createCustomer(any())).thenReturn(new Customer());

            // Act
            ResponseEntity<?> response = customerController.createCustomer(customerDTO);

            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

        @Test
        void deveGerarExcecaoQuandoRegistrarClienteNomeNulo() throws Exception {
            // Arrange
            CustomerDTO customerDTO = new CustomerDTO();

            // Act
            ResponseEntity<?> response = customerController.createCustomer(customerDTO);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @Nested
    class ReadCustomer {
        @Test
        void devePermitirPesquisarUmCliente() throws Exception {
            // Arrange
            String customerId = "123";
            Customer customer = new Customer();
            when(customerGateway.findCustomer(customerId)).thenReturn(customer);

            // Act
            ResponseEntity<?> response = customerController.findCustomer(customerId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(customer, response.getBody());
        }

        @Test
        void devePermitirListarTodosClientes() throws Exception {
            // Arrange
            List<Customer> customers = new ArrayList<>();
            customers.add(new Customer());
            customers.add(new Customer());

            when(customerGateway.listAllCustomers()).thenReturn(customers);

            // Act
            ResponseEntity<List<Customer>> response = customerController.listAllCustomers();

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(customers, response.getBody());
        }

        @Test
        void deveGerarExcecaoSeNaoEncontrarCliente() throws Exception {
            // Arrange
            String invalidId = "999";
            when(customerGateway.findCustomer(invalidId)).thenReturn(null);

            // Act
            ResponseEntity<?> response = customerController.findCustomer(invalidId);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Cliente não encontrado.", response.getBody());
        }
    }

    @Nested
    class UpdateCustomer {
        @Test
        void devePermitirAtualizarCliente() throws Exception {
            // Arrange
            UUID customerId = UUID.randomUUID();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setNome("John Doe");
            // Mock the behavior of customerGateway
            Customer customerOld = new Customer();
            customerOld.setId(customerId);
            Customer customerNew = new Customer(customerDTO);
            when(customerGateway.findCustomer(String.valueOf(customerId))).thenReturn(customerOld);
            when(customerGateway.updateCustomer(customerNew)).thenReturn(customerNew);

            // Act
            ResponseEntity<?> response = customerController.updateCustomer(String.valueOf(customerId), customerDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void deveGerarExcecaoQuandoAtualizarClienteNomeNulo() throws Exception {

        }

        @Test
        void deveGerarExcecaoQuandoAtualizarClienteNãoEncontrado() throws Exception {
            // Arrange
            String invalidId = "999";
            CustomerDTO customerDTO = new CustomerDTO();

            // Act
            ResponseEntity<?> response = customerController.updateCustomer(invalidId, customerDTO);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @Nested
    class DeleteCustomer {
        @Test
        void devePermitirApagarCliente() throws Exception {
            // Arrange
            String customerId = UUID.randomUUID().toString();
            Customer customer = new Customer();
            // Mock the behavior of customerGateway
            when(customerGateway.findCustomer(customerId)).thenReturn(customer);

            // Act
            ResponseEntity<?> response = customerController.deleteCustomer(customerId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Cliente removido.", response.getBody());
        }

        @Test
        void deveGerarExcecaoQuandoDeletarClienteNãoEncontrado() throws Exception {
            // Arrange
            String customerId = UUID.randomUUID().toString();
            // Mock the behavior of customerGateway
            when(customerGateway.findCustomer(customerId)).thenReturn(null);

            // Act
            ResponseEntity<?> response = customerController.deleteCustomer(customerId);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Cliente não encontrado.", response.getBody());
        }
    }
}

