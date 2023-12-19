-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS client
(
    id serial NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS appointment
(
    id serial NOT NULL,
    date date NOT NULL,
    "time" time without time zone NOT NULL,
    client_id integer NOT NULL,
    service_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS services
(
    id serial NOT NULL,
    service_name character varying(50) NOT NULL,
    price integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS appointment
    ADD FOREIGN KEY (client_id)
    REFERENCES client (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS appointment
    ADD FOREIGN KEY (service_id)
    REFERENCES services (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;