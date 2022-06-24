create table if not exists locations
(
    id   integer default nextval('cityes_id_seq'::regclass) not null
        constraint location_pkey
            primary key,
    name varchar(100)                                       not null
);

alter table locations
    owner to postgres;