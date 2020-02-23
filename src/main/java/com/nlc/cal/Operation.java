package com.nlc.cal;

public enum Operation {
    ADD ("ADD"),PLUS ("PLUS"),
    SUBTRACT ("SUBTRACT"),MINUS("MINUS"),LESS("LESS"),
    MULTIPLY("MULTIPLY"), TIMES("TIMES"),
    DIVIDE("DIVIDE"), OVER("OVER");

    private final String value;

    Operation(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}