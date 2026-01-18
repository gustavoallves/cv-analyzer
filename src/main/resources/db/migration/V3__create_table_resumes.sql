CREATE TABLE resumes (
                         id BIGSERIAL PRIMARY KEY,
                         file_name VARCHAR(255) NOT NULL,
                         extracted_text TEXT,
                         analysis TEXT,
                         skills TEXT,
                         improvements TEXT,
                         analyzed_at TIMESTAMP,
                         candidate_id BIGINT,
                         CONSTRAINT fk_candidate FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);