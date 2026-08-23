ALTER TABLE expenses
ALTER COLUMN occurred_at TYPE DATE
USING occurred_at::DATE;