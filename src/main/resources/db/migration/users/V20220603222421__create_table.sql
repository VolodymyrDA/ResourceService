create table if not exists users
(
    id          integer default nextval('user_id_seq'::regclass) not null
        constraint id
            primary key,
    username    varchar(100)                                     not null,
    phone       varchar(15)                                      not null,
    date        timestamp                                        not null,
    description varchar(500)                                     not null,
    location_id integer
        constraint location
            references locations,
    password    varchar(72),
    active      boolean,
    role_id     integer
        constraint roles
            references roles
);

alter table users
    owner to postgres;

