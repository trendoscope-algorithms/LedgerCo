package com.au.fabric.records;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanRepaymentTest {
    private LoanRepayment repayment;
    @BeforeEach
    void setUp() {
        repayment = new LoanRepayment(1, false, 10000, 1000);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstalmentNumber() {
        assertEquals(repayment.getInstalmentNumber(), 1);
    }

    @Test
    void setInstalmentNumber() {
        repayment.setInstalmentNumber(2);
        assertEquals(repayment.getInstalmentNumber(), 2);
    }

    @Test
    void isBulkPayment() {
        assertFalse(repayment.isBulkPayment());
    }

    @Test
    void setBulkPayment() {
        repayment.setBulkPayment(true);
        assertTrue(repayment.isBulkPayment());
    }

    @Test
    void getBalanceBefore() {
        assertEquals(repayment.getBalanceBefore(), 10000);
    }

    @Test
    void getEmiDeducted() {
        assertEquals(repayment.getEmiDeducted(), 1000);
    }

    @Test
    void getEmiDeductedAndBalanceWhenEmiLessThanBalance() {
        repayment = new LoanRepayment(1, false, 500, 1000);
        assertEquals(repayment.getEmiDeducted(), 500);
        assertEquals(repayment.getBalanceAfter(), 0);
    }

    @Test
    void getBalanceAfter() {
        assertEquals(repayment.getBalanceAfter(), 9000);
    }

    @Test
    void testToString() {
        assertNotNull(repayment.toString());
    }
}
