package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosListagemConsulta;
import med.voll.api.domain.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        return service.agendar(dados);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<DadosDetalharMedico> getMedico(@PathVariable long id){
//        return service.getById(id)
//                .map(DadosDetalharMedico::new)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listAll(@PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable paginacao, @AuthenticationPrincipal Usuario usuarioLogado){
        var page = service.listAll(paginacao,usuarioLogado);
        return ResponseEntity.ok(page);
    }
}
