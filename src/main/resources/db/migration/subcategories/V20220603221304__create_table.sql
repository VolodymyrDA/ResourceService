create table if not exists subcategories
(
    subcategory_id integer default nextval('subcategories_id_seq'::regclass) not null
        primary key,
    category_id    integer                                                   not null
        constraint categories
            references categories,
    name           varchar(100)                                              not null
);

alter table subcategories
    owner to postgres;