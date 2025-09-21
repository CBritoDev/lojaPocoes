--V1__create_pocao_table.sql

CREATE TABLE IF NOT EXISTS Pocao(
    id SERIAL UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(8,2) NOT NULL,
    disponivel BOOLEAN NOT NULL,
    tamanho VARCHAR(50) NOT NULL,
    efeito VARCHAR(50) NOT NULL,

    CONSTRAINT pk_pocao_id PRIMARY KEY(id)
);