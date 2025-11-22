package med.voll.api.domain.paciente;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.Perfil;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        String telefone,

        @NotNull
        @Valid
        DadosEndereco endereco,

        @NotNull
        Convenio convenio,

        Consulta consulta) {
}
