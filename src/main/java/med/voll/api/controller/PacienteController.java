package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Long> cadastrar(@RequestBody @Valid DadosCadastroPaciente dto, UriComponentsBuilder uriBuilder){
        var paciente = repository.save(new Paciente(dto));
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhePaciente> getPaciente(@PathVariable long id){
        return repository.findById(id)
                .map(DadosDetalhePaciente::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listAll(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        var page = repository.findAll(paginacao)
                .map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }
}
