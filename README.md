# 📚 Sistema de Gestão de Biblioteca Escolar

## 📌 Sobre o Projeto

Sistema web desenvolvido em Java utilizando Spring Boot para gerenciamento de bibliotecas escolares.

O projeto permite controlar alunos, responsáveis, livros, exemplares, empréstimos e multas através de uma interface web moderna construída com Thymeleaf.

Além do gerenciamento completo dos cadastros, o sistema possui um dashboard inicial com indicadores e gráficos estatísticos para acompanhamento das operações da biblioteca.

---

# 🚀 Tecnologias Utilizadas

### Backend

* Java 17+
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Frontend

* Thymeleaf
* HTML5
* CSS3
* JavaScript
* Chart.js

### Banco de Dados

* MySQL

### Build

* Maven

---

# 📋 Funcionalidades

## 👨‍🎓 Alunos

* Cadastro de alunos
* Edição de alunos
* Exclusão de alunos
* Consulta de alunos cadastrados
* Validação de RA único

---

## 👨‍👩‍👧 Responsáveis

* Cadastro de responsáveis
* Edição de responsáveis
* Exclusão de responsáveis
* Consulta de responsáveis cadastrados
* Validação de CPF único

---

## 📚 Livros

* Cadastro de livros
* Edição de livros
* Exclusão de livros
* Consulta de livros cadastrados

---

## 📖 Exemplares

* Cadastro de exemplares
* Associação com livros
* Controle de disponibilidade
* Consulta de exemplares

---

## 📕 Empréstimos

* Registro de empréstimos
* Associação entre aluno e exemplar
* Controle de empréstimos ativos
* Histórico de empréstimos

---

## 💰 Multas

* Cadastro de multas
* Associação com empréstimos
* Controle de multas pendentes
* Consulta de multas registradas

---

## 📊 Dashboard

Painel inicial com informações consolidadas do sistema:

* Total de alunos
* Total de responsáveis
* Total de livros
* Total de empréstimos
* Total de multas

### Gráficos

* Empréstimos por aluno
* Livros mais emprestados
* Multas por aluno

---

# 🗄️ Estrutura do Banco de Dados

Principais entidades:

* Aluno
* Responsável
* Livro
* Exemplar
* Empréstimo
* Multa

Relacionamentos:

* Um responsável pode estar associado a vários alunos
* Um livro possui vários exemplares
* Um aluno pode possuir vários empréstimos
* Um empréstimo pode gerar multas

---

# ⚙️ Pré-requisitos

Antes de executar o projeto, instale:

* Java JDK 17 ou superior
* Maven
* MySQL Server
* IntelliJ IDEA ou VS Code

---

# 🔐 Configuração do Banco

Crie o banco de dados:

```sql
CREATE DATABASE livraria;
```

Configure o arquivo:

```properties
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/livraria
spring.datasource.username=root
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

# ▶️ Executando o Projeto

## Pela IDE

Execute a classe:

```java
LivrariaApplication.java
```

---

## Pelo Terminal

```bash
mvn clean install
mvn spring-boot:run
```

---

# 🌐 Acesso

Após iniciar a aplicação:

```text
http://localhost:8080
```

---

# 🖥️ Interface do Sistema

O sistema possui:

* Menu lateral de navegação
* Dashboard com indicadores
* Gráficos estatísticos
* Formulários de cadastro
* Tabelas de consulta
* Layout responsivo

---

# 📂 Estrutura do Projeto

```text
src
├── main
│   ├── java
│   │   ├── controller
│   │   ├── entity
│   │   ├── repository
│   │   ├── service
│   │   └── LivrariaApplication
│   │
│   └── resources
│       ├── static
│       │   ├── css
│       │   ├── js
│       │   └── images
│       │
│       └── templates
│
└── pom.xml
```

---

# 🎯 Objetivo

Este projeto foi desenvolvido com foco acadêmico para aplicação dos conceitos de:

* Programação Orientada a Objetos
* Desenvolvimento Web com Spring Boot
* Persistência de Dados com JPA
* Integração Frontend e Backend
* Modelagem Relacional
* Arquitetura MVC

---

# 👨‍💻 Autor

Projeto desenvolvido por Bruno Rodrigues, Beatriz Moscareli, Henrique de Souza, Jo Martins e Rodrigo Àllvez para fins acadêmicos e de aprendizado em desenvolvimento Java Web.
