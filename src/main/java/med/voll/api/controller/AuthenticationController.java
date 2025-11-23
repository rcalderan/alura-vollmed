package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.user.*;
import med.voll.api.infra.exeption.security.DataJwt;
import med.voll.api.infra.exeption.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DataJwt> efetuarLogin(@RequestBody @Valid DadosAuthentication dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(),dto.password());

        var authentication = manager.authenticate(token);

        var jwt = tokenService.generate( (Usuario)authentication.getPrincipal() );

        return ResponseEntity.ok(
                new DataJwt(jwt)
        );
    }

    @PostMapping("/changepassword")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid DadosAlteraSenha dadosAlteraSenha, @AuthenticationPrincipal Usuario usuarioLogado){

        usuarioService.alterarSenha(dadosAlteraSenha, usuarioLogado);
        return  ResponseEntity.ok().build();
    }
}
