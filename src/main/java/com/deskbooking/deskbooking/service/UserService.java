package com.deskbooking.deskbooking.service;

import com.deskbooking.deskbooking.exception.NoUserFound;
import lombok.*;
import com.deskbooking.deskbooking.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.deskbooking.deskbooking.repository.UserRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        if (!user.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}"))
            return "The email format is incorrect!";
        if (user.getLastName().length() == 0 || user.getFirstName().length() == 0)
            return "You must fill the field!";
        if (!user.getLastName().matches("[a-zA-Z]+"))
            return "The last name must contain only letters!";
        if (!user.getFirstName().matches("[a-zA-Z]+"))
            return "The first name must contain only letters!";
        if (user.getPhoneNumber().length() != 10)
            return "The phone number must have 10 digits!";
        if (!user.getPhoneNumber().matches("[0-9]+"))
            return "The phone number must contain only digits!";
        if (user.getPassword().length() <= 5)
            return "The password is too weak. It has to contain at least 6 characters.";
        if (userRepository.findByEmail(user.getEmail()) != null)
            return "An account already exits with this email!";
        else
            {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return "The account was created!";
            }
    }

    public User findUserById(Integer userId) throws NoUserFound {
        return userRepository.findById(userId).orElseThrow(NoUserFound::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getManagers(){
        return getAllUsers().stream()
                .filter(user -> user.getIsManager() == 1)
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .collect(Collectors.toList());
    }

    public Integer getIdForManager(String firstName, String lastName){
        return userRepository.findByFirstNameAndLastName(firstName,lastName).getUserId();
    }
}
