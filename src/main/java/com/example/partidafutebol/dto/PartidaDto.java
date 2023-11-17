package com.example.partidafutebol.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class PartidaDto {

    private String clubeMandante;
    private String clubeVisitante;
    private String estadio;
    private int golMandante;
    private int golVisitante;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEvento;

}
