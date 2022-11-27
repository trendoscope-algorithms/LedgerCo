package com.au.fabric;

import com.au.fabric.exceptions.InvalidArgumentsException;
import com.au.fabric.exceptions.InvalidDataException;
import com.au.fabric.io.FileReader;
import com.au.fabric.scanner.Ledger;

import java.io.FileNotFoundException;
import java.util.List;

public class EntryPoint {
    private List<String> commands;
    private Ledger ledger;
    public EntryPoint(String filename) throws FileNotFoundException {
        this.commands = FileReader.readFile(filename);
        this.ledger = new Ledger(commands);
    }

    public void execute() throws InvalidDataException {
        if(!ledger.validateCommands()) {
            throw new InvalidDataException("Invalid input");
        }
        ledger.execute();
    }
    public static void main(String[] args) throws FileNotFoundException, InvalidDataException {
        if(args.length == 1){
            EntryPoint entryPoint = new EntryPoint(args[0]);
            entryPoint.execute();
        }
        else{
            throw new InvalidArgumentsException("Invalid number of input arguments");
        }
    }
}
