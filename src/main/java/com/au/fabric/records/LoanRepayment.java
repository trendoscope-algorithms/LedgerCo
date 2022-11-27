package com.au.fabric.records;

public class LoanRepayment {
    private int instalmentNumber;
    private boolean isBulkPayment;
    private float balanceBefore;
    private float emiDeducted;
    private float balanceAfter;

    public int getInstalmentNumber() {
        return instalmentNumber;
    }

    public void setInstalmentNumber(int instalmentNumber) {
        this.instalmentNumber = instalmentNumber;
    }

    public boolean isBulkPayment() {
        return isBulkPayment;
    }

    public void setBulkPayment(boolean bulkPayment) {
        isBulkPayment = bulkPayment;
    }

    public float getBalanceBefore() {
        return balanceBefore;
    }

    public float getEmiDeducted() {
        return emiDeducted;
    }

    public float getBalanceAfter() {
        return balanceAfter;
    }

    public LoanRepayment(int instalmentNumber, boolean isBulkPayment, float balanceBefore, float emi){
        this.instalmentNumber = instalmentNumber;
        this.isBulkPayment = isBulkPayment;
        this.balanceBefore = balanceBefore;
        this.emiDeducted = Math.min(emi, balanceBefore);
        this.balanceAfter = balanceBefore - emiDeducted;
    }

    @Override
    public String toString() {
        return "LoanRepayment{" +
                "instalmentNumber=" + instalmentNumber +
                ", isBulkPayment=" + isBulkPayment +
                ", balanceBefore=" + balanceBefore +
                ", emiDeducted=" + emiDeducted +
                ", balanceAfter=" + balanceAfter +
                '}';
    }
}
