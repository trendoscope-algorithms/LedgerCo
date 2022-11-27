package com.au.fabric.commands.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCommandTest {
    private final String command1 = "PAYMENT ABC John 1000 1";
    private final String command2 = "PAYMENT ABC John 1000 2";
    private final String command3 = "PAYMENT ABC Doe 1000 1";
    private final String command4 = "PAYMENT XYZ John 1000 1";
    private final String command5 = "PAYMENT XYZ John 5000 1";

    private PaymentCommand paymentCommand1, paymentCommand1Copy, paymentCommand2, paymentCommand3, paymentCommand4, paymentCommand5;
    @BeforeEach
    void setUp() {
        paymentCommand1 = new PaymentCommand(command1.split(" "));
        paymentCommand1Copy = new PaymentCommand(command1.split(" "));
        paymentCommand2 = new PaymentCommand(command2.split(" "));
        paymentCommand3 = new PaymentCommand(command3.split(" "));
        paymentCommand4 = new PaymentCommand(command4.split(" "));
        paymentCommand5 = new PaymentCommand(command5.split(" "));
    }
    @Test
    void testEquals() {
        assertEquals(paymentCommand1, paymentCommand1);
        assertEquals(paymentCommand1, paymentCommand1Copy);
        assertNotEquals(paymentCommand1, paymentCommand2);
        assertNotEquals(paymentCommand1, paymentCommand3);
        assertFalse(paymentCommand1.equals("Test"));
        assertNotEquals(paymentCommand1, paymentCommand4);
        assertNotEquals(paymentCommand3, paymentCommand4);
        assertNotEquals(paymentCommand4, paymentCommand5);
    }

    @Test
    void testHashCode() {
        assertEquals(paymentCommand1.hashCode(), paymentCommand1Copy.hashCode());
    }
}
