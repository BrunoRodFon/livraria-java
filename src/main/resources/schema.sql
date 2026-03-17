CREATE TABLE IF NOT EXISTS responsaveis(
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100),
    Endereco VARCHAR(150),
    Telefone VARCHAR(11),
    Cpf CHAR(11) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS alunos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    RA VARCHAR(20) NOT NULL UNIQUE,
    idResponsavel INT,
    FOREIGN KEY (idResponsavel) REFERENCES responsaveis(id)
    );

CREATE TABLE IF NOT EXISTS autores(
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS generos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS livros(
    id INT AUTO_INCREMENT PRIMARY KEY,
    Titulo VARCHAR(200) NOT NULL,
    idAutor INT,
    idGenero INT,
    Descricao VARCHAR(250),
    FOREIGN KEY (idAutor) REFERENCES autores(id),
    FOREIGN KEY (idGenero) REFERENCES generos(id)
    );

CREATE TABLE IF NOT EXISTS livro_exemplares(
    id INT AUTO_INCREMENT PRIMARY KEY,
    cod INT NOT NULL UNIQUE,
    status ENUM('Disponivel', 'Emprestado'),
    idLivro INT,
    FOREIGN KEY (idLivro) REFERENCES livros(id)
    );

CREATE TABLE IF NOT EXISTS emprestimos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    dt_emprestimo DATE NOT NULL,
    dt_prevista DATE NOT NULL,
    dt_devolucao DATE,
    idLivroExemplar INT,
    idAluno INT,
    FOREIGN KEY (idLivroExemplar) REFERENCES livro_exemplares(id),
    FOREIGN KEY (idAluno) REFERENCES alunos(id)
    );

CREATE TABLE IF NOT EXISTS multas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idEmprestimo INT,
    Tipo ENUM('Dano', 'atraso'),
    Valor DECIMAL(10,2),
    status ENUM('Pago', 'Pendente'),
    FOREIGN KEY (idEmprestimo) REFERENCES emprestimos(id)
    );