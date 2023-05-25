package com.deskbooking.deskbooking.dto;

import com.deskbooking.deskbooking.model.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer userId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public UserDTO(User user)
    {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
    }
}
