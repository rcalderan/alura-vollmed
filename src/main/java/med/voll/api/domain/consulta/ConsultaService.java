package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exeption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private CosultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta agendar(DadosAgendamentoConsulta dto){

        if(!medicoRepository.existsById(dto.idMedico())) {
            throw new ValidationException("Medico não encontrado");
        }
        if(!pacienteRepository.existsById(dto.idPaciente())) {
            throw new ValidationException("Paciente não encontrado");
        }

        var medico = medicoRepository.findById(dto.idMedico()).get();

        var paciente = pacienteRepository.findById(dto.idPaciente()).get();

        Consulta consulta = new Consulta(null, medico,paciente, dto.date());


        return repository.save(consulta);

    }
}
