package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dto){

        medicoRepository.save(new Medico(dto));
    }

    @GetMapping
    public Page<DadosListagemMedico> listAll(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        return medicoRepository.findAll(paginacao)
                .map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DadosUpdateMedico dto){

        var medico = medicoRepository.getReferenceById(dto.id());
        medico.update(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable long id){
        medicoRepository.deleteById(id);
    }
}
