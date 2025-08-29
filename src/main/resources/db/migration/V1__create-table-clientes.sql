create table clientes(

    id bigint not null auto_increment,
    nome_completo varchar(100) not null,
    data_nascimento datetime not null,
    cpf varchar(14) not null unique,
    telefone varchar(20) not null,
    email varchar(100) not null unique,
    sexo varchar(100) not null,
    estado_civil varchar(100) not null,
    profissao varchar(100) not null,
    nacionalidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)

);