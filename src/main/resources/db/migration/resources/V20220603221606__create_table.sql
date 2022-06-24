create table if not exists resources
(
    id              integer default nextval('supply_id_seq'::regclass) not null
        constraint supply_pkey
            primary key,
    name            varchar(100)                                       not null,
    subcategorie_id integer                                            not null
        constraint subcategories
            references subcategories
);

alter table resources
    owner to postgres;