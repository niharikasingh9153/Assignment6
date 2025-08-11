package com.hdfc.policy.models;

public class HealthInsurancePolicy extends Policy {

    public HealthInsurancePolicy(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return premiumAmount * termYears * 1.2 + 5000;
    }
}
