CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);