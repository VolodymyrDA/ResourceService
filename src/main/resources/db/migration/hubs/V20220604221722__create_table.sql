create table if not exists hubs
(
    key         integer default nextval('hubs_seq'::regclass) not null
        primary key,
    resource_id integer not null
        constraint resource
            references resources,
    quantity    integer not null,
    hub_id      integer
        constraint users
            references users
);

alter table hubs
    owner to postgres;