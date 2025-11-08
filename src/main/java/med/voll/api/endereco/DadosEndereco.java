package med.voll.api.endereco;

public record DadosEndereco(
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String numero,
        String complemento
) {

//        "logradouro": "rua 1",
//                "bairro": "bairro",
//                "cep": "12345678",
//                "cidade": "Brasilia",
//                "uf": "DF",
//                "numero": "1",
//                "complemento": "complemento"
//
}
