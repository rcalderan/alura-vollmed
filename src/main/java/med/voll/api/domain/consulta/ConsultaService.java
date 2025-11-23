package med.voll.api.domain.consulta;

import med.voll.api.domain.Perfil;
import med.voll.api.domain.consulta.validations.IConsultSchedule;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.user.Usuario;
import med.voll.api.infra.exeption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;
import java.util.Random;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<IConsultSchedule> validators;

    public ResponseEntity<DadosDetalhamentoConsulta> agendar(DadosAgendamentoConsulta dto) {
        validators.forEach(v->v.validate(dto));
        var medico = escolherMedico(dto);

        var paciente = pacienteRepository.findById(dto.idPaciente())
                .orElseThrow(() -> new ValidationException("Paciente não encontrado"));

        Consulta consulta = new Consulta(null, medico, paciente, dto.date());
        medico.addConsulta(consulta);
        paciente.addConsulta(consulta);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta( repository.save(consulta)));
    }

    public Page<DadosListagemConsulta> listAll(Pageable paginacao, Usuario usuario){
        System.out.println(usuario);
        return usuario == null || usuario.getPerfil() == Perfil.ATENDENTE
                ? repository.findAll(paginacao)
                .map(DadosListagemConsulta::new)
                : repository.getByPerfil(usuario.getId(), paginacao)
                .map(DadosListagemConsulta::new);
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
                List<Medico> medicos=  medicoRepository.getRandomMedic(dto.especialidade(), dto.date());
                return medicos.isEmpty() ? null : medicos.get(new Random().nextInt(medicos.size()));

            }
            return medico.get();

        }
        throw new ValidationException("O campo Medico deve ser informado");
    }
}
