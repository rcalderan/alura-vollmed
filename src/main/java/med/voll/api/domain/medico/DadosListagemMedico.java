package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Perfil;

public record DadosListagemMedico(
        Long id,
        String nome,
        String mail,
        String crm,
        Perfil perfil,
        Especialidade especialidade
) {
    public DadosListagemMedico(@NotNull Medico medico){
        this(medico.getId(), medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getPerfil(), medico.getEspecialidade());
    }
}
