package com.example.partidafutebol.repository;

import com.example.partidafutebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Integer > {

    public List<Partida> findByEstadio(String estadio);

    public List<Partida> findByClubeMandante(String clubeMandante);

    public List<Partida> findByClubeVisitante(String clubeVisitante);

    @Query("from Partida  where clubeMandante like concat(?1,'%') or clubeVisitante like concat(?1,'%')")
    public List<Partida> findByClubeMandanteOrClubeVisitante(String clube);

    @Query("select u from Partida u where u.golMandante = 0 and u.golVisitante = 0")
    public List<Partida> findByGolMandanteOrGolVisitante();

    @Query("from Partida  where abs(golMandante - golVisitante) >= 3 or abs(golVisitante - golMandante) > 3")
    public List<Partida> findByGolMandanteAndGolVisitante();


}
