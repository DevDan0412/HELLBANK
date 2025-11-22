# 🔥 HellBank - Sistema Bancário em Java

Projeto desenvolvido como avaliação do 2º Semestre de Ciência da Computação.
O sistema simula as operações básicas de um banco digital com foco em segurança e hierarquia de usuários.

## 🖥️ Tecnologias Utilizadas
* **Linguagem:** Java (JDK 17+)
* **Interface:** Java Swing (FlatLaf Dark Theme)
* **Banco de Dados:** MySQL
* **IDE:** NetBeans

## ✨ Funcionalidades Principais
* **Login Seguro:** Bloqueio após 3 tentativas erradas e efeito visual de erro.
* **Hierarquia:** Sistema de Admin Mestre (Intocável) e Usuários Comuns.
* **Transações:** Transferências via PIX com validação de senha e saldo.
* **Segurança:** Timeout de inatividade (1 min) e bloqueio de SQL Injection.
* **Extras:** Comprovante em .TXT, sons de sistema e relógio em tempo real.

## 🚀 Como Executar
1.  Clone este repositório.
2.  Importe o banco de dados `bd_hellbank.sql` no seu MySQL.
3.  Adicione os JARs da pasta `Bibliotecas` ao seu projeto no NetBeans.
4.  Configure a senha do banco na classe `ConnectionFactory`.
5.  Execute a classe `Tela_Login`.

## 👤 Autor
Desenvolvido por **[Daniel Barbosa]**.
