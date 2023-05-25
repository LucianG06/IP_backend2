package com.deskbooking.deskbooking.repository;

import com.deskbooking.deskbooking.dto.DeskDTO;
import com.deskbooking.deskbooking.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeskRepository extends JpaRepository<Desk,Integer> {
    Desk findDeskByName(String name);
}
