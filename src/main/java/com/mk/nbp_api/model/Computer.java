package com.mk.nbp_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "komputery")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "computer_id")
    private Long computerId;

    @Column(name = "nazwa")
    private String computer_name;

    @Column(name = "data_ksiegowania")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postingDate;

    @Column(name = "koszt_USD")
    private Double usdCost;

    @Column(name = "koszt_PLN")
    private Double plnCost;


}
