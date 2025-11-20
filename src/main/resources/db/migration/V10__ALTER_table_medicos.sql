-- Remover a constraint de chave estrangeira
ALTER TABLE consultas DROP CONSTRAINT fk_consultas_medico;

-- Alterar a coluna 'id' da tabela 'medicos' para BIGINT NOT NULL
ALTER TABLE medicos ALTER COLUMN id TYPE BIGINT;
ALTER TABLE medicos ALTER COLUMN id SET NOT NULL;

-- Adicionar novamente a constraint de chave estrangeira
ALTER TABLE consultas
ADD CONSTRAINT fk_consultas_medico_id
FOREIGN KEY (medico_id) REFERENCES medicos(id);