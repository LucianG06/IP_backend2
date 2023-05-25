package com.deskbooking.deskbooking.mapper;

import com.deskbooking.deskbooking.dto.BookingDTO;
import com.deskbooking.deskbooking.model.Booking;
import com.deskbooking.deskbooking.model.Desk;
import com.deskbooking.deskbooking.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T16:47:39+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDTO toBookingDTO(User user, Desk desk, Booking booking) {
        if ( user == null && desk == null && booking == null ) {
            return null;
        }

        User user1 = null;
        user1 = user;
        Desk desk1 = null;
        desk1 = desk;
        Booking booking1 = null;
        booking1 = booking;

        BookingDTO bookingDTO = new BookingDTO( user1, desk1, booking1 );

        return bookingDTO;
    }
}
