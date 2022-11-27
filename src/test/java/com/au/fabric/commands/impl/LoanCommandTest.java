package com.au.fabric.commands.impl;

import com.au.fabric.exceptions.InvalidDataException;
import com.au.fabric.scanner.Ledger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanCommandTest {
    private final String command1 = "LOAN ABC John 10000 5 3";
    private final String command2 = "LOAN ABC John 5000 3 5";
    private final String command3 = "LOAN ABC Doe 5000 3 5";
    private final String command4 = "LOAN XYZ Doe 5000 3 5";

    private LoanCommand loanCommand1, loanCommand1Copy, loanCommand2, loanCommand3, loanCommand4;
    @BeforeEach
    void setUp() {
        loanCommand1 = new LoanCommand(command1.split(" "));
        loanCommand1Copy = new LoanCommand(command1.split(" "));
        loanCommand2 = new LoanCommand(command2.split(" "));
        loanCommand3 = new LoanCommand(command3.split(" "));
        loanCommand4 = new LoanCommand(command4.split(" "));
    }
    @Test
    void testEquals() {
        assertEquals(loanCommand1, loanCommand1);
        assertEquals(loanCommand1, loanCommand1Copy);
        assertEquals(loanCommand1, loanCommand2);
        assertNotEquals(loanCommand1, loanCommand3);
        assertFalse(loanCommand1.equals("Test"));
        assertNotEquals(loanCommand1, loanCommand4);
        assertNotEquals(loanCommand3, loanCommand4);
    }

    @Test
    void testHashCode() {
        assertEquals(loanCommand1.hashCode(), loanCommand2.hashCode());
        assertNotEquals(loanCommand1.hashCode(), loanCommand3.hashCode());
    }

    @Test
    void executeCommand() {
        Ledger ledger = new Ledger(new ArrayList<>());
        try {
            loanCommand1.executeCommand(ledger);
            loanCommand2.executeCommand(ledger);
            assertTrue(false);
        } catch (InvalidDataException e) {
            assertTrue(true);
        }
    }
}
