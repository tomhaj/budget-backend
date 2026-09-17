CREATE TABLE account (
     id         UUID            PRIMARY KEY,
     name       VARCHAR         NOT NULL,
     on_budget  BOOLEAN         NOT NULL,
     balance    NUMERIC(19, 2)  NOT NULL
);
