package com.deskbooking.deskbooking.mapper;

import com.deskbooking.deskbooking.dto.BookingDTO;
import com.deskbooking.deskbooking.dto.UserDTO;
import com.deskbooking.deskbooking.model.Booking;
import com.deskbooking.deskbooking.model.Desk;
import com.deskbooking.deskbooking.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO toBookingDTO(User user, Desk desk, Booking booking);
}
