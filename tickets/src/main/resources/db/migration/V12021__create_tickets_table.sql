CREATE TABLE IF NOT EXISTS events (
                                      id BIGSERIAL PRIMARY KEY,
                                      status VARCHAR(50) NOT NULL,
                                      created_at TIMESTAMP,
                                      updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tickets (
                                       id BIGSERIAL PRIMARY KEY,
                                       customer_id BIGINT,
                                       event_id BIGINT NOT NULL,
                                       type VARCHAR(100),
                                       price NUMERIC(10, 2),
                                       created_at TIMESTAMP,
                                       updated_at TIMESTAMP,
                                       CONSTRAINT fk_ticket_event FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
                                       CONSTRAINT fk_ticket_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE

                                   );
