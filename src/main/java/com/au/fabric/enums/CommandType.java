package com.au.fabric.enums;

public enum CommandType{
    LOAN,
    PAYMENT,
    BALANCE,
    NONE;
    public boolean validate(int arguments){
        return this == CommandType.BALANCE? arguments == 4 :
                this == CommandType.PAYMENT? arguments == 5 :
                        this == CommandType.LOAN? arguments == 6 : false;
    }
}
