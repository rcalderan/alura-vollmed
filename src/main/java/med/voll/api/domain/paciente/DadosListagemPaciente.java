package med.voll.api.domain.paciente;

import med.voll.api.domain.Perfil;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        Perfil perfil,
        Convenio especialidade
) {
    public DadosListagemPaciente(Paciente p){
        this(p.getId(),p.getNome(),p.getEmail(),p.getTelefone(),p.getPerfil(), p.getConvenio());
    }
}
