package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosUpdateMedico;
import med.voll.api.domain.paciente.*;
import med.voll.api.domain.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Long> cadastrar(@RequestBody @Valid DadosCadastroPaciente dto, UriComponentsBuilder uriBuilder){
        var pacienteId = pacienteService.salvarPaciente(dto).getId();
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteId).toUri();
        return ResponseEntity.created(uri).body(pacienteId);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid DadosUpdatePaciente dto){
        pacienteService.update(dto);
        return ResponseEntity.accepted().build(); //ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhePaciente> getPaciente(@PathVariable long id){
        return pacienteService.getById(id)
                .map(DadosDetalhePaciente::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listAll(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        var page = pacienteService.getAll(paginacao);
        return ResponseEntity.ok(page);
    }

    //@Secured("ROLE_ADMIN") //caso queira perminir apenas sob esta Role. Precisa habilitar em SecutityConfiguration SecurityFilterChain
    public ResponseEntity<Void> deletePaciente(@PathVariable long id){
        pacienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
