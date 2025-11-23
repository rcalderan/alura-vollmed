package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DadosAlteraSenha(
        @NotBlank
        String senhaAtual,

        @NotBlank
        String novaSenha,

        @NotBlank
        String novaSenhaConformacao
) {
}
