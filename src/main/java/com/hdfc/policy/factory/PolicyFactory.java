package com.hdfc.policy.factory;

import com.hdfc.policy.models.*;

public class PolicyFactory {
    public static Policy createPolicy(String type, String policyId, double premium, int termYears) {
        switch (type.toLowerCase()) {
            case "life":
                return new LifeInsurancePolicy(policyId, premium, termYears);
            case "health":
                return new HealthInsurancePolicy(policyId, premium, termYears);
            case "term":
                return new TermInsurancePolicy(policyId, premium, termYears);
            default:
                throw new IllegalArgumentException("Invalid policy type: " + type);
        }
    }
}
