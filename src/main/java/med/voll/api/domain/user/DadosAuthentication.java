package med.voll.api.domain.user;

import med.voll.api.domain.Perfil;

public record DadosAuthentication(
        String login,
        String password
) {
}
