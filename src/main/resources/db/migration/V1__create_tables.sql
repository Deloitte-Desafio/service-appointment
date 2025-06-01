CREATE TABLE tb_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(50) NOT NULL
);

CREATE TABLE tb_servico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    duracao_minutos INT NOT NULL,
    profissional_id BIGINT NOT NULL,
    FOREIGN KEY (profissional_id) REFERENCES tb_user(id)
);

CREATE TABLE tb_disponibilidade (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    profissional_id BIGINT NOT NULL,
    dia_da_semana VARCHAR(50) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    FOREIGN KEY (profissional_id) REFERENCES tb_user(id)
);

CREATE TABLE tb_agendamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    servico_id BIGINT NOT NULL,
    data_hora_inicio TIMESTAMP NOT NULL,
    data_hora_fim TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES tb_user(id),
    FOREIGN KEY (profissional_id) REFERENCES tb_user(id),
    FOREIGN KEY (servico_id) REFERENCES tb_servico(id)
);