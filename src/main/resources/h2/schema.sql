DROP TABLE IF EXISTS coupons;
DROP TABLE IF EXISTS users;

CREATE TABLE coupons
(
    id          bigint auto_increment primary key,
    code        VARCHAR(255) not null,
    end_date    DATE         null,
    name        VARCHAR(255) not null,
    start_date  DATE         null,
    status      VARCHAR(255) not null,
    type        VARCHAR(255) not null,
    created_at  TIMESTAMP    null,
    modified_at TIMESTAMP    null
);


CREATE TABLE users
(
    id          bigint auto_increment primary key,
    name        VARCHAR(20) not null,
    email       VARCHAR(30) not null,
    password    VARCHAR(255) not null,
    role        VARCHAR(14) not null,
    created_at  TIMESTAMP    null,
    modified_at TIMESTAMP    null
);

