CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
