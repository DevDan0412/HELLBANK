-- ============================================================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS - HELLBANK
-- Autor: Daniel Barbosa
-- Descrição: Cria a estrutura completa do banco, tabelas e usuário administrador.
-- ============================================================================

-- 1. CRIAÇÃO DO BANCO DE DADOS
-- Cria o banco apenas se ele ainda não existir.
CREATE DATABASE IF NOT EXISTS hellbank_db;

-- Seleciona o banco para executar os próximos comandos.
USE hellbank_db;

-- ============================================================================
-- 2. CRIAÇÃO DA TABELA DE USUÁRIOS (users)
-- ============================================================================
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,      -- UNIQUE impede CPFs duplicados
    email VARCHAR(100) NOT NULL UNIQUE,   -- UNIQUE impede e-mails duplicados
    phone VARCHAR(20),
    dob VARCHAR(15),                      -- Data de nascimento (texto)
    password VARCHAR(50) NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,  -- Saldo inicial
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP, -- Data automática de registro
    tipo_permissao VARCHAR(20) DEFAULT 'USUARIO'      -- Nível de acesso (USUARIO ou ADMIN)
);

-- ============================================================================
-- 3. CRIAÇÃO DA TABELA DE TRANSAÇÕES (transactions)
-- ============================================================================
CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,             
    type VARCHAR(20) NOT NULL,        -- 'RECEITA' ou 'DESPESA'
    method VARCHAR(30),               -- 'PIX', 'CREDITO', 'DEBITO'
    amount DECIMAL(10, 2) NOT NULL,   
    description VARCHAR(100),         
    date_time DATETIME DEFAULT CURRENT_TIMESTAMP, 
    
    -- Cria a relação com a tabela de usuários
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ============================================================================
-- 4. DADOS INICIAIS (ADMINISTRADOR MESTRE)
-- ============================================================================
-- Insere o usuário Admin já com saldo e permissão corretos.
-- Se você rodar isso num banco limpo, o ID dele será 1.

INSERT INTO users (name, cpf, email, phone, dob, password, balance, tipo_permissao)
VALUES (
    'Daniel Barbosa', 
    '389.593.408-98', 
    'dani.ramalho0405@gmail.com', 
    '(11) 99521-1263', 
    '04/05/2006', 
    'Dam&041210', 
    10000.00, 
    'ADMIN'
);

-- Insere o histórico desse saldo inicial (para o extrato não ficar vazio)
-- Obs: Estamos assumindo que o ID do Daniel gerado acima foi o primeiro (1 ou 2 dependendo do histórico).
-- O comando abaixo pega o último ID inserido para garantir que vai para o usuário certo.

INSERT INTO transactions (user_id, type, method, amount, description)
VALUES (LAST_INSERT_ID(), 'RECEITA', 'PIX', 10000.00, 'Saldo Inicial / Salário');

-- ============================================================================
-- 5. VERIFICAÇÃO
-- ============================================================================
SELECT * FROM users;
SELECT * FROM transactions;