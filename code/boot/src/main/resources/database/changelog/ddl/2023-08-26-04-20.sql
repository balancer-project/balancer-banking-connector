--liquibase formatted sql
--Add Plaid Item model

--changeset juancrrn:1

create table plaid_item (
    id varchar(64) primary key,
    user_id uuid not null,
    institution_id varchar(64) not null,
    access_token varchar(64) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);
