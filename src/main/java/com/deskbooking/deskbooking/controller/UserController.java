package com.deskbooking.deskbooking.controller;

import com.deskbooking.deskbooking.exception.NoUserFound;
import com.deskbooking.deskbooking.exception.WrongCredentials;
import com.deskbooking.deskbooking.model.AuthRequest;
import com.deskbooking.deskbooking.service.UserService;
import com.deskbooking.deskbooking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import com.deskbooking.deskbooking.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String createUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/user/getById/{userId}")
    public User findUserById(@PathVariable Integer userId) throws NoUserFound {
        return userService.findUserById(userId);
    }

    @GetMapping("/user/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/auth")
    public String generateAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (Exception e) {
            throw new WrongCredentials("Incorrect email or password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }

    @GetMapping("/user/getManagers")
    public List<String> getManagers(){
        return userService.getManagers();
    }

    @GetMapping("/user/getIdForManager")
    public Integer getIdForManager(@RequestParam String firstName,@RequestParam String lastName){
        return userService.getIdForManager(firstName,lastName);
    }
}
