CREATE TABLE jobs (
                      id BIGSERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      requirements TEXT,
                      created_at TIMESTAMP,
                      level VARCHAR(50)
);