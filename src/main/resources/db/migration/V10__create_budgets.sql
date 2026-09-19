CREATE TABLE budgets (
                         id UUID PRIMARY KEY,
                         user_id UUID NOT NULL,
                         category VARCHAR(50) NOT NULL,
                         amount NUMERIC(19, 2) NOT NULL,
                         currency VARCHAR(10) NOT NULL,
                         year INTEGER NOT NULL,
                         month INTEGER NOT NULL,
                         created_at TIMESTAMPTZ NOT NULL,
                         updated_at TIMESTAMPTZ NOT NULL,

                         CONSTRAINT fk_budgets_user
                             FOREIGN KEY (user_id)
                                 REFERENCES users(id),

                         CONSTRAINT uk_budget_user_category_period
                             UNIQUE (
                                     user_id,
                                     category,
                                     year,
                                     month
                                 ),

                         CONSTRAINT chk_budget_amount_positive
                             CHECK (amount > 0),

                         CONSTRAINT chk_budget_month
                             CHECK (month BETWEEN 1 AND 12)
    );

CREATE INDEX idx_budgets_user_period
    ON budgets(user_id, year, month);