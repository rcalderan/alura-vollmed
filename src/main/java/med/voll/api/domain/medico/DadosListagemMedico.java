package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;

public record DadosListagemMedico(
        Long id,
        String nome,
        String mail,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedico(@NotNull Medico medico){
        this(medico.getId(), medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
