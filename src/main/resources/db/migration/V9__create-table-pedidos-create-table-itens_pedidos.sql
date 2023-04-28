CREATE TABLE pedidos (
                         id BIGSERIAL NOT NULL PRIMARY KEY,
                         data DATE,
                         valor_total NUMERIC(10, 2),
                         cliente_id BIGINT NOT NULL,
                         CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE itens_pedido (
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              quantidade INTEGER,
                              preco_unitario NUMERIC(10, 2),
                              produto_id BIGINT NOT NULL,
                              pedido_id BIGINT NOT NULL,
                              CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos (id),
                              CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos (id)
);
