package med.voll.api.domain.user;

import med.voll.api.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public void deleteUsuario(Long id){
        this.usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> getById(Long id){
        return usuarioRepository.findById(id);
    }


    public Long salvarUsuario(String nome, String email, String senha){
        var encryptedPassword = passwordEncoder.encode(senha);
        var saved =  usuarioRepository.save(new Usuario(nome, email, encryptedPassword));
        return  saved.getId();
    }

    public UserDetails getUserDetails(String login){
        return usuarioRepository.findByLogin(login);
    }

}
