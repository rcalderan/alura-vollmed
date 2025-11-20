package med.voll.api.domain.medico;

import med.voll.api.domain.user.Usuario;
import med.voll.api.domain.user.UsuarioRepository;
import med.voll.api.domain.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;


    @Transactional
    public Medico create(DadosCadastroMedico dto){
        var userId = usuarioService.salvarUsuario(dto.nome(), dto.email(), dto.crm());
        return medicoRepository.save(new Medico(userId, dto));
    }

    @Transactional
    public void update(DadosUpdateMedico dto){
        var medico = medicoRepository.getReferenceById(dto.id());
        medico.update(dto);
        medicoRepository.save(medico);
    }

    @Transactional
    public void delete(Long id){
        usuarioService.deleteUsuario(id);
        medicoRepository.deleteById(id);
    }

    public Optional<Medico> getById(Long id){
        return medicoRepository.findById(id);
    }

    public Page<DadosListagemMedico> getAll(Pageable paginacao){
        return medicoRepository.findAll(paginacao)
                .map(DadosListagemMedico::new);
    }
}
