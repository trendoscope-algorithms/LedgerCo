package com.au.fabric.records;

import java.util.*;

public class LoanRecord {
    public static final int REPAYMENTS_PER_YEAR = 12;
    private String bankName;
    private String borrowerName;
    private float principal;
    private int years;
    private float rateOfInterest;
    private float totalRepayments;
    private float emi;
    private int numberOfRepayments;
    private ArrayList<LoanRepaymentTerm> repaymentTerms;
    private HashMap<Integer, Float> bulkRepayments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRecord)) return false;
        LoanRecord that = (LoanRecord) o;
        return getBankName().equals(that.getBankName()) && getBorrowerName().equals(that.getBorrowerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBankName(), getBorrowerName());
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public float getPrincipal() {
        return principal;
    }

    public void setPrincipal(float principal) {
        this.principal = principal;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(float rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public float getTotalRepayments() {
        return totalRepayments;
    }

    public float getEmi() {
        return emi;
    }

    public int getNumberOfRepayments() {
        return numberOfRepayments;
    }

    public Map<Integer, Float> getBulkRepayments() {
        return bulkRepayments;
    }

    public Map<Integer, Float> addBulkRepayment(int instalmentNumber, float repayment){
        bulkRepayments.put(instalmentNumber, repayment);
        return bulkRepayments;
    }

    public List<LoanRepaymentTerm> getRepaymentTerms() {
        return this.repaymentTerms;
    }

    public LoanRecord(String bankName, String borrowerName, float principal, int years, float rateOfInterest){
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.rateOfInterest = rateOfInterest;
        this.years = years;
        this.numberOfRepayments = years*REPAYMENTS_PER_YEAR;
        bulkRepayments = new HashMap<>();
        repaymentTerms = new ArrayList<>();
        calculateEmi();
    }

    private void calculateEmi(){
        float interest = this.principal*this.rateOfInterest*this.years /100;
        this.totalRepayments = this.principal + interest;
        this.emi = (float) Math.ceil(this.totalRepayments/this.numberOfRepayments);
    }

    public void calculateRepayments(){
        repaymentTerms.clear();
        float balance = totalRepayments;
        int installmentNumber = 1;
        while(balance > 0){
            LoanRepaymentTerm term = new LoanRepaymentTerm(installmentNumber);
            LoanRepayment repayment = new LoanRepayment(installmentNumber, false, balance, emi);
            term.addRepayment(repayment);
            if(bulkRepayments.containsKey(installmentNumber)){
                float currentBulkRepayments = bulkRepayments.get(installmentNumber);
                LoanRepayment bulkRepayment = new LoanRepayment(installmentNumber, true, term.getBalanceAfter(), currentBulkRepayments);
                term.addRepayment(bulkRepayment);
            }
            repaymentTerms.add(term);
            balance = term.getBalanceAfter();
            installmentNumber++;
        }
    }
}
