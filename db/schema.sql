CREATE DATABASE cars;

create table cars
(
    id    serial primary key,
    brand varchar(255) not null,
    type  varchar(255) not null
);

create table photos
(
    id   serial primary key,
    name varchar(255) not null
);

create table users
(
    id       serial primary key,
    email    varchar(255) not null unique,
    name     varchar(255) not null,
    password varchar(255) not null
);

create table posts
(
    id          serial primary key,
    description varchar(255) not null,
    sale        boolean      not null,
    author_id   int      not null references users (id),
    car_id      int      not null references cars (id)
);

create table posts_photos
(
    post_id   int not null references posts (id),
    photos_id int not null unique references photos (id)
);

create table users_posts
(
    user_id  int not null references users (id),
    posts_id int not null unique references posts (id)
);

