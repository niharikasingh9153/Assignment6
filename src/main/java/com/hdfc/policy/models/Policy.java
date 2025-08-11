package com.hdfc.policy.models;

public abstract class Policy {
    private final String policyId;
    protected double premiumAmount;
    protected int termYears;

    public Policy(String policyId, double premiumAmount, int termYears) {
        this.policyId = policyId;
        this.premiumAmount = premiumAmount;
        this.termYears = termYears;
    }

    public String getPolicyId() {
        return policyId;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public int getTermYears() {
        return termYears;
    }

    public abstract double calculateMaturityAmount();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy)) return false;
        Policy p = (Policy) o;
        return policyId.equals(p.policyId);
    }

    @Override
    public int hashCode() {
        return policyId.hashCode();
    }

    public static void printWelcomeMessage() {
        System.out.println("Welcome to HDFC Life Insurance Policy Portal");
    }

    public void printDetails() {
        System.out.println(policyId + " | " + premiumAmount + " | " + termYears);
    }
}
