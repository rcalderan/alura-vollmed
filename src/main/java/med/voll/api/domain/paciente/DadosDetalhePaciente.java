package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.DadosEndereco;

public record DadosDetalhePaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco,
        Convenio convenio
) {
    public DadosDetalhePaciente(Paciente p){
        this(p.getId(), p.getNome(),p.getEmail(),p.getTelefone(),new DadosEndereco(p.getEndereco()),p.getConvenio());
    }
}
