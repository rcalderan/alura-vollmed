package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.consulta.ConsultaEnums;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exeption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static med.voll.api.domain.consulta.ConsultaEnums.MEDIC_SCHEDULED_MSG;


@Component
public class MedicScheduleValidator implements IConsultSchedule{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(DadosAgendamentoConsulta data) {
        var isScheduled = consultaRepository.existsByMedicoIdAndDate(data.idMedico(), data.date());
        if(isScheduled){
            throw new ValidationException(MEDIC_SCHEDULED_MSG.getMessage());
        }

    }
}
