package com.deskbooking.deskbooking.dto;

import com.deskbooking.deskbooking.model.Booking;
import com.deskbooking.deskbooking.model.Desk;
import com.deskbooking.deskbooking.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String emailUser;
    private String nameDesk;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkIn; //14:00
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkOut;

    public BookingDTO(User user, Desk desk,Booking booking){
        this.emailUser = user.getEmail();
        this.nameDesk = desk.getName();
        this.checkIn = booking.getCheckIn();
        this.checkOut = booking.getCheckOut();
    }
}
