ALTER TABLE hubs
    DROP COLUMN key;
alter table hubs
    add constraint hubs_pk
        primary key (resource_id, hub_id);