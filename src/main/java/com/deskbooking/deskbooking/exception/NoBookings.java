package com.deskbooking.deskbooking.exception;

public class NoBookings extends Exception{
    public NoBookings(){
        super("No bookings could be found");
    }
}
