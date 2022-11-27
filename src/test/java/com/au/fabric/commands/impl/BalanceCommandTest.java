package com.au.fabric.commands.impl;

import com.au.fabric.records.LoanRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceCommandTest {
    private String command1 = "BALANCE ABC John 10";
    private String command2 = "BALANCE ABC John 20";
    private String command3 = "BALANCE ABC Doe 20";
    private String command4 = "BALANCE XYZ Doe 20";
    BalanceCommand balanceCommand1, balanceCommand1Copy, balanceCommand2, balanceCommand3, balanceCommand4;
    @BeforeEach
    void setUp() {
        balanceCommand1 = new BalanceCommand(command1.split(" "));
        balanceCommand1Copy = new BalanceCommand(command1.split(" "));

        balanceCommand2 = new BalanceCommand(command2.split(" "));
        balanceCommand3 = new BalanceCommand(command3.split(" "));
        balanceCommand4 = new BalanceCommand(command4.split(" "));
    }

    @Test
    void testEquals() {
        assertEquals(balanceCommand1, balanceCommand1Copy);
        assertEquals(balanceCommand1, balanceCommand1);
        assertFalse(balanceCommand1.equals("Test"));
        assertNotEquals(balanceCommand1, balanceCommand2);
        assertNotEquals(balanceCommand2, balanceCommand3);
        assertNotEquals(balanceCommand2, balanceCommand4);
        assertNotEquals(balanceCommand3, balanceCommand4);
    }

    @Test
    void testHashCode() {
        assertEquals(balanceCommand1.hashCode(), balanceCommand1Copy.hashCode());
    }
}
