package com.au.fabric.records;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanRepaymentTermTest {
    private LoanRepaymentTerm repaymentTerm;
    @BeforeEach
    void setUp() {
        repaymentTerm = new LoanRepaymentTerm(1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstalmentNumber() {
        assertEquals(repaymentTerm.getInstalmentNumber(), 1);
    }

    @Test
    void addRepayment() {
        LoanRepayment repayment = new LoanRepayment(1, false, 10000, 1000);
        repaymentTerm.addRepayment(repayment);
        assertEquals(repaymentTerm.getBalanceAfter(), 9000);
        assertEquals(repaymentTerm.getRepayments().size(), 1);
    }

    @Test
    void addMultipleRepayment() {
        LoanRepayment repayment1 = new LoanRepayment(1, false, 10000, 1000);
        LoanRepayment repayment2 = new LoanRepayment(1, true, 10000, 1000);
        repaymentTerm.addRepayment(repayment1);
        repaymentTerm.addRepayment(repayment2);
        assertEquals(repaymentTerm.getBalanceAfter(), 9000);
        assertEquals(repaymentTerm.getRepayments().size(), 2);
    }
}
