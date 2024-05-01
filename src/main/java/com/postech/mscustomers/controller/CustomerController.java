package com.postech.mscustomers.controller;

import com.postech.mscustomers.MscustomersApplication;
import com.postech.mscustomers.dto.CustomerDTO;
import com.postech.mscustomers.entity.Customer;
import com.postech.mscustomers.gateway.CustomerGateway;
import com.postech.mscustomers.usecase.CustomerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.SchemaPropertyDeprecatingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerGateway customerGateway;
    private final MscustomersApplication mscustomersApplication;
    private final SchemaPropertyDeprecatingConverter schemaPropertyDeprecatingConverter;

    @PostMapping("")
    @Operation(summary = "Request for create a customer", responses = {
            @ApiResponse(description = "The new customers was created", responseCode = "201", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(description = "Customer Name Invalid", responseCode = "400", content = @Content(schema = @Schema(type = "string", example = "O nome não pode ser nulo.")))
    })
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        log.info("PostMapping - createCustomer for customer [{}]", customerDTO.getNome());
        try {
            Customer customerNew = new Customer(customerDTO);
            CustomerUseCase.validarInsertCustomer(customerNew);
            Customer customerCreated = customerGateway.createCustomer(customerNew);
            return new ResponseEntity<>(customerCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Request for list all customers", responses = {
            @ApiResponse(description = "Customer's list", responseCode = "200"),
    })
    public ResponseEntity<List<Customer>> listAllCustomers() {
        log.info("GetMapping - listCustomers");
        List<Customer> customers = customerGateway.listAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get only customer by ID", responses = {
            @ApiResponse(description = "The customer by ID", responseCode = "200", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(description = "Customer Not Found", responseCode = "404", content = @Content(schema = @Schema(type = "string", example = "Cliente não encontrado.")))
    })
    public ResponseEntity<?> findCustomer(@PathVariable String id) {
        log.info("GetMapping - FindCustomer");
        Customer customer = customerGateway.findCustomer(id);
        if (customer != null) {
            return new ResponseEntity<>( customer , HttpStatus.OK);
        }
        return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Request for update a customer by ID", responses = {
            @ApiResponse(description = "The customers was updated", responseCode = "200", content = @Content(schema = @Schema(implementation = Customer.class)))
    })
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerDTO customerDTO) {
        log.info("PutMapping - updateCustomer");
        try {
            Customer customerOld = customerGateway.findCustomer(id);
            Customer customerNew = new Customer(customerDTO);
            customerNew.setId(UUID.fromString(id));
            CustomerUseCase.validarUpdateCliente(id, customerOld, customerNew);
            customerNew = customerGateway.updateCustomer(customerNew);
            return new ResponseEntity<>(customerNew, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer by ID", responses = {
            @ApiResponse(description = "The customer was deleted", responseCode = "200", content = @Content(schema = @Schema(type = "string", example = "Cliente removido."))),
            @ApiResponse(description = "Customer Not Found", responseCode = "404", content = @Content(schema = @Schema(type = "string", example = "Cliente não encontrado.")))
    })
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        log.info("DeleteMapping - deleteCustomer");
        try {
            Customer customer = customerGateway.findCustomer(id);
            CustomerUseCase.validarDeleteCliente(customer);
            customerGateway.deleteCustomer(id);
            return new ResponseEntity<>("Cliente removido.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

