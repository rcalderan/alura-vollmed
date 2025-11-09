package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;

public record DadosListagemMedico(
        String nome,
        String mail,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedico(@NotNull Medico medico){
        this(medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
