package com.au.fabric.scanner;

import com.au.fabric.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {
    private Ledger ledger;
    private static final List<String> COMMANDS = new ArrayList<>(
            Arrays.asList(
                    "LOAN IDIDI Dale 5000 1 6",
                    "LOAN MBI Harry 10000 3 7",
                    "Loan UON Shelly 15000 2 9",
                    "PAYMENT IDIDI Dale 1000 5",
                    "Payment MBI Harry 5000 10",
                    "PAYMENT UON Shelly 7000 12",
                    "BALANCE IDIDI Dale 3",
                    "Balance IDIDI Dale 6",
                    "BALANCE UON Shelly 12",
                    "BALANCE MBI Harry 12"
                    )
    );

    private static final List<String> COMMANDS_INVALID_LOAN = new ArrayList<>(
            Arrays.asList(
                    "LOAN IDIDI Dale 5000 1 6",
                    "LOAN MBI Harry 10000 3 7",
                    "LOAN UON Shelly 15000 2 9 4",
                    "PAYMENT IDIDI Dale 1000 5",
                    "PAYMENT MBI Harry 5000 10",
                    "PAYMENT UON Shelly 7000 12",
                    "BALANCE IDIDI Dale 3",
                    "BALANCE IDIDI Dale 6",
                    "BALANCE UON Shelly 12",
                    "BALANCE MBI Harry 12"
            )
    );

    private static final List<String> COMMANDS_INVALID_BALANCE = new ArrayList<>(
            Arrays.asList(
                    "LOAN IDIDI Dale 5000 1 6",
                    "LOAN MBI Harry 10000 3 7",
                    "LOAN UON Shelly 15000 2 9",
                    "PAYMENT IDIDI Dale 1000 5",
                    "PAYMENT MBI Harry 5000 10",
                    "PAYMENT UON Shelly 7000 12",
                    "BALANCE IDIDI Dale 3",
                    "BALANCE IDIDI Dale 6",
                    "BALANCE UON Shelly 12",
                    "BALANCE MBI Harry"
            )
    );

    private static final List<String> COMMANDS_INVALID_PAYMENT = new ArrayList<>(
            Arrays.asList(
                    "LOAN IDIDI Dale 5000 1 6",
                    "LOAN MBI Harry 10000 3 7",
                    "LOAN UON Shelly 15000 2 9",
                    "PAYMENT IDIDI Dale 1000 5",
                    "PAYMENT MBI Harry 5000 10",
                    "PAYMENT UON Shelly 7000 12 55",
                    "BALANCE IDIDI Dale 3",
                    "BALANCE IDIDI Dale 6",
                    "BALANCE UON Shelly 12",
                    "BALANCE MBI Harry 12"
            )
    );

    private static final List<String> COMMANDS_INVALID_COMMAND = new ArrayList<>(
            Arrays.asList(
                    "TEST 123 123 123 ttt"
            )
    );

    @Test
    void validateCommands() {
        ledger = new Ledger(COMMANDS);
        assertTrue(ledger.validateCommands());
    }

    @Test
    void validateCommandsInvalidLoan() {
        ledger = new Ledger(COMMANDS_INVALID_LOAN);
        assertFalse(ledger.validateCommands());
    }

    @Test
    void validateCommandsInvalidBalance() {
        ledger = new Ledger(COMMANDS_INVALID_BALANCE);
        assertFalse(ledger.validateCommands());
    }

    @Test
    void validateCommandsInvalidPayment() {
        ledger = new Ledger(COMMANDS_INVALID_PAYMENT);
        assertFalse(ledger.validateCommands());
    }

    @Test
    void validateCommandsInvalidCommand() {
        ledger = new Ledger(COMMANDS_INVALID_COMMAND);
        assertFalse(ledger.validateCommands());
    }

    @Test
    void executeCommands() {
        ledger = new Ledger(COMMANDS);
        try {
            ledger.execute();
            assertTrue(true);
        } catch (InvalidDataException e) {
            assertTrue(false);
        }
    }

    @Test
    void executeCommandsInvalidCommand() {
        ledger = new Ledger(COMMANDS_INVALID_COMMAND);
        try {
            ledger.execute();
            assertTrue(false);
        } catch (InvalidDataException e) {
            assertTrue(true);
        }
    }
}
