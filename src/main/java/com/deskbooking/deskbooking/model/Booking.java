package com.deskbooking.deskbooking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer deskId;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkIn;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkOut;

    @NotNull
    private Integer canceled = 0;

    public Booking(Integer userId, Integer deskId, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.userId = userId;
        this.deskId = deskId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
