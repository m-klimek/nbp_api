package com.mk.nbp_api.model.computer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "komputery")
@JacksonXmlRootElement
public class Computer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "komputer_id")
    @JsonIgnore
    private Long computerId;

    @Column(name = "nazwa")
    @JacksonXmlProperty(localName = "nazwa")
    private String computerName;

    @Column(name = "data_ksiegowania")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JacksonXmlProperty(localName = "data_ksiegowania")
    private LocalDate postingDate;

    @Column(name = "koszt_USD", precision = 7)
    @JacksonXmlProperty(localName = "koszt_USD")
    private Double usdCost;

    @Column(name = "koszt_PLN", precision = 7)
    @JacksonXmlProperty(localName = "koszt_PLN")
    private Double plnCost;


}
