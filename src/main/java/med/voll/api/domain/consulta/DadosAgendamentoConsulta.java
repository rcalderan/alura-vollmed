package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        @NotNull
        @JsonAlias("medico_id")
        Long idMedico,

        @NotNull
        @JsonAlias("paciente_id")
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime date,

        Especialidade especialidade
) {
}
