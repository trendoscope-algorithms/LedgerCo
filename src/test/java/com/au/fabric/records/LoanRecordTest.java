package com.au.fabric.records;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanRecordTest {
    private LoanRecord loanRecord;
    @BeforeEach
    void setUp() {
        loanRecord = new LoanRecord("ABC", "John", 10000, 5, 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEqualsAndHashCode() {
        LoanRecord second = new LoanRecord("ABC", "John", 5000, 3, 3);
        assertEquals(loanRecord, loanRecord);
        assertEquals(loanRecord, second);
        assertFalse(loanRecord.equals("Test"));
        assertEquals(loanRecord.hashCode(), second.hashCode());
        LoanRecord third = new LoanRecord("ABC", "Doe", 5000, 3, 3);
        assertNotEquals(loanRecord, third);
    }

    @Test
    void getBankName() {
        assertEquals(loanRecord.getBankName(), "ABC");
    }

    @Test
    void setBankName() {
        loanRecord.setBankName("DEF");
        assertEquals(loanRecord.getBankName(), "DEF");
    }

    @Test
    void getBorrowerName() {
        assertEquals(loanRecord.getBorrowerName(), "John");
    }

    @Test
    void setBorrowerName() {
        loanRecord.setBorrowerName("Doe");
        assertEquals(loanRecord.getBorrowerName(), "Doe");
    }

    @Test
    void getPrincipal() {
        assertEquals(loanRecord.getPrincipal(), 10000);
    }

    @Test
    void setPrincipal() {
        loanRecord.setPrincipal(5000);
        assertEquals(loanRecord.getPrincipal(), 5000);
    }

    @Test
    void getRateOfInterest() {
        assertEquals(loanRecord.getRateOfInterest(), 10);
    }

    @Test
    void setRateOfInterest() {
        loanRecord.setRateOfInterest(20);
        assertEquals(loanRecord.getRateOfInterest(), 20);
    }

    @Test
    void getRepaymentYears() {
        assertEquals(loanRecord.getYears(), 5);
    }

    @Test
    void setRepaymentYears() {
        loanRecord.setYears(10);
        assertEquals(loanRecord.getYears(), 10);
    }

    @Test
    void getTotalRepayments() {
        assertEquals(loanRecord.getTotalRepayments(), 15000);
    }

    @Test
    void getEmi() {
        assertEquals(loanRecord.getEmi(), 250);
    }

    @Test
    void getNumberOfRepayments() {
        assertEquals(loanRecord.getNumberOfRepayments(), 60);
    }

    @Test
    void getBulkRepayments() {
        assertEquals(loanRecord.getBulkRepayments().size(), 0);
    }

    @Test
    void addBulkRepayment() {
        loanRecord.addBulkRepayment(1, 1000);
        assertEquals(loanRecord.getBulkRepayments().size(), 1);
        assertEquals(loanRecord.getBulkRepayments().get(1), new Float(1000));
    }

    @Test
    void addBulkRepaymentMultipleForSameKey() {
        loanRecord.addBulkRepayment(1, 1000);
        loanRecord.addBulkRepayment(1, 2000);
        assertEquals(loanRecord.getBulkRepayments().size(), 1);
        assertEquals(loanRecord.getBulkRepayments().get(1), new Float(2000));
    }

    @Test
    void getRepayments() {
        assertNotNull(loanRecord.getRepaymentTerms());
        assertEquals(loanRecord.getRepaymentTerms().size(), 0);
    }


    @Test
    void testCalculateRepayments() {
        loanRecord.calculateRepayments();
        assertNotNull(loanRecord.getRepaymentTerms());
        assertEquals(loanRecord.getRepaymentTerms().size(), loanRecord.getNumberOfRepayments());
        int lastIndex = loanRecord.getRepaymentTerms().size()-1;
        assertEquals(loanRecord.getRepaymentTerms().get(lastIndex).getBalanceAfter(), 0);
        assertEquals(loanRecord.getRepaymentTerms().get(lastIndex).getInstalmentNumber(), loanRecord.getNumberOfRepayments());
    }

    @Test
    void testCalculateRepaymentsWithInstalments() {
        loanRecord.addBulkRepayment(40, 1000);
        loanRecord.calculateRepayments();
        assertNotNull(loanRecord.getRepaymentTerms());
        assertEquals(loanRecord.getRepaymentTerms().size(), 56);
        int lastIndex = loanRecord.getRepaymentTerms().size()-1;
        assertEquals(loanRecord.getRepaymentTerms().get(lastIndex).getBalanceAfter(), 0);
        assertEquals(loanRecord.getRepaymentTerms().get(lastIndex).getInstalmentNumber(), 56);
    }
}
