package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record DadosUpdateMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
