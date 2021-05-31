CREATE TABLE transport_orders
(
    id serial not null
        constraint transport_orders_pk
            primary key,
    infos jsonb not null
);

ALTER TABLE sales
    ADD COLUMN transport_order_id int;

ALTER TABLE sales
    ADD CONSTRAINT fk_transport_orders_sale FOREIGN KEY (transport_order_id) references transport_orders;

