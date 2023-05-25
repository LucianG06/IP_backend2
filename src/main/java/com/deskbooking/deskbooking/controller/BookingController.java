package com.deskbooking.deskbooking.controller;

import com.deskbooking.deskbooking.dto.BookingDTO;
import com.deskbooking.deskbooking.exception.NoBookings;
import com.deskbooking.deskbooking.model.Booking;
import com.deskbooking.deskbooking.service.BookingService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookingController {
    private final BookingService bookingService;

//    @PostMapping("/create")
//    public Booking createBooking(@RequestBody Booking booking){
//        return bookingService.createBooking(booking);
//    }
    @PostMapping("/create")
    public Booking createBooking(Principal principal, @RequestBody BookingDTO bookingDTO){
        bookingDTO.setEmailUser(principal.getName());
        return bookingService.createBooking(bookingDTO);
    }

    @GetMapping("/getAll")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/getAllByUserEmail")
    public List<BookingDTO> getAllBookigsByUserEmail(Principal principal) throws NoBookings {
        return bookingService.getAllBookigsByUserEmail(principal.getName());
    }

    @GetMapping("/getAllByDesk")
    public List<BookingDTO> getAllBookigsByDesk(@RequestParam String deskName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return bookingService.getAllBookingsByDesk(deskName,date);
    }

    @GetMapping("/getAllByDeskWithoutCanceled")
    public List<BookingDTO> getAllBookigsByDeskWithoutCanceled(@RequestParam String deskName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return bookingService.getAllBookingsByDeskWithoutCanceled(deskName,date);
    }

    @GetMapping("/desksByDate")
    public List<Booking> getBookingsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date){
        return bookingService.showBookingsForADate(date);
    }

    @PostMapping ("/deleteBooking")
    public void deleteBookingById(Principal principal,@RequestBody BookingDTO bookingDTO){
        System.out.println(principal.getName());
        bookingDTO.setEmailUser(principal.getName());
        bookingService.deleteBooking(bookingDTO);
    }

    @GetMapping("/availableHours")
    public List<LocalDateTime> getAvailableHoursForADate(@RequestParam String deskName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return bookingService.getAvailableHoursForDate(date, deskName);
    }

    @PostMapping ("/updateBooking")
    public void updateBookingById(Principal principal,@RequestBody BookingDTO bookingDTO,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime checkIn,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime checkOut){
        System.out.println(principal.getName());
        bookingDTO.setEmailUser(principal.getName());
        bookingService.updateBooking(bookingDTO,checkIn,checkOut);
    }
}
