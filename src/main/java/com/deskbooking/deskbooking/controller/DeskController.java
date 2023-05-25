package com.deskbooking.deskbooking.controller;

import com.deskbooking.deskbooking.dto.DeskDTO;
import com.deskbooking.deskbooking.service.DeskService;
import lombok.RequiredArgsConstructor;
import com.deskbooking.deskbooking.model.Desk;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("desk")
public class DeskController {
    private final DeskService deskService;

    @PostMapping("/create")
    public Desk createDesk(@RequestBody Desk desk){
        return deskService.createDesk(desk);
    }

    @GetMapping("/getById/{deskId}")
    public Desk findDeskById(@PathVariable Integer deskId){
        return deskService.findDeskById(deskId);
    }

    @GetMapping("/getAll")
//    public List<Desk> getAllDesks(Principal principal){
    public List<DeskDTO> getAllDesks(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date){
        return deskService.getAllDesks(date);
    }

    @GetMapping("/getByName")
    public Desk getDeskByName(@RequestParam String name){
        return deskService.findDeskByName(name);
    }
}
