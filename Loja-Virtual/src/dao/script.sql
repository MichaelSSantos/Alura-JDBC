--Schema lojavirtual
create table lojavirtual;

--Tabela categoria
create table categoria(
	id int auto_increment,
	nome varchar(255),
	constraint pk_categoria
		primary key(id)
);

-- Tabela produto
create table produto(
	id int auto_increment,
	nome varchar(255),
	descricao varchar(255),
	constraint pk_produto
		primary key(id) 
);


-- Exclus�o de produtos
--delete from produto where id > 3;

-- Inclus�o de produtos
insert into produto (nome, descricao) values('Geladeira', 'Duas portas');
insert into produto (nome, descricao) values('Ferro de passar', 'Vaporizador');
insert into produto (nome, descricao) values('Notebook', '4GB de RAM e i5');
insert into produto (nome, descricao) values('Mesa azul', 'Mesa com 4 p�s');
insert into produto (nome, descricao) values('Mesa vermelha', 'Mesa com 4 p�s');

-- Inclus�o da coluna categoria em produto
alter table produto add column categoria_id int;

-- Inclus�o de categorias
insert into categoria (nome) values ('Eletrodom�stico');
insert into categoria (nome) values ('Eletr�nico');
insert into categoria (nome) values ('M�vel');

--Vinculando produto a categoria
update produto set categoria_id = 1 where id in (1, 2);
update produto set categoria_id = 2 where id = 3;
update produto set categoria_id = 3 where id in (4, 5);

--Constraint FK de categoria em produto
alter table produto add constraint fk_categoria 
	foreign key (categoria_id) references categoria(id);