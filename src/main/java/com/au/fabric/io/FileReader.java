package com.au.fabric.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static List<String> readFile(String filename) throws FileNotFoundException {
        List<String> commands = new LinkedList<>();
        FileInputStream fileInputStream=new FileInputStream(filename);
        Scanner scanner=new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            commands.add(scanner.nextLine());
        }
        return commands;
    }
}
