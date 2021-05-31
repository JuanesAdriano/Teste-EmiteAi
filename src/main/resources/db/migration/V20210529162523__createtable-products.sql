create table IF NOT EXISTS products
(
    id serial not null
        constraint products_pk
            primary key,
    name varchar(85),
    unitary_price bigint default 0
);

