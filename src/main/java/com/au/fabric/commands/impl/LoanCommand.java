package com.au.fabric.commands.impl;

import com.au.fabric.commands.Command;
import com.au.fabric.exceptions.InvalidDataException;
import com.au.fabric.records.LoanRecord;
import com.au.fabric.scanner.Ledger;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoanCommand implements Command {
    private String bankName;
    private String borrowerName;
    private float principal;
    private int years;
    private float rateOfInterest;


    public LoanCommand(String bank, String borrower, float principal, int years, float rateOfInterest) {
        this.bankName = bank;
        this.borrowerName = borrower;
        this.principal = principal;
        this.years = years;
        this.rateOfInterest = rateOfInterest;
    }

    public LoanCommand(String[] args){
        this(args[1], args[2], Float.parseFloat(args[3]), Integer.parseInt(args[4]), Float.parseFloat(args[5]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanCommand)) return false;
        LoanCommand loanDTO = (LoanCommand) o;
        return bankName.equals(loanDTO.bankName) && borrowerName.equals(loanDTO.borrowerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName);
    }

    @Override
    public void executeCommand(Ledger ledger) throws InvalidDataException {
        Map<String,LoanRecord> loanRecordMap = ledger.getLoanRecords();
        LoanRecord newRecord = new LoanRecord(this.bankName, this.borrowerName, this.principal, this.years, this.rateOfInterest);
        if(!loanRecordMap.values().contains(newRecord)){
            String key = this.bankName+':'+this.borrowerName;
            newRecord.calculateRepayments();
            loanRecordMap.put(key, newRecord);
        }else{
            throw new InvalidDataException("Loan Record for given bank and borrower already exist. Cannot add another loan");
        }
    }
}
