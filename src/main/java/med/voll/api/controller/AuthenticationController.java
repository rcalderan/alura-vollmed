package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticationService;
import med.voll.api.domain.user.DadosAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid DadosAuthentication dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(),dto.password());

        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();

    }
}
