CREATE TABLE IF NOT EXISTS tb_product(
    id serial primary key,
    name VARCHAR(255) not null,
    description VARCHAR(255),
    price NUMERIC(7, 2) not null,
    quantity INTEGER not null
);

INSERT INTO tb_product (name, description, price, quantity) VALUES ('TV LG 50 polegadas OLED', 'TV show de bola com alta definição de imagem', 3500.49, 40);
INSERT INTO tb_product (name, description, price, quantity) VALUES ('Geladeira Consulta', 'Geladeira smart com frost free', 5629.99, 20);