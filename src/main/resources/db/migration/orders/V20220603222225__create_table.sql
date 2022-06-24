create table if not exists orders
(
    id          integer default nextval('order_id_seq'::regclass) not null
        constraint order_pkey
            primary key,
    resource_id integer                                           not null
        constraint resource
            references resources,
    quantity    integer                                           not null,
    user_id     integer,
    hub_id      integer default 0
);

alter table orders
    owner to postgres;