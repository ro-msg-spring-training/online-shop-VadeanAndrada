CREATE TABLE IF NOT EXISTS revenue
 (ID int AUTO_INCREMENT primary key,
  location int,
  datee date,
  sum decimal,
  foreign key (location) references location
  );
