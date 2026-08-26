CREATE TABLE savings_contributions (
    id UUID PRIMARY KEY,
    savings_goal_id UUID NOT NULL,
    user_id UUID NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    date DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_savings_contribution_goal
        FOREIGN KEY (savings_goal_id)
        REFERENCES savings_goals(id),

    CONSTRAINT fk_savings_contribution_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE INDEX idx_savings_contributions_goal
ON savings_contributions(savings_goal_id);

CREATE INDEX idx_savings_contributions_user_date
ON savings_contributions(user_id, date);