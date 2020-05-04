CREATE TABLE IF NOT EXISTS product_category (
ID INT AUTO_INCREMENT primary key,
NAME VARCHAR(20) unique,
description varchar(100));

CREATE TABLE IF NOT EXISTS supplier (
ID INT  AUTO_INCREMENT primary key,
NAME VARCHAR(20) unique);

CREATE TABLE IF NOT EXISTS customer (
ID INT  AUTO_INCREMENT primary key,
first_name VARCHAR(20),
last_name varchar(50),
username varchar(60),
password varchar(200),
email_address varchar(50));

CREATE TABLE IF NOT EXISTS product
 (ID INT  AUTO_INCREMENT primary key,
  name VARCHAR(50) unique,
  description varchar(50),
  price decimal,
  weight float(25),
  category_id int,
  supplier_id int,
  image_url varchar(100),
  foreign key (category_id) references product_category,
  foreign key (supplier_id) references supplier
  );

CREATE TABLE IF NOT EXISTS location
 (ID INT  AUTO_INCREMENT primary key,
  name VARCHAR(50),
  country varchar(50),
  city VARCHAR(50),
  county VARCHAR(50),
  street_address VARCHAR(50),
  );

CREATE TABLE IF NOT EXISTS revenue
 (ID int AUTO_INCREMENT primary key,
  location_id int,
  `date` date,
  sum decimal,
  foreign key (location_id) references location
  );

CREATE TABLE IF NOT EXISTS orders
 (ID INT  AUTO_INCREMENT primary key,
  shipped_form int,
  customer_id int,
  created_at TIMESTAMP,
  country varchar(50),
  city varchar(50),
  county varchar(50),
  street_address varchar(100),
  foreign key (shipped_form) references location,
  foreign key (customer_id) references customer
  );

CREATE TABLE IF NOT EXISTS order_detail
 (order_id int,
  product_id int,
  quantity int,
  foreign key (product_id) references product,
  foreign key (order_id) references orders,
  primary key (order_id, product_id)
  );

CREATE TABLE IF NOT EXISTS stock
 (product_id int,
  location_id int,
  quantity int,
    foreign key (product_id) references product,
  foreign key (location_id) references location,
  PRIMARY KEY (LOCATION_ID, PRODUCT_ID)
  );