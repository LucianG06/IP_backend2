package com.deskbooking.deskbooking.service;

import com.deskbooking.deskbooking.dto.BookingDTO;
import com.deskbooking.deskbooking.exception.NoBookings;
import com.deskbooking.deskbooking.mapper.BookingMapper;
import com.deskbooking.deskbooking.model.Booking;
import com.deskbooking.deskbooking.model.Desk;
import com.deskbooking.deskbooking.model.User;
import com.deskbooking.deskbooking.repository.BookingRepository;
import com.deskbooking.deskbooking.repository.DeskRepository;
import com.deskbooking.deskbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DeskRepository deskRepository;
    private final BookingMapper bookingMapper;

    //    public Booking createBooking(Booking booking) {
//        return bookingRepository.save(booking);
//    }

    public Booking createBooking(BookingDTO bookingDTO) {
        Booking newBooking = new Booking();
        newBooking.setDeskId(deskRepository.findDeskByName(bookingDTO.getNameDesk()).getDeskId());
        newBooking.setUserId(userRepository.findByEmail(bookingDTO.getEmailUser()).getUserId());
        newBooking.setCheckIn(bookingDTO.getCheckIn());
        newBooking.setCheckOut(bookingDTO.getCheckOut());
        bookingRepository.save(newBooking);
        return newBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<BookingDTO> getAllBookigsByUserEmail(String userEmail) throws NoBookings {
        User user = userRepository.findByEmail(userEmail);
        return bookingRepository.findAllByUserId(user.getUserId()).stream().filter(booking -> booking.getCanceled() == 0).map(booking -> {
            return bookingMapper.toBookingDTO(userRepository.findById(booking.getUserId()).get(), deskRepository.findById(booking.getDeskId()).get(), booking);
        }).collect(Collectors.toList());
    }

    public List<BookingDTO> getAllBookingsByDesk(String deskName, LocalDateTime date) {
        Desk desk = deskRepository.findDeskByName(deskName);
        return showBookingsForADate(date).stream().filter(booking -> booking.getDeskId().equals(desk.getDeskId()))
                .map(booking -> {
                    return bookingMapper.toBookingDTO(userRepository.findById(booking.getUserId()).get(), deskRepository.findById(booking.getDeskId()).get(), booking);
                })
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getAllBookingsByDeskWithoutCanceled(String deskName, LocalDateTime date) {
        Desk desk = deskRepository.findDeskByName(deskName);
        return showBookingsForADate(date).stream().filter(booking -> booking.getDeskId().equals(desk.getDeskId()))
                .filter(booking -> booking.getCanceled().equals(0))
                .map(booking -> {
                    return bookingMapper.toBookingDTO(userRepository.findById(booking.getUserId()).get(), deskRepository.findById(booking.getDeskId()).get(), booking);
                })
                .collect(Collectors.toList());
    }

    public void deleteBooking(BookingDTO bookingDTO) {
        User user = userRepository.findByEmail(bookingDTO.getEmailUser());
        System.out.println(user.getUserId());
        Desk desk = deskRepository.findDeskByName(bookingDTO.getNameDesk());
        System.out.println(desk.getDeskId());
        System.out.println(bookingDTO.getCheckIn());
        Booking bookingFound = bookingRepository.findAllByUserId(user.getUserId()).stream()
                .filter(booking -> (booking.getDeskId() == desk.getDeskId() && booking.getCanceled() == 0
                        && booking.getCheckIn().isEqual(bookingDTO.getCheckIn())
                        && booking.getCheckOut().isEqual(bookingDTO.getCheckOut()))).findFirst().get();
        bookingRepository.deleteById(bookingFound.getBookingId());
    }

    public List<Booking> showBookingsForADate(LocalDateTime date) {
        return getAllBookings().stream()
                .filter(booking -> booking.getCheckIn().getDayOfMonth() == date.getDayOfMonth() &&
                        booking.getCheckIn().getMonth() == date.getMonth() &&
                        booking.getCheckIn().getYear() == date.getYear()).collect(Collectors.toList());
    }

    public List<LocalDateTime> getAvailableHoursForDate(LocalDateTime date, String deskName) {
        List<LocalDateTime> checkInHours = getAllBookingsByDeskWithoutCanceled(deskName, date).stream()
                .sorted(Comparator.comparing(BookingDTO::getCheckIn))
                .map(BookingDTO::getCheckIn).toList();

        List<LocalDateTime> checkOutHours = getAllBookingsByDeskWithoutCanceled(deskName, date).stream()
                .sorted(Comparator.comparing(BookingDTO::getCheckOut))
                .map(BookingDTO::getCheckOut).toList();

        List<LocalDateTime> availableHours = new ArrayList<>();

        if (checkInHours.size() == 0) {
            for (int i = 7; i < 20; i++) {
                availableHours.add(date.withHour(i));
            }
        } else {
            if (checkInHours.size() > 1) {
                for (int i = 0; i < checkInHours.size() - 1; i++) {
                    for (int j = 7; j < 20; j++) {
                        if (j < checkInHours.get(0).getHour() || (j >= checkOutHours.get(i).getHour()
                                && j < checkInHours.get(i + 1).getHour())
                                || (j >= checkOutHours.get(checkOutHours.size() - 1).getHour())) {
                            if (!availableHours.contains(date.withHour(j))) {
                                availableHours.add(date.withHour(j));
                            }
                        }
                    }
                }
            } else {
                for (int j = 7; j < 20; j++) {
                    if (j < checkInHours.get(0).getHour() || (j >= checkOutHours.get(0).getHour())) {
                        availableHours.add(date.withHour(j));
                    }
                }
            }
        }
//        try {
//            if (availableHours.size() == 0)
//                throw new DeskCompletelyBooked("Desk is already booked for this day: " + date.getDayOfMonth() + "-" + date.getMonth() + "-" + date.getYear());
//        } catch (DeskCompletelyBooked e) {
//            e.printStackTrace();
//        }
        return availableHours;
    }

    public void updateBooking(BookingDTO bookingDTO, LocalDateTime checkIn, LocalDateTime checkOut){
        User user = userRepository.findByEmail(bookingDTO.getEmailUser());
        System.out.println(user.getUserId());
        Desk desk = deskRepository.findDeskByName(bookingDTO.getNameDesk());
        System.out.println(desk.getDeskId());
        System.out.println(bookingDTO.getCheckIn());
        Booking bookingFound = bookingRepository.findAllByUserId(user.getUserId()).stream()
                .filter(booking -> (booking.getDeskId() == desk.getDeskId() && booking.getCanceled() == 0
                        && booking.getCheckIn().isEqual(bookingDTO.getCheckIn())
                        && booking.getCheckOut().isEqual(bookingDTO.getCheckOut()))).findFirst().get();
        bookingRepository.updateById(bookingFound.getBookingId(), checkIn, checkOut);
    }
}
