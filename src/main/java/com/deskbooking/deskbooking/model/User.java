package com.deskbooking.deskbooking.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    @Column(unique=true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique=true)
    private String phoneNumber;

    @NotNull
    private Integer isManager=0;

    private Integer managerId;

//    @ElementCollection
//    private List<Integer> bookings;

//    public void addBooking(Integer booking){
//        this.bookings.add(booking);
//    }
}
