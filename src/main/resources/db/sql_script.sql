drop database if exists timetracking;

create database timetracking character set utf8mb4;

use timetracking;

create table roles (
    role_id serial,
    role_name varchar(45) unique not null,

    primary key (role_id)
);

create table users (
    user_id serial,
    login varchar(45) unique not null,
    password varchar(45) not null,
    first_name varchar(90) not null,
    last_name varchar(90) not null,
    role_id bigint unsigned not null,

    primary key (user_id),
    foreign key fk_role_id (role_id)
    references roles (role_id)
    on update restrict
    on delete restrict
);

create table activities (
    activity_id serial,
    activity_name varchar(180) unique not null,
    approved boolean not null,

    primary key (activity_id)
);

create table user_activities (
    user_activity_id serial,
    assigned boolean not null,
    user_id bigint unsigned not null,
    activity_id bigint unsigned not null,

    primary key (user_activity_id),
    foreign key fk_user_id (user_id)
    references users (user_id)
    on update restrict
    on delete restrict,
    foreign key fk_activity_id (activity_id)
    references activities (activity_id)
    on update restrict
    on delete restrict
);

create table activity_records (
    activity_record_id serial,
    activity_date date not null,
    duration bigint unsigned not null,
    user_activity_id bigint unsigned not null,

    primary key (activity_record_id),
    foreign key fk_user_activity_id (user_activity_id)
    references user_activities (user_activity_id)
    on update restrict
    on delete restrict
);

begin;

insert into roles (role_name)
values ('ADMIN'),
    ('USER')
;

insert into users (login, password, first_name, last_name, role_id)
values ('john', 'john', 'John', 'Doe', 1),
    ('ivan', 'ivan', 'Іван', 'Іваненко', 2)
;

insert into activities (activity_name, approved)
values ('Написання статті', true),
      ('Проведення інтерв''ю', true),
      ('Редагування', false)
;

insert into user_activities (assigned, user_id, activity_id)
values (true, 2, 1),
       (false, 2, 2)
;

insert into activity_records (activity_date, duration, user_activity_id)
values ('2019-01-01', 8, 1),
       ('2019-01-02', 8, 1),
       ('2019-01-03', 8, 1),
       ('2019-01-04', 8, 1),
       ('2019-01-05', 8, 1),
       ('2019-01-06', 8, 1),
       ('2019-01-07', 8, 1)
;

commit;