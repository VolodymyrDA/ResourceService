create table if not exists roles
(
    role_id   integer not null
        primary key,
    role_name varchar(30)
);

alter table roles
    owner to postgres;