CREATE TABLE IF NOT EXISTS stock
 (product int,
  location int,
  quantity int,
    foreign key (product) references product,
  foreign key (location) references location,
  PRIMARY KEY (LOCATION, PRODUCT)
  );