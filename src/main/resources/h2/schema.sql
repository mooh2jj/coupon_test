DROP TABLE IF EXISTS coupon;
CREATE TABLE coupon
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

