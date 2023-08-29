--liquibase formatted sql
--Add initialUpdateDone and historicalUpdateDone fields to Plaid Item model

--changeset juancrrn:1

alter table plaid_item add column initial_update_done boolean not null default false;
alter table plaid_item add column historical_update_done boolean not null default false;
