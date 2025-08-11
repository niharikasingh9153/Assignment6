package com.hdfc.policy.models;

import java.util.*;

public class Customer {
    private final String customerId;
    private String name;
    private int age;
    private Map<String, Policy> policies = new HashMap<>();

    public Customer(String customerId, String name, int age) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<String, Policy> getPolicies() {
        return policies;
    }

    public void addPolicy(Policy policy) {
        policies.put(policy.getPolicyId(), policy);
    }

    public void removePolicy(String policyId) {
        policies.remove(policyId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return customerId.equals(c.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return customerId + " - " + name;
    }
}
