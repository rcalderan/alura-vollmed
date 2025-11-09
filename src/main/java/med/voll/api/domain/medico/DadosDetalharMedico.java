package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosEndereco;

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
