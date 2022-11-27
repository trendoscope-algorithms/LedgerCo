package com.au.fabric;

import com.au.fabric.exceptions.InvalidArgumentsException;
import com.au.fabric.exceptions.InvalidDataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EntryPointTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mainWithZeroArguments() {
        try{
            EntryPoint.main(new String[0]);
            assertFalse(true);
        }catch (InvalidArgumentsException e){
            assertTrue(true);
        }catch (Exception e){
            assertFalse(true);
        }
    }

    @Test
    void applicationWithValidInput1() throws FileNotFoundException, InvalidDataException {
        EntryPoint.main(new String[]{"inputs/input1.txt"});
    }

    @Test
    void applicationWithValidInput2() throws FileNotFoundException, InvalidDataException {
        EntryPoint.main(new String[]{"inputs/input2.txt"});
    }

    @Test
    void applicationWithInvalidInput() {
        try{
            EntryPoint.main(new String[]{"inputs/invalid_input1.txt"});
            assertFalse(true);
        }catch (InvalidDataException e){
            assertTrue(true);
        }catch (Exception e){
            assertFalse(true);
        }
    }

    @Test
    void applicationWithTwoArgument() {
        try{
            EntryPoint.main(new String[]{"inputs/input1.txt", "Wrong Second input"});
            assertFalse(true);
        }catch (InvalidArgumentsException e){
            assertTrue(true);
        }catch (Exception e){
            assertFalse(true);
        }
    }
}
