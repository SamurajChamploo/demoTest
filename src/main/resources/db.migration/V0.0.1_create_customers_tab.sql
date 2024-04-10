CREATE TABLE customers.customers (
    id BIGINT PRIMARY KEY NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    cnum BIGINT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    phone_number TEXT,
    created_date TIMESTAMP NOT NULL DEFAULT now(),
    update_date TIMESTAMP NOT NULL DEFAULT now()
);