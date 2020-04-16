CREATE TABLE IF NOT EXISTS orders
 (ID INT  AUTO_INCREMENT primary key,
  shipped_form int,
  customer int,
  created_at TIMESTAMP,
  country varchar(50),
  city varchar(50),
  county varchar(50),
  street_address varchar(50),
  foreign key (shipped_form) references location,
  foreign key (customer) references customer
  );