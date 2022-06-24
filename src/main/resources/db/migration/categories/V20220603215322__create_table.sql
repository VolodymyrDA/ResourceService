create table if not exists categories
(
    categorie_id integer default nextval('categories_id_seq'::regclass) not null
        primary key,
    name         varchar(100)                                           not null
);

alter table categories
    owner to postgres;