package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.Especialidade;

public record DadosUpdatePaciente(@NotNull
                                  Long id,
                                  String nome,
                                  String email,
                                  String telefone,
                                  DadosEndereco endereco,
                                  Convenio convenio) {

}
