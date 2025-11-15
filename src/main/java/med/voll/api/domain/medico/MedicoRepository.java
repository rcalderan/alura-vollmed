package med.voll.api.domain.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
            SELECT m from medicos m
            WHERE
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from consultas c
                where
                c.date = :data
            )
            order by rand()
            limit 1
            """)
    Medico getRandomMedic(Especialidade especialidade, LocalDateTime dateTime);
}
