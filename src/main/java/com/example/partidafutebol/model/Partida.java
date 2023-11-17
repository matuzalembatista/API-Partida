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

    @NotEmpty(message = "Clube em branco, Insira o nome do clube mandante")
    @Column
    private String clubeMandante;

    @NotEmpty(message = "Clube em branco, Insira o nome do clube visitante")
    @Column
    private String clubeVisitante ;

    @NotEmpty(message = "Estadio em branco, Insira o nome do estadio")
    @Column
    private String estadio;



    @Column
    @Min(0)
    private int golMandante;

    @Column
    @Min(0)
    private int golVisitante;

    @NotNull
    @Column
    @PastOrPresent
    private LocalDate dataEvento;

    @NotNull
    @Column
    @PastOrPresent
    private LocalTime horaEvento;






}
