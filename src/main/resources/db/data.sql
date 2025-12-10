INSERT INTO `tiendadb`.`categorias` (category_id, category_name) VALUES
(1, 'Electrónica'),
(2, 'Ropa'),
(3, 'Hogar');


INSERT INTO tiendadb.productos (producto_id, product_name, description, price, image_url,category_id) VALUES
(0, 'Laptop Dell XPS 13', 'Laptop ultraligera con procesador Intel i7', 1200.00, 'https://example.com/images/laptop1.jpg',1);

INSERT INTO tiendadb.productos (producto_id, product_name, description, price, image_url,category_id) VALUES
(0, 'Smartphone Samsung Galaxy S23', 'Teléfono inteligente con cámara de 108MP', 999.99, 'https://example.com/images/smartphone1.jpg',1);

INSERT INTO tiendadb.productos (producto_id, product_name, description, price, image_url,category_id) VALUES
(0, 'Auriculares Sony WH-1000XM4', 'Auriculares inalámbricos con cancelación de ruido', 350.50, 'https://example.com/images/headphones1.jpg',1);

INSERT INTO tiendadb.productos (producto_id, product_name, description, price, image_url,category_id) VALUES
(0, 'Reloj Apple Watch Series 9', 'Smartwatch con monitor de salud y GPS', 449.00, 'https://example.com/images/watch1.jpg',1);

INSERT INTO tiendadb.productos (producto_id, product_name, description, price, image_url,category_id) VALUES
(0, 'Tablet iPad Pro', 'Tablet con pantalla Liquid Retina y chip M2', 1100.00, 'https://example.com/images/tablet1.jpg',1);

INSERT INTO tiendadb.users VALUES (0, "admin", "holaholita", "admin@localhost.com");

INSERT INTO tiendadb.users VALUES (0, "Rafa", "holaholita1", "rafael@mancina.com");

INSERT INTO tiendadb.users VALUES (0, "Sergi", "holaholita2", "sergi@sergi.com");   



INSERT INTO tiendadb.users_bought_productos (Users_user_id, productos_producto_id)
VALUES 
(2, 3),  -- Rafa compró Smartphone Samsung Galaxy S23
(2, 4);  -- Rafa compró Tablet iPad Pro

-- Sergi compra auriculares y smartwatch
INSERT INTO tiendadb.users_bought_productos (Users_user_id, productos_producto_id)
VALUES 
(3, 3) ; -- Sergi compró Auriculares Sony WH-1000XM4

INSERT INTO dnis (number, front_img, back_img, Users_user_id)
VALUES 
('12345678A', 'https://example.com/dni/admin_front.jpg', 'https://example.com/dni/admin_back.jpg', 1),
('98765432B', 'https://example.com/dni/rafa_front.jpg', 'https://example.com/dni/rafa_back.jpg', 2),
('45678912C', 'https://example.com/dni/sergi_front.jpg', 'https://example.com/dni/sergi_back.jpg', 3);