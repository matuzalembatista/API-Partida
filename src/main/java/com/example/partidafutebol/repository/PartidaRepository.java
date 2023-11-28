package com.example.partidafutebol.repository;

import com.example.partidafutebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {

    public List<Partida> findByEstadio(String estadio);

    public List<Partida> findByClubeMandante(String clubeMandante);

    public List<Partida> findByClubeVisitante(String clubeVisitante);

    @Query("from Partida  where clubeMandante like concat(?1,'%') or clubeVisitante like concat(?1,'%')")
    public List<Partida> findByClubeMandanteOrClubeVisitante(String clube);

    @Query("select u from Partida u where u.golMandante = 0 and u.golVisitante = 0")
    public List<Partida> findByGolMandanteOrGolVisitante();

    @Query("from Partida  where abs(golMandante - golVisitante) >= 3 or abs(golVisitante - golMandante) > 3")
    public List<Partida> findByGolMandanteAndGolVisitante();


    @Query(value = "SELECT *\n" +
            "FROM partida\n" +
            "WHERE data_evento BETWEEN ?1 AND ?2\n" +
            "AND ((clube_mandante = ?3 or clube_visitante = ?3)\n" +
            "OR (clube_mandante = ?4 or clube_visitante = ?4));", nativeQuery = true)
    public List<Partida> findByClubeMandanteOrClubeVisitanteBetweenDates(String primeiraData, String segundaData, String clubeUm, String clubeDois );



    @Query(value = "SELECT * FROM Partida\n" +
            "WHERE estadio = :estadio AND data_evento = :dataEvento", nativeQuery = true)
    public List<Partida> findByEstadioAndDataEventoQuery(@Param("estadio")String estadio, @Param("dataEvento") String dataEvento);



/*
    @Query(value = "SELECT * FROM Partida\n" +
            "WHERE estadio = ?1 AND data_evento = ?2", nativeQuery = true)
    public List<Partida> findByEstadioAndDataEventoQuery(String estadio,  String dataEvento);

*/

}
