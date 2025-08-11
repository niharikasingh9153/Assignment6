package com.hdfc.policy.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super("Customer with ID " + customerId + " not found.");
    }
}
