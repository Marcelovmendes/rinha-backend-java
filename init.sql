CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS transacoes (
    id SERIAL PRIMARY KEY,
    valor INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    id_cliente INTEGER NOT NULL,
    realizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

DO $$
BEGIN
  INSERT INTO clientes (nome, limite)
  VALUES
    ('Alvaro', 1000 * 100),
    ('Gibran', 800 * 100),
    ('Iago', 10000 * 100),
    ('Jvac', 100000 * 100),
    ('Guilherme', 5000 * 100);
END; $$