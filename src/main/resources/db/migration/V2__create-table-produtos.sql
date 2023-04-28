create table produtos(
                        id BIGSERIAL not null ,
                        nome varchar(100) not null,
                        descricao varchar(255) not null,
                        preco numeric,
                        categoria_id bigint,

                        constraint pk_produtos primary key(id),
                        constraint fk_produtos_categoria_id foreign key(categoria_id) references categorias(id)

);