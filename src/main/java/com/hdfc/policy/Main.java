package com.hdfc.policy;

import com.hdfc.policy.models.*;
import com.hdfc.policy.factory.PolicyFactory;
import com.hdfc.policy.services.PolicyService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Policy.printWelcomeMessage();

        PolicyService service = PolicyService.getInstance();

        // Add customers
        Customer c1 = new Customer("C001", "Alice", 30);
        Customer c2 = new Customer("C002", "Bob", 40);
        service.addCustomer(c1);
        service.addCustomer(c2);

        // Add policies via factory
        Policy p1 = PolicyFactory.createPolicy("life", "P001", 10000, 10);
        Policy p2 = PolicyFactory.createPolicy("health", "P002", 8000, 5);
        Policy p3 = PolicyFactory.createPolicy("term", "P003", 6000, 8);
        Policy p4 = PolicyFactory.createPolicy("life", "P004", 12000, 12);

        service.addPolicyToCustomer("C001", p1);
        service.addPolicyToCustomer("C001", p2);
        service.addPolicyToCustomer("C002", p3);
        service.addPolicyToCustomer("C002", p4);

        System.out.println("\n--- Customer Details ---");
        service.printCustomerDetails();

        System.out.println("\n--- Total Maturity Amount for Alice ---");
        System.out.println(service.calculateTotalMaturity("C001"));

        System.out.println("\n--- Customers sorted by Name ---");
        service.displayCustomersSortedByName();

        System.out.println("\n--- All Policies sorted by Premium ---");
        service.displayAllPoliciesSortedByPremium();

        System.out.println("\n--- Iteration using keySet() ---");
        service.iteratePoliciesUsingKeySet();

        System.out.println("\n--- Iteration using entrySet() ---");
        service.iteratePoliciesUsingEntrySet();

        System.out.println("\n--- Iteration using forEach() ---");
        service.iteratePoliciesUsingForEach();

        System.out.println("\n--- Export customer-policy map ---");
        Map<String, List<String>> exported = service.exportCustomerPolicyMap();
        exported.forEach((customer, policies) -> {
            System.out.println(customer + " -> " + policies);
        });

        // Removing a policy
        service.removePolicy("C001", "P002");
        System.out.println("\nAfter removing policy P002 from Alice:");
        service.printCustomerDetails();

        // Removing a customer
        service.removeCustomer("C002");
        System.out.println("\nAfter removing customer Bob:");
        service.printCustomerDetails();
    }
}
