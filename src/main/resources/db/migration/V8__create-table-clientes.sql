create table clientes(
                        id BIGSERIAL not null ,
                        nome VARCHAR(255) NOT NULL,
                        cpf VARCHAR(14) NOT NULL,
                        ativo boolean,
                        constraint pk_clientes primary key (id)
);