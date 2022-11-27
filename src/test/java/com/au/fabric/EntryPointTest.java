package com.au.fabric;

import com.au.fabric.exceptions.InvalidArgumentsException;
import com.au.fabric.exceptions.InvalidDataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.util.List;

import static com.au.fabric.io.FileReader.readFile;
import static java.io.FileReader.*;
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
            fail();
        }catch (InvalidArgumentsException e){
            assertTrue(true);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void applicationWithValidInput1() throws FileNotFoundException, InvalidDataException {
        EntryPoint.main(new String[]{"inputs/input1.txt"});
        List<String> outputList = readFile("outputs/output1.txt");
        String output = String.join("\n", outputList);
        assertEquals(output.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void applicationWithValidInput2() throws FileNotFoundException, InvalidDataException {
        EntryPoint.main(new String[]{"inputs/input2.txt"});
        List<String> outputList = readFile("outputs/output2.txt");
        String output = String.join("\n", outputList);
        assertEquals(output.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void applicationWithValidInput3() throws FileNotFoundException, InvalidDataException {
        EntryPoint.main(new String[]{"inputs/input3.txt"});
        List<String> outputList = readFile("outputs/output3.txt");
        String output = String.join("\n", outputList);
        assertEquals(output.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void applicationWithInvalidInput() {
        try{
            EntryPoint.main(new String[]{"inputs/invalid_input1.txt"});
            fail();
        }catch (InvalidDataException e){
            assertTrue(true);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void applicationWithTwoArgument() {
        try{
            EntryPoint.main(new String[]{"inputs/input1.txt", "Wrong Second input"});
            fail();
        }catch (InvalidArgumentsException e){
            assertTrue(true);
        }catch (Exception e){
            fail();
        }
    }
}
