package com.au.fabric.commands.impl;

import com.au.fabric.commands.Command;
import com.au.fabric.records.LoanRecord;
import com.au.fabric.scanner.Ledger;

import java.util.Map;
import java.util.Objects;

public class PaymentCommand implements Command {
    private String bankName;
    private String borrowerName;
    private float lumpsum;
    private int emiNumber;

    public PaymentCommand(String bankName, String borrowerName, float lumpsum, int emiNumber) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.lumpsum = lumpsum;
        this.emiNumber = emiNumber;
    }

    public PaymentCommand(String[] args){
        this(args[1], args[2], Float.parseFloat(args[3]), Integer.parseInt(args[4]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentCommand)) return false;
        PaymentCommand that = (PaymentCommand) o;
        return Float.compare(that.lumpsum, lumpsum) == 0 &&
                emiNumber == that.emiNumber && bankName.equals(that.bankName) &&
                borrowerName.equals(that.borrowerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName, lumpsum, emiNumber);
    }

    @Override
    public void executeCommand(Ledger ledger) {
        Map<String, LoanRecord> loanRecordsMap = ledger.getLoanRecords();
        String key = this.bankName+":"+this.borrowerName;
        LoanRecord currentRecord = loanRecordsMap.get(key);
        currentRecord.addBulkRepayment(this.emiNumber, this.lumpsum);
        currentRecord.calculateRepayments();
    }
}
