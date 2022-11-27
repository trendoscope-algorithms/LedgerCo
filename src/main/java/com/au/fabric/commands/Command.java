package com.au.fabric.commands;

import com.au.fabric.exceptions.InvalidDataException;
import com.au.fabric.scanner.Ledger;

public interface Command {
    public void executeCommand(Ledger ledger) throws InvalidDataException;
}
