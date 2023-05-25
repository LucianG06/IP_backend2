//package com.deskbooking.deskbooking;
//
//import com.deskbooking.deskbooking.controller.BookingController;
//import com.deskbooking.deskbooking.filter.JwtFilter;
//import com.deskbooking.deskbooking.model.Booking;
//import com.deskbooking.deskbooking.security.SecurityConfig;
//import com.deskbooking.deskbooking.service.BookingService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.servlet.Filter;
//import java.util.Arrays;
//import java.sql.Date;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(BookingController.class)
//public class BookingControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookingService bookingService;
//
//    @MockBean
//    private JwtFilter jwtFilter;
//
//    @MockBean
//    private SecurityConfig securityConfig;
//
//    @MockBean(name = "springSecurityFilterChain")
//    private Filter filter;
//
//    @Test
//    public void itShouldAddBooking() throws Exception {
//        Booking booking = new Booking(1, 2, 3, new Date(11), new Date(22), 0);
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockMvc.perform(post("/booking/create")
//                        .content(objectMapper.writeValueAsString(booking))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void itShouldGetAllBookings() throws Exception {
//        Booking booking1 = new Booking(1, 2, 3, new Date(1), new Date(2), 0);
//        Booking booking2 = new Booking(2, 4, 1, new Date(4), new Date(5), 0);
//        List<Booking> allBookings = Arrays.asList(booking1, booking2);
//        given(bookingService.getAllBookings()).willReturn(allBookings);
//        mockMvc.perform(get("/booking/getAll")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkIn", Matchers.is(booking1.getCheckIn().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookingId", Matchers.is(booking2.getBookingId())));
//    }
//
//    @Test
//    public void itShouldGetBookingsByUser() throws Exception {
//        Booking booking1 = new Booking(2, 4, 1, new Date(4), new Date(5), 1);
//        Booking booking2 = new Booking(3, 4, 2, new Date(4), new Date(5), 0);
//        List<Booking> bookings = Arrays.asList(booking1, booking2);
//        given(bookingService.getAllBookigsByUser(4)).willReturn(bookings);
//        mockMvc.perform(get("/booking/getAllByUser/4")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkIn", Matchers.is(booking1.getCheckIn().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookingId", Matchers.is(booking2.getBookingId())));
//    }
//
//    @Test
//    public void itShouldGetBookingsByDesk() throws Exception {
//        Booking booking1 = new Booking(2, 4, 1, new Date(4), new Date(5), 1);
//        Booking booking2 = new Booking(3, 4, 1, new Date(4), new Date(5), 0);
//        List<Booking> bookings = Arrays.asList(booking1, booking2);
//        given(bookingService.getAllBookingsByDesk(1)).willReturn(bookings);
//        mockMvc.perform(get("/booking/getAllByDesk/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkIn", Matchers.is(booking1.getCheckIn().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookingId", Matchers.is(booking2.getBookingId())));
//    }
//
//    @Test
//    public void itShouldGetBookingsByDate() throws Exception {
//        Date checkInDate = new Date(4);
//        Booking booking1 = new Booking(2, 4, 1, checkInDate, new Date(5), 1);
//        Booking booking2 = new Booking(3, 4, 1, checkInDate, new Date(7), 0);
//        List<Booking> bookings = Arrays.asList(booking1, booking2);
//        given(bookingService.showBookingsForADate(checkInDate.toString())).willReturn(bookings);
//        mockMvc.perform(get("/booking/desksByDate?date=" + checkInDate)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkIn", Matchers.is(booking1.getCheckIn().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookingId", Matchers.is(booking2.getBookingId())));
//    }
//
//    @Test
//    public void itShouldDeleteBooking() throws Exception {
//        mockMvc.perform(put("/booking/deleteBooking/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}
