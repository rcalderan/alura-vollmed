package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exeption.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;


@Component
public class ClinicScheduleValidator implements IConsultSchedule{

    public void validate(DadosAgendamentoConsulta dto){
        var consultDate = dto.date();

         var early = consultDate.getHour() < 7;
         var later = consultDate.getHour() > 18;

         var sunday = consultDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);

         if(early || later || sunday){
             throw new ValidationException("Consulta fora do horário de funcionamento");
         }
    }
}
