create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to avemt;

create table products
(
    id            integer generated by default as identity
        constraint products_pkey
            primary key,
    name          varchar(85),
    unitary_price bigint
);

alter table products
    owner to avemt;

create table sale_code_sequence_number
(
    code bigint not null
        constraint sale_code_sequence_number_pkey
            primary key
);

alter table sale_code_sequence_number
    owner to avemt;

create table sales
(
    id                 integer generated by default as identity
        constraint sales_pkey
            primary key,
    created_at         timestamp,
    sale_value         bigint,
    code_code          bigint
        constraint fkaj8bj0sd7h7tukrf12ubr4yb5
            references sale_code_sequence_number,
    transport_order_id integer
);

alter table sales
    owner to avemt;

create table sale_products
(
    id          integer generated by default as identity
        constraint sale_products_pkey
            primary key,
    amount      integer,
    total_value bigint,
    product_id  integer
        constraint fk5wid6n4tyw3oprg9j77lgul01
            references products,
    sale_id     integer
        constraint fkg7exm2ml8xxfrdiu4k7mojvhq
            references sales
);

alter table sale_products
    owner to avemt;

create table transport_orders
(
    id      integer generated by default as identity
        constraint transport_orders_pkey
            primary key,
    infos   jsonb,
    sale_id integer
        constraint fk76kaxd299ijq4y8154rch8h5m
            references sales
);

alter table transport_orders
    owner to avemt;

alter table sales
    add constraint fkkry5te76brsekt7tj3dsgob6p
        foreign key (transport_order_id) references transport_orders;

