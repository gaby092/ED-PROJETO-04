CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE Livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(255),
    disponivel BOOLEAN DEFAULT TRUE
);

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT,
    livroId INT,
    dataEmprestimo DATE,
    dataDevolucao DATE,
    FOREIGN KEY (usuarioId) REFERENCES Usuarios(id),
    FOREIGN KEY (livroId) REFERENCES Livros(id)
);

CREATE TABLE Reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT,
    livroId INT,
    dataReserva DATE,
    FOREIGN KEY (usuarioId) REFERENCES Usuarios(id),
    FOREIGN KEY (livroId) REFERENCES Livros(id)
);

CREATE TABLE Notificacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT,
    mensagem TEXT,
    dataEnvio DATE,
    FOREIGN KEY (usuarioId) REFERENCES Usuarios(id)
);

CREATE TABLE Avaliacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT,
    livroId INT,
    avaliacao INT CHECK (avaliacao BETWEEN 1 AND 5),
    comentario TEXT,
    dataAvaliacao DATE,
    FOREIGN KEY (usuarioId) REFERENCES Usuarios(id),
    FOREIGN KEY (livroId) REFERENCES Livros(id)
);
