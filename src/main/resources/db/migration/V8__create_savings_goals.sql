CREATE TABLE savings_goals (
                               id UUID PRIMARY KEY,
                               user_id UUID NOT NULL,
                               name VARCHAR(150) NOT NULL,
                               target_amount NUMERIC(19,2) NOT NULL,
                               current_amount NUMERIC(19,2) NOT NULL,
                               currency VARCHAR(10) NOT NULL,
                               target_date DATE,
                               status VARCHAR(20) NOT NULL,
                               created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                               updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

                               CONSTRAINT fk_savings_goals_user
                                   FOREIGN KEY (user_id)
                                       REFERENCES users(id)
);

CREATE INDEX idx_savings_goals_user_id
    ON savings_goals(user_id);