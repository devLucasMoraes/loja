alter table categorias add ativo smallint;
update categorias set ativo = 1;
