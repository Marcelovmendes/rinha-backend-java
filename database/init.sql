
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL DEFAULT 0
);
INSERT INTO clientes (nome, limite)
VALUES 
('João', 1000),
('Maria', 2000),
('José', 3000),
('Ana', 4000),
('Carlos', 5000);

