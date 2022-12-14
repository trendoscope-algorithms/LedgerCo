package com.au.fabric.commands.impl;

import com.au.fabric.commands.Command;
import com.au.fabric.records.LoanRecord;
import com.au.fabric.records.LoanRepaymentTerm;
import com.au.fabric.scanner.Ledger;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BalanceCommand implements Command {
    private final String bankName;
    private final String borrowerName;
    private final int emiNumber;

    public BalanceCommand(String bankName, String borrowerName, int emiNumber) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.emiNumber = emiNumber;
    }

    public BalanceCommand(String[] arguments){
        this(arguments[1], arguments[2], Integer.parseInt(arguments[3]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BalanceCommand)) return false;
        BalanceCommand that = (BalanceCommand) o;
        return emiNumber == that.emiNumber && bankName.equals(that.bankName) && borrowerName.equals(that.borrowerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName, emiNumber);
    }

    @Override
    public void executeCommand(Ledger ledger) {
        Map<String, LoanRecord> loanRecordsMap = ledger.getLoanRecords();
        String key = this.bankName+":"+this.borrowerName;
        LoanRecord currentRecord = loanRecordsMap.get(key);
        List<LoanRepaymentTerm> repaymentTermList = currentRecord.getRepaymentTerms();
        LoanRepaymentTerm repaymentTerm = repaymentTermList.get(this.emiNumber);
        float balanceAfter = repaymentTerm.getBalanceAfter();
        int repaymentsMade = (int) (currentRecord.getTotalRepayments() - balanceAfter);
        int remainingRepayments = (int) Math.ceil(balanceAfter/currentRecord.getEmi());
        System.out.println(this.bankName + " "+this.borrowerName + " "+repaymentsMade + " "+remainingRepayments);
    }
}
