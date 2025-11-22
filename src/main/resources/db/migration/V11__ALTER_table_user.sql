-- 1. Adicionar a coluna como TEXT
ALTER TABLE usuarios
ADD COLUMN perfil TEXT NOT NULL DEFAULT 'N/A';

-- 2. Atualizar os registros de acordo com o vínculo
-- Se o id do usuário existir na tabela medicos
UPDATE usuarios u
SET perfil = 'MEDICO'
WHERE EXISTS (
    SELECT 1 FROM medicos m WHERE m.id = u.id
);

-- Se o id do usuário existir na tabela pacientes
UPDATE usuarios u
SET perfil = 'PACIENTE'
WHERE EXISTS (
    SELECT 1 FROM pacientes p WHERE p.id = u.id
);

-- Os demais permanecem com 'N/A'
