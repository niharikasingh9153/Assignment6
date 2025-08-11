package com.hdfc.policy.services;

import com.hdfc.policy.models.*;
import com.hdfc.policy.exceptions.*;
import com.hdfc.policy.repository.Repository;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class PolicyService {
    private static volatile PolicyService instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private final Repository<Customer> customerRepo = new Repository<>();

    private PolicyService() {}

    public static PolicyService getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new PolicyService();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        customerRepo.add(customer.getCustomerId(), customer);
    }

    public void removeCustomer(String customerId) {
        customerRepo.remove(customerId);
    }

    public void addPolicyToCustomer(String customerId, Policy policy) {
        Customer c = getCustomer(customerId);
        c.addPolicy(policy);
    }

    public void removePolicy(String customerId, String policyId) {
        Customer c = getCustomer(customerId);
        if (!c.getPolicies().containsKey(policyId)) throw new PolicyNotFoundException(policyId);
        c.removePolicy(policyId);
    }

    public double calculateTotalMaturity(String customerId) {
        return getCustomer(customerId)
                .getPolicies().values().stream()
                .mapToDouble(Policy::calculateMaturityAmount)
                .sum();
    }

    public void printCustomerDetails() {
        customerRepo.getAll().forEach(c -> {
            System.out.println(c.getName() + ":");
            c.getPolicies().forEach((id, policy) -> policy.printDetails());
        });
    }

    public Customer getCustomer(String id) {
        Customer c = customerRepo.get(id);
        if (c == null) throw new CustomerNotFoundException(id);
        return c;
    }

    public void displayCustomersSortedByName() {
        TreeMap<String, Customer> sorted = new TreeMap<>();
        customerRepo.getAll().forEach(c -> sorted.put(c.getName(), c));
        sorted.values().forEach(System.out::println);
    }

    public void displayAllPoliciesSortedByPremium() {
        List<Policy> allPolicies = customerRepo.getAll().stream()
                .flatMap(c -> c.getPolicies().values().stream())
                .sorted(Comparator.comparingDouble(Policy::getPremiumAmount))
                .collect(Collectors.toList());
        allPolicies.forEach(Policy::printDetails);
    }

    public Map<String, List<String>> exportCustomerPolicyMap() {
        Map<String, List<String>> result = new HashMap<>();
        for (Customer c : customerRepo.getAll()) {
            List<String> policyIds = new ArrayList<>(c.getPolicies().keySet());
            result.put(c.getName(), policyIds);
        }
        return result;
    }

    // Iteration examples

    public void iteratePoliciesUsingKeySet() {
        for (Customer c : customerRepo.getAll()) {
            System.out.println("Policies of " + c.getName());
            Set<String> keys = c.getPolicies().keySet();
            for (String key : keys) {
                System.out.println("PolicyId: " + key);
            }
        }
    }

    public void iteratePoliciesUsingEntrySet() {
        for (Customer c : customerRepo.getAll()) {
            System.out.println("Policies of " + c.getName());
            Set<Map.Entry<String, Policy>> entries = c.getPolicies().entrySet();
            for (Map.Entry<String, Policy> entry : entries) {
                System.out.println(entry.getKey() + " -> " + entry.getValue().getPremiumAmount());
            }
        }
    }

    public void iteratePoliciesUsingForEach() {
        for (Customer c : customerRepo.getAll()) {
            System.out.println("Policies of " + c.getName());
            c.getPolicies().forEach((k, v) ->
                    System.out.println(k + " | Premium: " + v.getPremiumAmount()));
        }
    }
}
