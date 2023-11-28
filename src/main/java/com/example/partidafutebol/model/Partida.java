package com.example.partidafutebol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
public class Partida {
    @Id
    @Column
    private int id;

    @NotBlank(message = "Campo não informado")
    @Column
    private String clubeMandante;

    @NotBlank(message = "Campo não informado")
    @Column
    private String clubeVisitante ;

    @NotBlank(message = "Campo não informado")
    @Column
    private String estadio;


    @Column
    @Min(0)
    private int golMandante;

    @Column
    @Min(0)
    private int golVisitante;

    //@NotBlank (message = "Campo não informado")
    @Column
    @PastOrPresent
    private LocalDate dataEvento;

    //@NotBlank (message = "Campo não informado")
    @Column
    @PastOrPresent
    private LocalTime horaEvento;






}
