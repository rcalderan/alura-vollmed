package med.voll.api.domain.paciente;

import med.voll.api.domain.Perfil;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.DadosUpdateMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.user.GeradorSenha;
import med.voll.api.domain.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Long salvarPaciente(DadosCadastroPaciente dto){

        var randomPassword = GeradorSenha.gerarSenha(8);
        var userId = usuarioService.salvarUsuario(dto.nome(),dto.email(),randomPassword, Perfil.PACIENTE);
        return pacienteRepository.save(new Paciente(userId, dto)).getId();
    }

    @Transactional
    public void update(DadosUpdatePaciente dto){
        var paciente = pacienteRepository.getReferenceById(dto.id());
        paciente.update(dto);
        pacienteRepository.save(paciente);
    }

    @Transactional
    public void delete(Long id){
        usuarioService.deleteUsuario(id);
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> getById(Long id){
        return pacienteRepository.findById(id);
    }

    public Page<DadosListagemPaciente> getAll(Pageable paginacao){
        return pacienteRepository.findAll(paginacao)
                .map(DadosListagemPaciente::new);
    }
}
