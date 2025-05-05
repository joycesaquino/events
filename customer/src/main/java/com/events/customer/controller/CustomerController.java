package com.events.customer.controller;

import com.events.customer.dto.CustomerCreateDTO;
import com.events.customer.dto.CustomerDTO;
import com.events.customer.dto.CustomerUpdateDTO;
import com.events.customer.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for customer operations.
 */
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Get all customers.
     *
     * @return list of all customers as DTOs
     */
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /**
     * Get a customer by ID.
     *
     * @param id the customer ID
     * @return the customer DTO if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get a customer by email.
     *
     * @param email the customer email
     * @return the customer DTO if found, or 404 if not found
     */
    @GetMapping("/by-email")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@RequestParam String email) {
        return customerService.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new customer.
     *
     * @param customerCreateDTO the customer data to create
     * @return the created customer DTO
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerCreateDTO customerCreateDTO) {
        if (customerService.existsByEmail(customerCreateDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customerCreateDTO));
    }

    /**
     * Update an existing customer.
     *
     * @param id the customer ID
     * @param customerUpdateDTO the customer data to update
     * @return the updated customer DTO if found, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return customerService.updateCustomer(id, customerUpdateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a customer by ID.
     *
     * @param id the customer ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.getCustomerById(id).isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
