package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosDetalharMedico(
        Long id,
        String nome,
        String telefone,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco
) {
    public DadosDetalharMedico(Medico md) {
        this(md.getId(),
                md.getNome(), md.getTelefone(),
                md.getEmail(),
                md.getCrm(),
                md.getEspecialidade(), new DadosEndereco(md.getEndereco())
        );
    }
}
