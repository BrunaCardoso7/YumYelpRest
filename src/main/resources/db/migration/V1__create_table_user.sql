CREATE TABLE users (
    id VARCHAR PRIMARY KEY UNIQUE NOT NULL,
    username VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);