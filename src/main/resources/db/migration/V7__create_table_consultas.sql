-- V7__create_consultas_table.sql

CREATE TABLE "Consultas" (
    id BIGSERIAL PRIMARY KEY,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    date TIMESTAMP NOT NULL,

    CONSTRAINT fk_consultas_medico FOREIGN KEY (medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);