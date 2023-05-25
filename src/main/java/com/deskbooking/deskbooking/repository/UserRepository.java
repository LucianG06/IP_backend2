package com.deskbooking.deskbooking.repository;

import com.deskbooking.deskbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByFirstNameAndLastName(String firstName, String lastName);
}
