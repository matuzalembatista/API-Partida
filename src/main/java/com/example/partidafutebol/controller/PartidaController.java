package com.example.partidafutebol.controller;

import com.example.partidafutebol.dto.PartidaDto;
import com.example.partidafutebol.model.Partida;
import com.example.partidafutebol.service.PartidaService;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @GetMapping
    public List<PartidaDto> listar(){
        return partidaService.listar();
    }

    @GetMapping("/estadio/{estadio}")
    public List<Partida> listarTesteEstadio(@PathVariable String estadio){
        return partidaService.listarEstadio(estadio);
    }

    @GetMapping("/zeroGol")
    public List<Partida> listarZeroGolsClube(){
        return partidaService.listarZeroGols();
    }

    @GetMapping("/clubeMandante/{clubeMandante}")
    public List<Partida> listarClubeMandante(@PathVariable String clubeMandante){
        return partidaService.listarClubeMandante(clubeMandante);
    }

    @GetMapping("/clubeVisitante/{clubeVisitante}")
    public List<Partida> listarClubeVisitante(@PathVariable String clubeVisitante){
        return partidaService.listarClubeVisitante(clubeVisitante);
    }

    @GetMapping("/clube/{clube}")
    public List<Partida> listClube(@PathVariable String clube){
        return partidaService.listarClube(clube);
    }

    @GetMapping("/goleada")
    public List<Partida> listGoleada(){
        return partidaService.listarGoleada();
    }





    @PostMapping
    public ResponseEntity<Partida> salvar(@RequestBody @Valid PartidaDto partidaDto){
        Partida novaPartida = partidaService.salvar(partidaDto);
        return new ResponseEntity<>(novaPartida, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> alterar(@RequestBody PartidaDto partidaDto, @PathVariable Integer id ){
        Partida partidaAlterada = partidaService.alterar(partidaDto, id);
        return ResponseEntity.ok(partidaAlterada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        partidaService.deletar(id);
        return  ResponseEntity.noContent().build();
    }



}
