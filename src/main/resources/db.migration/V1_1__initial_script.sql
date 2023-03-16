create table if not exists brand
(
    id         bigserial
        primary key,
    brand_name varchar(200) default '342ew'::character varying
);

alter table brand
    owner to postgres;

create table if not exists model
(
    id         bigserial
        primary key,
    model_name varchar(200) not null
        unique,
    brand_id   bigint       not null
        constraint model_brand_id_fk
            references brand
);

alter table model
    owner to postgres;

create table if not exists phone_case
(
    id   bigint       not null
        primary key,
    name varchar(200) not null
);

alter table phone_case
    owner to postgres;

create table if not exists phone_case_model
(
    id            bigserial
        primary key,
    model_id      bigint not null
        constraint phone_case_model_model_id_fk
            references model,
    phone_case_id bigint not null
        constraint phone_case_model_phone_case_id_fk
            references phone_case
);

alter table phone_case_model
    owner to postgres;

create table if not exists phone
(
    id                  bigserial
        primary key,
    date_of_issue       date,
    vin_code            varchar(200) not null
        unique,
    color               varchar(200) not null,
    phone_case_model_id bigint       not null
        constraint phone_phone_case_model_id_fk
            references phone_case_model
);

alter table phone
    owner to postgres;

create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

alter table flyway_schema_history
    owner to postgres;

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

