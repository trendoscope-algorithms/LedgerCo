package com.au.fabric.records;

import java.util.ArrayList;

public class LoanRepaymentTerm {
    private int instalmentNumber;
    private ArrayList<LoanRepayment> repayments;
    private float balanceAfter;

    public int getInstalmentNumber() {
        return instalmentNumber;
    }

    public ArrayList<LoanRepayment> getRepayments() {
        return repayments;
    }

    public float getBalanceAfter() {
        return balanceAfter;
    }

    public LoanRepaymentTerm(int instalmentNumber){
        this.instalmentNumber = instalmentNumber;
        repayments = new ArrayList<>();
    }

    public void addRepayment(LoanRepayment repayment){
        repayments.add(repayment);
        this.balanceAfter = repayment.getBalanceAfter();
    }
}
