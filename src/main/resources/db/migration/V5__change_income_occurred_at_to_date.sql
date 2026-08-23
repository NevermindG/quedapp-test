ALTER TABLE incomes
ALTER COLUMN occurred_at TYPE DATE
USING occurred_at::DATE;