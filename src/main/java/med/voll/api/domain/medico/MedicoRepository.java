package med.voll.api.domain.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
            SELECT m from medicos m
            WHERE
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from consultas c
                where
                c.date = :dateTime
            )
            """)
    List<Medico> getRandomMedic(Especialidade especialidade, LocalDateTime dateTime);

//    @Query("""
//        SELECT m
//        FROM medicos m
//        LEFT JOIN m.consultas c
//        WHERE m.especialidade = :especialidade
//          AND (c IS NULL OR c.date <> :dateTime)
//        """)
//    List<Medico> getRandomMedic(Especialidade especialidade, LocalDateTime dateTime);
}
