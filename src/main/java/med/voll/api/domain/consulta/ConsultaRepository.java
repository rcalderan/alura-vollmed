package med.voll.api.domain.consulta;

import med.voll.api.domain.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDate(Long medicoId, LocalDateTime date);

    @Query("""
            SELECT c FROM Consulta c
            WHERE ( c.medico.id = :id OR c.paciente.id = :id)
            ORDER BY c.date
            """)
    Page<Consulta> getByPerfil(Long id, Pageable paginacao);
}
