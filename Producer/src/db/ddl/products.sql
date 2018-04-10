create table products
(
  id serial not null
    constraint products_pkey
    primary key,
  name varchar(200) not null,
  description varchar(500),
  created_time timestamp default now() not null,
  updated_time timestamp default now() not null,
  created_by varchar(100) not null,
  updated_by varchar(100) not null
);

create unique index products_id_uindex on products (id);

