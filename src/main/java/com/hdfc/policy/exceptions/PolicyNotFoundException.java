package com.hdfc.policy.exceptions;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(String policyId) {
        super("Policy with ID " + policyId + " not found.");
    }
}
