package com.au.fabric.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void readFile() throws FileNotFoundException {
        FileReader reader = new FileReader();
        List<String> commands = FileReader.readFile("inputs/input1.txt");
        assertEquals(commands.size(), 6);
        assertNotNull(reader);
    }

    @Test
    void readFileNotFound() {
        try {
            List<String> commands = FileReader.readFile("randomText.txt");
            assertFalse(true);
            assertNull(commands);
        } catch (FileNotFoundException e) {
            assertTrue(true);
        }
    }
}
