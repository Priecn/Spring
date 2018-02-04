DROP SCHEMA public CASCADE;
commit;
CREATE SCHEMA public;
CREATE table category(
	id SERIAL,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	CONSTRAINT pk_category_id PRIMARY KEY (id)
);

INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_1.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Television', 'This is description for Television category!', 'CAT_2.png', true);
INSERT INTO category (name, description,image_url,is_active) VALUES ('Mobile', 'This is description for Mobile category!', 'CAT_3.png', true);

select * from category;
delete from category where name='ax';

CREATE TABLE user_detail (
	id SERIAL,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY (id)
);

drop table user_detail cascade;

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Virat', 'Kohli', 'ADMIN', true, '$2y$10$MgtCmg2rm.LWb3vyjZ90I.hJV3r4CeeXDMVQ6sSAd8.85.3xjlz8i', 'vk@gmail.com', '8888888888');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Ravindra', 'Jadeja', 'SUPPLIER', true, '$2y$10$H3s5dwPVJhJJlOc1T2nVieEevz1/nmGb5OFFyad3jc44dlamv9DF6', 'rj@gmail.com', '9999999999');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Ravichandra', 'Ashwin', 'SUPPLIER', true, '$2y$10$0BBi8olm0uUmmVMokmSQ1O9C6o.vQfhSXX2JpAC3OkF8oa9DUgZFq', 'ra@gmail.com', '7777777777');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Prince', 'Kumar', 'USER', true, '$2y$10$GsIPAAXBCJxJgg9RjJ9nveezrD6ORLxulYhkDcFWb0zYSWc4cNyha', 'pk@gmail.com', '6666666666');

update user_detail set role = 'ADMIN' where email = 'vk@gmail.com';
select * from user_detail;

CREATE TABLE product (
	id SERIAL,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail (id)	
);	


INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 3, 2, 0, 0);

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 32000, 2, true, 3, 3, 0, 0);

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 3, 2, 0, 0);

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES ('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 1, 3, 0, 0 );

update product set is_active=false where id= 33;

select * from product limit 6;
select * from cart;
select * from address;
select * from user_detail;

CREATE TABLE cart_line (
	id SERIAL,
	cart_id int,
	total DECIMAL(10, 2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10, 2),
	is_available boolean,
	CONSTRAINT fk_cartline_cart_id FOREIGN KEY (cart_id) REFERENCES cart (id),
	CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id) REFERENCES product (id),
	CONSTRAINT pk_cartline_id PRIMARY KEY (id)
);
select * from cart_line;
commit;