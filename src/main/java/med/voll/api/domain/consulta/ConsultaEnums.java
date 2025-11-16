package med.voll.api.domain.consulta;

import lombok.Getter;

@Getter
public enum ConsultaEnums {
    MEDIC_SCHEDULED_MSG("Médico já possui consulta agendada."),
    OUT_OF_HOURS_MSG("Consulta fora do horário de funcionamento");

    private final String message;

    ConsultaEnums(String message) {
        this.message = message;
    }

}

