--liquibase formatted sql
--Add next cursor field to Plaid Item model

--changeset juancrrn:1

alter table plaid_item add column next_cursor varchar(256) null;
