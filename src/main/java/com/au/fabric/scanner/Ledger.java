package com.au.fabric.scanner;

import com.au.fabric.commands.impl.BalanceCommand;
import com.au.fabric.commands.Command;
import com.au.fabric.commands.impl.LoanCommand;
import com.au.fabric.commands.impl.PaymentCommand;
import com.au.fabric.enums.CommandType;
import com.au.fabric.exceptions.InvalidDataException;
import com.au.fabric.records.LoanRecord;

import java.util.*;

public class Ledger {
    private Map<String, LoanRecord> loanRecords;
    private List<String> commands;
    public Ledger(List<String> commands){
        loanRecords = new HashMap<>();
        this.commands = commands;
    }

    public Map<String, LoanRecord> getLoanRecords() {
        return loanRecords;
    }

    private static boolean validateCommand(String command){
        String[] subCommands = command.split(" ");
        String mainCommand = subCommands[0];
        boolean validCommand = Arrays.stream(CommandType.values()).
                anyMatch(commandType -> commandType.name().equalsIgnoreCase(mainCommand));

        CommandType type = validCommand ? CommandType.valueOf(mainCommand.toUpperCase(Locale.ROOT)) : CommandType.NONE;
        return type.validate(subCommands.length);
    }

    public boolean validateCommands(){
        boolean commandsValid = true;
        for(String command : commands){
            commandsValid = validateCommand(command);
            if(!commandsValid){
                break;
            }
        }
        return commandsValid;
    }

    private void executeSingleCommand(String command) throws InvalidDataException {
        String[] subCommands = command.split(" ");
        String mainCommand = subCommands[0];
        boolean validCommand = Arrays.stream(CommandType.values()).
                anyMatch(commandType -> commandType.name().equalsIgnoreCase(mainCommand));

        CommandType type = validCommand ? CommandType.valueOf(mainCommand.toUpperCase(Locale.ROOT)) : CommandType.NONE;
        Command ledgerCommand;
        switch (type){
            case LOAN:
                ledgerCommand = new LoanCommand(subCommands);
                break;
            case BALANCE:
                ledgerCommand = new BalanceCommand(subCommands);
                break;
            case PAYMENT:
                ledgerCommand = new PaymentCommand(subCommands);
                break;
            case NONE:
            default:
                throw new InvalidDataException("Invalid data");
        }
        ledgerCommand.executeCommand(this);
    }
    public void execute() throws InvalidDataException {
        for(String command: commands){
            executeSingleCommand(command);
        }

    }


}
