CREATE TABLE IF NOT EXISTS product
 (ID INT  AUTO_INCREMENT primary key,
  name VARCHAR(20),
  description varchar(50),
  price decimal,
  weight float(25),
  category int,
  supplier int,
  image_url varchar(50),
  foreign key (category) references product_category,
  foreign key (supplier) references supplier
  );
