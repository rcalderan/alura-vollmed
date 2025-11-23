CREATE TABLE medicos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    perfil VARCHAR(50) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,

    logradouro VARCHAR(100),
    bairro VARCHAR(100),
    cep VARCHAR(20),
    cidade VARCHAR(100),
    uf VARCHAR(2),
    numero VARCHAR(20),
    complemento VARCHAR(100)
);