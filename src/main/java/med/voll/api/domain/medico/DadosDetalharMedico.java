package med.voll.api.domain.medico;

import med.voll.api.domain.Perfil;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosDetalharMedico(
        Long id,
        String nome,
        String telefone,
        String email,
        String crm,
        Perfil perfil,
        Especialidade especialidade,
        DadosEndereco endereco
) {
    public DadosDetalharMedico(Medico md) {
        this(md.getId(),
                md.getNome(), md.getTelefone(),
                md.getEmail(),
                md.getCrm(),
                md.getPerfil(),
                md.getEspecialidade(), new DadosEndereco(md.getEndereco())
        );
    }
}
