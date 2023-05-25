package com.deskbooking.deskbooking.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deskId;

    @NotNull
    private String name;


}
