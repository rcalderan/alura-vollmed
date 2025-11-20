package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Long> cadastrar(@RequestBody @Valid DadosCadastroMedico dto, UriComponentsBuilder uriBuilder){
        var medico = medicoService.create(dto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medico.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalharMedico> getMedico(@PathVariable long id){
        return medicoService.getById(id)
                .map(DadosDetalharMedico::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listAll(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        var page = medicoService.getAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid DadosUpdateMedico dto){

        medicoService.update(dto);

        return ResponseEntity.accepted().build(); //ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional

    //@Secured("ROLE_ADMIN") //caso queira perminir apenas sob esta Role. Precisa habilitar em SecutityConfiguration SecurityFilterChain
    public ResponseEntity<Void> deleteMedico(@PathVariable long id){
        medicoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
