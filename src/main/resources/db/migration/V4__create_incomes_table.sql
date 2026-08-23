CREATE TABLE incomes (
                         id UUID PRIMARY KEY,
                         user_id UUID NOT NULL,
                         amount NUMERIC(19, 2) NOT NULL,
                         currency VARCHAR(10) NOT NULL,
                         description VARCHAR(255),
                         category VARCHAR(50) NOT NULL,
                         occurred_at TIMESTAMP WITH TIME ZONE NOT NULL,
                         created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                         updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_incomes_user_id
    ON incomes(user_id);