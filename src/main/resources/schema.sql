create table if not exists Computer_Order (
  id identity,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);

create table if not exists Hardware (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null,
  primary key (id)
);

create table if not exists Computer (
  id IDENTITY,
  name varchar(50) not null,
  computer_order bigint not null,
  computer_order_key bigint not null,
  created_at timestamp not null,
  foreign key (computer_order) references Computer_Order(id)
);

create table if not exists Hardware_Ref (
  hardware varchar(4) not null,
  computer bigint not null,
  computer_key bigint not null,
  foreign key (hardware) references Hardware(id)
);
    