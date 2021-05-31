ALTER TABLE transport_orders
    ADD COLUMN sale_id int;
ALTER TABLE transport_orders
    ADD CONSTRAINT fk_sale_transport_orders FOREIGN KEY (sale_id) references sales;