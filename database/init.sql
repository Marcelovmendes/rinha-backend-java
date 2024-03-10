CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    limite DECIMAL
);

INSERT INTO clientes (nome, limite)

VALUES 
('João', 1000),
('Maria', 2000),
('José', 3000),
('Ana', 4000),
('Carlos', 5000);
 