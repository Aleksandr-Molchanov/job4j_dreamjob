CREATE TABLE if not exists post (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT
);

CREATE TABLE if not exists candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT
);

CREATE TABLE if not exists user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    password TEXT
);