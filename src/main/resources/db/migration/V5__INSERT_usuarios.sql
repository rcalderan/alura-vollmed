INSERT INTO usuarios(name, login, password, perfil) VALUES ('Joao', 'joao@email.com', '$2y$10$GE/i8ZvFAPuRU8Av7Bq4sO3/PbSYWon2MG2oscE2B7nxPwCEGI2yu','MEDICO');
INSERT INTO usuarios(name, login, password, perfil) VALUES ('Maria', 'maria@email.com', '$2y$10$wXAjl/nG8gKHX.hUZVXRSO0Bgyfqtp0meTbTzaI559/qqIVxc2zbu','MEDICO');

INSERT INTO medicos (
    id,
    nome,
    email,
    crm,
    telefone,
    perfil,
    especialidade,
    logradouro,
    bairro,
    cep,
    cidade,
    uf,
    numero,
    complemento
) VALUES (
    1,                        -- id fixo
    'Joao',                   -- nome ajustado
    'jpjr@voll.med',          -- email
    '123456',                 -- CRM fictício, ajuste conforme necessário
    '232222232323',           -- telefone
    'MEDICO',                 -- perfil (ajuste conforme enum Perfil)
    'CARDIOLOGIA',          -- especialidade (ajuste conforme enum Especialidade)
    'rua 1',                  -- logradouro
    'bairro',                 -- bairro
    '12345678',               -- cep
    'Brasilia',               -- cidade
    'DF',                     -- uf
    '1',                      -- número
    NULL                      -- complemento não informado
);

INSERT INTO medicos (
    id,
    nome,
    email,
    crm,
    telefone,
    perfil,
    especialidade,
    logradouro,
    bairro,
    cep,
    cidade,
    uf,
    numero,
    complemento
) VALUES (
    2,                        -- id fixo para Maria
    'Maria',                  -- nome
    'maria@email.com',        -- email
    '654321',                 -- CRM fictício, ajuste conforme necessário
    '11999999999',            -- telefone fictício
    'MEDICO',                 -- perfil (ajuste conforme enum Perfil)
    'DERMATOLOGIA',          -- especialidade (ajuste conforme enum Especialidade)
    'rua 2',                  -- logradouro
    'bairro',                 -- bairro
    '87654321',               -- cep
    'Brasilia',               -- cidade
    'DF',                     -- uf
    '2',                      -- número
    NULL                      -- complemento não informado
);


