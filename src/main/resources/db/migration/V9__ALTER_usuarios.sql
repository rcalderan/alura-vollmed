--ALTER TABLE usuarios ADD name VARCHAR(20) DEFAULT 'N/A' NOT NULL;
INSERT INTO usuarios(name, login, password) VALUES ('Joao', 'joao@email.com', '$2y$10$GE/i8ZvFAPuRU8Av7Bq4sO3/PbSYWon2MG2oscE2B7nxPwCEGI2yu');
INSERT INTO usuarios(name, login, password) VALUES ('Maria', 'maria@email.com', '$2y$10$wXAjl/nG8gKHX.hUZVXRSO0Bgyfqtp0meTbTzaI559/qqIVxc2zbu');