package com.deskbooking.deskbooking.exception;

public class NoUserFound extends Exception{
    public NoUserFound(){
        super("The user does not exist");
    }
}
