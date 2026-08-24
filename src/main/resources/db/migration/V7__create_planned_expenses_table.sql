CREATE TABLE planned_expenses (
                                  id UUID PRIMARY KEY,
                                  user_id UUID NOT NULL,
                                  amount NUMERIC(19, 2) NOT NULL,
                                  currency VARCHAR(10) NOT NULL,
                                  description VARCHAR(255),
                                  category VARCHAR(50) NOT NULL,
                                  due_date DATE NOT NULL,
                                  status VARCHAR(20) NOT NULL,
                                  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                                  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

                                  CONSTRAINT fk_planned_expenses_user
                                      FOREIGN KEY (user_id)
                                          REFERENCES users(id)
);

CREATE INDEX idx_planned_expenses_user_id
    ON planned_expenses(user_id);

CREATE INDEX idx_planned_expenses_user_due_date
    ON planned_expenses(user_id, due_date);