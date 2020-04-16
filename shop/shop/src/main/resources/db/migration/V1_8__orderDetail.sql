CREATE TABLE IF NOT EXISTS order_detail
 (orders int,
  product int,
  quantity int,
  foreign key (product) references product,
  foreign key (orders) references orders,
  primary key (orders, product)
  );