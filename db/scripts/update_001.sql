CREATE TABLE if not exists posts (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP,
    visible BOOLEAN,
    city_id INT REFERENCES city (id)
);

CREATE TABLE if not exists candidates (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP,
    visible BOOLEAN,
    city_id INT REFERENCES city (id),
    photo bytea
);

CREATE TABLE if not exists users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email VARCHAR(255) UNIQUE,
    password TEXT
);

CREATE TABLE IF NOT EXISTS city (
    id SERIAL PRIMARY KEY,
    name TEXT
);

INSERT INTO city(name) VALUES ('Москва');
INSERT INTO city(name) VALUES ('СПб');
INSERT INTO city(name) VALUES ('Екб');