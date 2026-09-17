CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    stock_quantity INTEGER NOT NULL,
    title VARCHAR(255),
    author VARCHAR(255),
    category VARCHAR(255),
    isbn VARCHAR(255) UNIQUE,
    publisher VARCHAR(255),
    published_year INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    borrowed_books_count INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT REFERENCES books (id),
    student_id BIGINT REFERENCES students (id),
    loan_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP,
    limit_date TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);
