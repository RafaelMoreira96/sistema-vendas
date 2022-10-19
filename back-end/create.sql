create sequence hibernate_sequence start with 1 increment by 1

    create table compra (
       id integer not null,
        data_compra date,
        valor_total double not null,
        fornecedor_id integer,
        funcionario_id integer,
        primary key (id)
    )

    create table compra_produtos (
       compra_id integer not null,
        produtos_id integer not null
    )

    create table contato (
       id integer not null,
        numero varchar(255) not null,
        tipo varchar(255),
        primary key (id)
    )

    create table endereco (
       id integer not null,
        bairro varchar(255) not null,
        cep varchar(255),
        cidade varchar(255),
        complemento varchar(255),
        estado varchar(255),
        logradouro varchar(255) not null,
        numero varchar(255),
        primary key (id)
    )

    create table forma_pagamento (
       venda_id integer not null,
        descricao varchar(255) not null,
        primary key (venda_id)
    )

    create table hibernate_sequences (
       sequence_name varchar(255) not null,
        next_val bigint,
        primary key (sequence_name)
    )

    create table item_compra (
       id integer not null,
        cod_barras varchar(255) not null,
        descricao varchar(255) not null,
        preco_compra double not null,
        quant double not null,
        primary key (id)
    )

    create table item_venda (
       id integer not null,
        cod_barras varchar(255) not null,
        descricao varchar(255) not null,
        preco_vendido double not null,
        quant double not null,
        primary key (id)
    )

    create table nivelauth (
       funcionario_id integer not null,
        nivel_auth integer
    )

    create table pessoa (
       dtype varchar(31) not null,
        id integer not null,
        cnpj varchar(255),
        cpf varchar(255),
        data_cadastro date,
        nome varchar(255) not null,
        status boolean,
        inscricao_estadual varchar(255),
        nome_fantasia varchar(255),
        data_admissao date,
        data_demissao date,
        nome_usuario varchar(255),
        senha varchar(255),
        endereco_id integer,
        primary key (id)
    )

    create table pessoa_contatos (
       pessoa_id integer not null,
        contatos_id integer not null
    )

    create table produto (
       id integer not null,
        cod_barras varchar(255) not null,
        descricao varchar(255) not null,
        preco_atacado double not null,
        preco_varejo double not null,
        qte_estoque double not null,
        qte_max double not null,
        qte_min double not null,
        primary key (id)
    )

    create table venda (
       id integer not null,
        data_venda date,
        numero_venda integer,
        status integer,
        cliente_id integer,
        forma_pagamento_id integer,
        funcionario_id integer,
        primary key (id)
    )

    create table venda_lista_produtos (
       venda_id integer not null,
        lista_produtos_id integer not null
    )

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    

    alter table compra_produtos 
       add constraint UK_k8mu1463aqvlbbjyb6sq6fafm unique (produtos_id)

    alter table pessoa_contatos 
       add constraint UK_omlb9f9j1l5qxbnhd5e494fgp unique (contatos_id)

    alter table venda_lista_produtos 
       add constraint UK_25kve4uyipdobkad4um0cjvds unique (lista_produtos_id)

    alter table compra 
       add constraint FKlcdrolkwb71xl0q2qe21gtbb8 
       foreign key (fornecedor_id) 
       references pessoa

    alter table compra 
       add constraint FKr2sscx23pc4c3i160muojyq4u 
       foreign key (funcionario_id) 
       references pessoa

    alter table compra_produtos 
       add constraint FKjw8qfkf7w45l8pygg7t7h9na 
       foreign key (produtos_id) 
       references item_compra

    alter table compra_produtos 
       add constraint FKibggswb6t3o3069sod887dc3a 
       foreign key (compra_id) 
       references compra

    alter table forma_pagamento 
       add constraint FKfsoci8ox0gfwoe0qp0s2fjh38 
       foreign key (venda_id) 
       references venda

    alter table nivelauth 
       add constraint FKmxmfu7b57l90t9aplc42udx1w 
       foreign key (funcionario_id) 
       references pessoa

    alter table pessoa 
       add constraint FKei4abnsw085kx27j89rp796ny 
       foreign key (endereco_id) 
       references endereco

    alter table pessoa_contatos 
       add constraint FKnp8gchq5vuod4qvnehr3mledk 
       foreign key (contatos_id) 
       references contato

    alter table pessoa_contatos 
       add constraint FKjjjk6nsnefrect5ui0irylfyu 
       foreign key (pessoa_id) 
       references pessoa

    alter table venda 
       add constraint FKnvdoekb8r7hhsemgfyvwmr72v 
       foreign key (cliente_id) 
       references pessoa

    alter table venda 
       add constraint FKm46is4lg2cs7j4t5fqpspb8h7 
       foreign key (forma_pagamento_id) 
       references forma_pagamento

    alter table venda 
       add constraint FKenra7vdabbyulojvmbhf9p9ce 
       foreign key (funcionario_id) 
       references pessoa

    alter table venda_lista_produtos 
       add constraint FK9ahxxeo186dfhlinmae445gjc 
       foreign key (lista_produtos_id) 
       references item_venda

    alter table venda_lista_produtos 
       add constraint FKql4dybgi9spxogpn5yfbg3ycu 
       foreign key (venda_id) 
       references venda
create sequence hibernate_sequence start with 1 increment by 1

    create table compra (
       id integer not null,
        data_compra date,
        valor_total double not null,
        fornecedor_id integer,
        funcionario_id integer,
        primary key (id)
    )

    create table compra_produtos (
       compra_id integer not null,
        produtos_id integer not null
    )

    create table contato (
       id integer not null,
        numero varchar(255) not null,
        tipo varchar(255),
        primary key (id)
    )

    create table endereco (
       id integer not null,
        bairro varchar(255) not null,
        cep varchar(255),
        cidade varchar(255),
        complemento varchar(255),
        estado varchar(255),
        logradouro varchar(255) not null,
        numero varchar(255),
        primary key (id)
    )

    create table forma_pagamento (
       venda_id integer not null,
        descricao varchar(255) not null,
        primary key (venda_id)
    )

    create table hibernate_sequences (
       sequence_name varchar(255) not null,
        next_val bigint,
        primary key (sequence_name)
    )

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    insert into hibernate_sequences(sequence_name, next_val) values ('default',0)

    create table item_compra (
       id integer not null,
        cod_barras varchar(255) not null,
        descricao varchar(255) not null,
        preco_compra double not null,
        quant double not null,
        primary key (id)
    )

    create table item_venda (
       id integer not null,
        cod_barras varchar(255) not null,
        descricao varchar(255) not null,
        preco_vendido double not null,
        quant double not null,
        primary key (id)
    )

    create table nivelauth (
       funcionario_id integer not null,
        nivel_auth integer
    )

    create table pessoa (
       dtype varchar(31) not null,
        id integer not null,
        cnpj varchar(255),
        cpf varchar(255),
        data_cadastro date,
        nome varchar(255) not null,
        status boolean,
        inscricao_estadual varchar(255),
        nome_fantasia varchar(255),
        data_admissao date,
        data_demissao date,
        nome_usuario varchar(255),
        senha varchar(255),
        endereco_id integer,
        primary key (id)
    )

    create table pessoa_contatos (
       pessoa_id integer not null,
        contatos_id integer not null
    )

    create table produto (
       id integer not null,
        cod_barras varchar(255) not null,
        descricao varchar(255) not null,
        preco_atacado double not null,
        preco_varejo double not null,
        qte_estoque double not null,
        qte_max double not null,
        qte_min double not null,
        primary key (id)
    )

    create table venda (
       id integer not null,
        data_venda date,
        numero_venda integer,
        status integer,
        cliente_id integer,
        forma_pagamento_id integer,
        funcionario_id integer,
        primary key (id)
    )

    create table venda_lista_produtos (
       venda_id integer not null,
        lista_produtos_id integer not null
    )

    alter table compra_produtos 
       add constraint UK_k8mu1463aqvlbbjyb6sq6fafm unique (produtos_id)

    alter table pessoa_contatos 
       add constraint UK_omlb9f9j1l5qxbnhd5e494fgp unique (contatos_id)

    alter table venda_lista_produtos 
       add constraint UK_25kve4uyipdobkad4um0cjvds unique (lista_produtos_id)

    alter table compra 
       add constraint FKlcdrolkwb71xl0q2qe21gtbb8 
       foreign key (fornecedor_id) 
       references pessoa

    alter table compra 
       add constraint FKr2sscx23pc4c3i160muojyq4u 
       foreign key (funcionario_id) 
       references pessoa

    alter table compra_produtos 
       add constraint FKjw8qfkf7w45l8pygg7t7h9na 
       foreign key (produtos_id) 
       references item_compra

    alter table compra_produtos 
       add constraint FKibggswb6t3o3069sod887dc3a 
       foreign key (compra_id) 
       references compra

    alter table forma_pagamento 
       add constraint FKfsoci8ox0gfwoe0qp0s2fjh38 
       foreign key (venda_id) 
       references venda

    alter table nivelauth 
       add constraint FKmxmfu7b57l90t9aplc42udx1w 
       foreign key (funcionario_id) 
       references pessoa

    alter table pessoa 
       add constraint FKei4abnsw085kx27j89rp796ny 
       foreign key (endereco_id) 
       references endereco

    alter table pessoa_contatos 
       add constraint FKnp8gchq5vuod4qvnehr3mledk 
       foreign key (contatos_id) 
       references contato

    alter table pessoa_contatos 
       add constraint FKjjjk6nsnefrect5ui0irylfyu 
       foreign key (pessoa_id) 
       references pessoa

    alter table venda 
       add constraint FKnvdoekb8r7hhsemgfyvwmr72v 
       foreign key (cliente_id) 
       references pessoa

    alter table venda 
       add constraint FKm46is4lg2cs7j4t5fqpspb8h7 
       foreign key (forma_pagamento_id) 
       references forma_pagamento

    alter table venda 
       add constraint FKenra7vdabbyulojvmbhf9p9ce 
       foreign key (funcionario_id) 
       references pessoa

    alter table venda_lista_produtos 
       add constraint FK9ahxxeo186dfhlinmae445gjc 
       foreign key (lista_produtos_id) 
       references item_venda

    alter table venda_lista_produtos 
       add constraint FKql4dybgi9spxogpn5yfbg3ycu 
       foreign key (venda_id) 
       references venda
