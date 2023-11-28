package com.example.partidafutebol.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.partidafutebol.dto.PartidaDto;
import com.example.partidafutebol.model.Partida;
import com.example.partidafutebol.repository.PartidaRepository;
import jakarta.servlet.http.Part;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {
    @Autowired
    private PartidaRepository partidaRepository;

    public Partida salvar(PartidaDto partidaDto){
        Partida partida = new Partida();
        BeanUtils.copyProperties(partidaDto, partida);

        LocalTime horaInicio = partida.getHoraEvento();
        if(horaInicio.getHour() > 8 || horaInicio.getHour() < 22){
            //Evento datas
            LocalDate diaDoEvento = partida.getDataEvento();
            String diaDoEventoPlusDois = diaDoEvento.plusDays(2).toString();
            String diaDoEventoMinusDois = diaDoEvento.minusDays(2).toString();


            List<Partida> retornoValid = partidaRepository.findByClubeMandanteOrClubeVisitanteBetweenDates(
                    diaDoEventoMinusDois, diaDoEventoPlusDois, partida.getClubeMandante(), partida.getClubeVisitante());

            if(retornoValid.size() > 0){
                throw new Error("ERRO DA VALID DE CLUBES");
            }
                List<Partida> retornoValidEstadioAndDiaEvento = partidaRepository.findByEstadioAndDataEventoQuery(partidaDto.getEstadio(), diaDoEvento.toString());

            if(retornoValidEstadioAndDiaEvento.size() > 1){
                throw new Error("ERRO DA VALID DE ESTADIO");
            }


            System.out.println("retornoValidEstadioAndDiaEvento: " );
            partidaRepository.save(partida);

        }

        return partida;
    }




    public Partida alterar(PartidaDto partidaDto, Integer id){
        Partida partida = new Partida();
        BeanUtils.copyProperties(partidaDto, partida);

        partida.setId(id);
        partidaRepository.save(partida);
        return partida;
    }


    public void deletar(Integer id) {
        Partida pt = partidaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Partida não encontrada")
        );
        partidaRepository.deleteById(pt.getId());

    }

    public List<PartidaDto> listar(){
        List<Partida> listaDePartidas = partidaRepository.findAll();
        List<PartidaDto> listarDto = new ArrayList<>();
        for (Partida partida : listaDePartidas){
            PartidaDto partidaDto = new PartidaDto();

            // nomes iguais Model/DTO
            BeanUtils.copyProperties(partida, partidaDto);
            listarDto.add(partidaDto);
        }
        return listarDto;
    }

    public List<Partida> listarEstadio(String estadio) {
        return partidaRepository.findByEstadio(estadio);
    }

    public List<Partida> listarZeroGols(){
        return partidaRepository.findByGolMandanteOrGolVisitante();
    }

    public List<Partida> listarClubeMandante(String clubeMandante ){
        return partidaRepository.findByClubeMandante(clubeMandante);
    }

    public List<Partida> listarClubeVisitante(String clubeVisitante ){
        return partidaRepository.findByClubeVisitante(clubeVisitante);
    }

    public List<Partida> listarClube(String clube ){
        return partidaRepository.findByClubeMandanteOrClubeVisitante(clube);
    }

    public List<Partida> listarGoleada(){
        return partidaRepository.findByGolMandanteAndGolVisitante();
    }



}
