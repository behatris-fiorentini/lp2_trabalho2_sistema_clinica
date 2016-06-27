CREATE DATABASE sysclinica;
USE sysclinica;


CREATE TABLE medico(
cod_medico BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(40) NOT NULL,
especializacao VARCHAR(30) NOT NULL,
crm VARCHAR(20) NOT NULL,
endereco VARCHAR(50) NOT NULL,
telefone VARCHAR(24) NOT NULL,
contratacao VARCHAR(10) NOT NULL,
demissao VARCHAR(10)
);



CREATE TABLE paciente(
cod_paciente BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
idade INT NOT NULL,
data_nascimento VARCHAR(10) NOT NULL,
cpf VARCHAR(11) NOT NULL,
rg VARCHAR(10) NOT NULL,
endereco VARCHAR(30) NOT NULL,
cidade VARCHAR(30) NOT NULL,
bairro VARCHAR(30) NOT NULL,
numero_residencia INT,
telefone VARCHAR(12),
celular VARCHAR(12)
);


CREATE TABLE auxiliar(
cod_auxiliar BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(40),
especializacao VARCHAR(30),
registro VARCHAR(20),
endereco VARCHAR(50),
telefone VARCHAR(24),
contratacao VARCHAR(10),
demissao VARCHAR(10)
);

CREATE TABLE usuario(
cod_usuario BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50),
senha VARCHAR(30),
tipo VARCHAR(30)
);

