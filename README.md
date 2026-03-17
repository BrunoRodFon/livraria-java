# 📚 Sistema de Controle de Empréstimos - Livraria Escolar

## 📌 Descrição

Sistema desenvolvido em Java com Spring Boot para controle de empréstimos de uma biblioteca escolar de pequeno porte.

Permite o gerenciamento de alunos, responsáveis e, futuramente, livros, empréstimos e multas.

---

## 🚀 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Thymeleaf
* MySQL
* Maven

---

## ⚙️ Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

* JDK 17 ou superior
* Maven
* MySQL Server
* IDE (recomendado: IntelliJ ou VS Code)

---

## 🗄️ Configuração do Banco de Dados

1. Acesse o MySQL

2. Crie o banco de dados:

```sql
CREATE DATABASE livraria;
```

3. Execute o script de criação das tabelas (fornecido no projeto)

---

## 🔐 Configuração da Aplicação

No arquivo:

```
src/main/resources/application.properties
```

Configure suas credenciais do banco:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/livraria
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## ▶️ Como Executar o Projeto

### Opção 1 — Pela IDE

1. Abra o projeto
2. Localize a classe:

```
LivrariaApplication.java
```

3. Execute (Run)

---

### Opção 2 — Pelo terminal

Na raiz do projeto:

```bash
mvn spring-boot:run
```

---

## 🌐 Acesso ao Sistema

Após iniciar, acesse no navegador:

```
http://localhost:8080
```

Principais rotas:

* `/alunos` → Cadastro de alunos
* `/responsaveis` → Cadastro de responsáveis

---

## ✅ Funcionalidades Atuais

* Cadastro de alunos
* Cadastro de responsáveis
* Associação entre aluno e responsável
* Validação de RA único
* Validação de CPF único
* Exibição de erros na interface

---

## 🔄 Funcionalidades Futuras

* Cadastro de livros
* Controle de exemplares
* Empréstimos
* Controle de atrasos
* Multas automáticas
* Relatórios

---


## 📌 Observações

* O projeto roda localmente (localhost)
* Não possui autenticação (escopo acadêmico)
* Estrutura simples com foco em aprendizado

---

## 💡 Dica

Sempre execute:

```bash
mvn clean install
```

antes de rodar o projeto, para garantir que todas as dependências estão corretas.
