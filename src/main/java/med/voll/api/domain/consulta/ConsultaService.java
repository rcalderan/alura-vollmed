package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exeption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private CosultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public ResponseEntity<?> agendar(DadosAgendamentoConsulta dto) {

        try{
            var medico = escolherMedico(dto);

            var paciente = pacienteRepository.findById(dto.idPaciente())
                    .orElseThrow(() -> new ValidationException("Paciente não encontrado"));

            Consulta consulta = new Consulta(null, medico, paciente, dto.date());
            medico.addConsulta(consulta);
            paciente.addConsulta(consulta);

            return ResponseEntity.ok(repository.save(consulta));

        }catch (ValidationException errorValidation){
            return ResponseEntity.badRequest().body(errorValidation.getMessage());
        }
    }


    /**
     * Try to get medico, if not found, try to get by Especialidade
     * @param dto dados da consulta
     * @return found medico
     */
    private Medico escolherMedico(DadosAgendamentoConsulta dto){
        if(dto.idMedico() != null){
            var medico = medicoRepository.findById(dto.idMedico());
            if(medico.isEmpty() ){
                if(dto.especialidade()==null){
                    throw new ValidationException("Especialidade deve ser informada");
                }
                return medicoRepository.getRandomMedic(dto.especialidade(), dto.date());
            }
            return medico.get();

        }
        throw new ValidationException("O campo Medico deve ser informado");
    }
}
