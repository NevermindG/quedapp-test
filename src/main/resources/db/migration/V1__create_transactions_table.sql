CREATE TABLE transactions (
                              id UUID PRIMARY KEY,
                              user_id UUID NOT NULL,
                              amount NUMERIC(19, 2) NOT NULL,
                              type VARCHAR(20) NOT NULL,
                              description VARCHAR(255),
                              date DATE NOT NULL
);