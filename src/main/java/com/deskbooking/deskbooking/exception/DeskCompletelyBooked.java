package com.deskbooking.deskbooking.exception;

public class DeskCompletelyBooked extends Exception{
    public DeskCompletelyBooked(String message){
        super(message);
    }
}
