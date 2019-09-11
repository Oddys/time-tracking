drop database if exists timetracking;

create database timetracking character set utf8mb4;

use timetracking;

create table roles (
    id serial,
    name varchar(45) unique not null,

    primary key (id)
);

create table users (
    id serial,
    login varchar(45) unique not null,
    password varchar(45) not null,
    first_name varchar(90) not null,
    last_name varchar(90) not null,
    role_id bigint unsigned not null,

    primary key (id),
    foreign key fk_role_id (role_id)
    references roles (id)
    on update restrict
    on delete restrict
);

create table activities (
    id serial,
    name varchar(180) unique not null,
    approved boolean not null,

    primary key (id)
);

create table user_activities (
    id serial,
    assigned boolean not null,
    user_id bigint unsigned not null,
    activity_id bigint unsigned not null,

    primary key (id),
    foreign key fk_user_id (user_id)
    references users (id)
    on update restrict
    on delete restrict,
    foreign key fk_activity_id (activity_id)
    references activities (id)
    on update restrict
    on delete restrict
);

create table activity_records (
    id serial,
    activity_date date not null,
    duration bigint unsigned not null,
    user_activity_id bigint unsigned not null,

    primary key (id),
    foreign key fk_user_activity_id (user_activity_id)
    references user_activities (id)
    on update restrict
    on delete restrict
);

begin;

insert into roles (name)
values ('ADMIN'),
    ('USER')
;

insert into users (login, password, first_name, last_name, role_id)
values ('john', 'john', 'John', 'Doe', 1),
    ('ivan', 'ivan', 'Іван', 'Іваненко', 2)
;

insert into activities (name, approved)
values ('Написання статті', true),
      ('Проведення інтерв''ю', true),
      ('Редагування', false)
;

insert into user_activities (assigned, user_id, activity_id)
values (true, 2, 1),
       (false, 2, 2)
;

commit;