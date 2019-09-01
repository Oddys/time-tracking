drop database if exists timetracking;

create database timetracking character set utf8mb4;

use timetracking;

create table roles (
    id int unsigned auto_increment,
    name varchar(45) unique not null,

    primary key (id)
);

create table users (
    id int unsigned auto_increment,
    login varchar(45) unique not null,
    password varchar(45) not null,
    first_name varchar(90) not null,
    last_name varchar(90) not null,
    role_id int unsigned not null,

    primary key (id),
    foreign key fk_role_id (role_id)
    references roles (id)
    on update restrict
    on delete restrict
);

create table activities (
    id int unsigned auto_increment,
    name varchar(180) unique not null,
    approved boolean not null,

    primary key (id)
);

create table user_activities (
    id int unsigned auto_increment,
    assigned boolean not null,
    user_id int unsigned not null,
    activity_id int unsigned not null,

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
    id int unsigned auto_increment,
    activity_date date not null,
    duration int unsigned not null,
    user_activity_id int unsigned not null,

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

commit;