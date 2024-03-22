CREATE TABLE IF NOT EXISTS tb_product(
    id serial primary key,
    name VARCHAR(255) not null,
    description VARCHAR(255),
    price NUMERIC(7, 2) not null,
    quantity INTEGER not null
);