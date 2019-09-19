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
    password varchar(64) not null,
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
--     approved boolean not null,

    primary key (activity_id)
);

create table user_activities (
    user_activity_id serial,  -- FIXME It is unnecessary, can be deleted here and from entity
    assigned boolean not null,
    status_change_requested boolean not null,
    user_id bigint unsigned not null,
    activity_id bigint unsigned not null,

    primary key (user_id, activity_id),
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
    activity_record_id serial, -- FIXME It is unnecessary, can be deleted here and from entity
    activity_date date not null,
    duration bigint unsigned not null,
    user_activity_id bigint unsigned not null,

    primary key (activity_date, user_activity_id),
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
values ('john', '96d9632f363564cc3032521409cf22a852f2032eec099ed5967c0d000cec607a', 'John', 'Doe', 1),
    ('ivan', 'cd0b9452fc376fc4c35a60087b366f70d883fc901524daf1f122fbd319384f6a', 'Іван', 'Іваненко', 2)
--     ('mark', '6201eb4dccc956cc4fa3a78dca0c2888177ec52efd48f125df214f046eb43138', 'Марк', 'Марченко', 1)
;

-- insert into activities (activity_name, approved)
-- values ('Написання статті', true),
--       ('Проведення інтерв''ю', true),
--       ('Редагування', true),
--       ('Наповнення стрічки новин', false)
-- ;
insert into activities (activity_name)
values ('Написання статті'),
      ('Проведення інтерв''ю'),
      ('Редагування'),
      ('Наповнення стрічки новин')
;

insert into user_activities (assigned, status_change_requested, user_id, activity_id)
values (true, false, 2, 1),
       (false, true, 2, 2)
;

insert into activity_records (activity_date, duration, user_activity_id)
values ('2019-01-01', 120, 1),
       ('2019-01-02', 80, 1),
       ('2019-01-03', 300, 1),
       ('2019-01-04', 50, 1),
       ('2019-01-05', 40, 1),
       ('2019-01-06', 180, 1),
       ('2019-01-07', 600, 1)
;

commit;