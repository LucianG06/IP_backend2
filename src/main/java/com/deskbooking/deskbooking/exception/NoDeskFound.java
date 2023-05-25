package com.deskbooking.deskbooking.exception;

public class NoDeskFound extends Exception{
    public NoDeskFound(){
        super("The desk does not exist");
    }
}
