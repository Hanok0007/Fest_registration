-- Create the database
CREATE DATABASE IF NOT EXISTS fest_registration;
USE fest_registration;

-- user table
CREATE TABLE IF NOT EXISTS user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('ADMIN', 'ORGANIZER', 'PARTICIPANT') NOT NULL DEFAULT 'PARTICIPANT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- events table
CREATE TABLE IF NOT EXISTS events (
    events_id INT AUTO_INCREMENT PRIMARY KEY,
    events_name VARCHAR(100) NOT NULL,
    events_date DATETIME NOT NULL,
    registration_deadline DATETIME,
    events_location VARCHAR(100) NOT NULL,
    max_participants INT NOT NULL,
    current_participants INT DEFAULT 0,
    fee DECIMAL(10, 2) DEFAULT 0.00,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES user(user_id),
    INDEX idx_events_date (events_date),
    INDEX idx_location (events_location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Participants table
CREATE TABLE IF NOT EXISTS participants (
    participant_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    events_id INT NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    college VARCHAR(100),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_status ENUM('PENDING', 'COMPLETED', 'FAILED') DEFAULT 'PENDING',
    special_requirements TEXT,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (events_id) REFERENCES events(events_id) ON DELETE CASCADE,
    UNIQUE KEY uk_user_events (user_id, events_id),
    INDEX idx_events (events_id),
    INDEX idx_payment_status (payment_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Payments table
CREATE TABLE IF NOT EXISTS payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    participant_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method ENUM('CARD', 'UPI', 'NET_BANKING', 'CASH') NOT NULL,
    transaction_id VARCHAR(100),
    payment_status ENUM('PENDING', 'SUCCESS', 'FAILED', 'REFUNDED') NOT NULL DEFAULT 'PENDING',
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    receipt_sent BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (participant_id) REFERENCES participants(participant_id) ON DELETE CASCADE,
    INDEX idx_transaction (transaction_id),
    INDEX idx_payment_date (payment_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Reports table
CREATE TABLE IF NOT EXISTS reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    events_id INT NOT NULL,
    total_participants INT NOT NULL,
    total_revenue DECIMAL(10, 2) NOT NULL,
    attendance_count INT,
    generated_by INT,
    generated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    report_data JSON,
    FOREIGN KEY (events_id) REFERENCES events(events_id) ON DELETE CASCADE,
    FOREIGN KEY (generated_by) REFERENCES user(user_id),
    INDEX idx_events (events_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- events categories
CREATE TABLE IF NOT EXISTS events_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- events-category mapping
CREATE TABLE IF NOT EXISTS events_category_mapping (
    events_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (events_id, category_id),
    FOREIGN KEY (events_id) REFERENCES events(events_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES events_categories(category_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Check all tables were created
SHOW TABLES;

-- View structure of any table
DESCRIBE user;
DESCRIBE events;
DESCRIBE participants;

-- Create a dedicated user (optional)
CREATE USER 'fest_user'@'localhost' IDENTIFIED BY '1234';

-- Grant full access to the database
GRANT ALL PRIVILEGES ON fest_registration.* TO 'fest_user'@'localhost';

-- Apply changes
FLUSH PRIVILEGES;

-- Sample admin user (password: admin123)
INSERT INTO user (username, password, email, role)
VALUES ('admin', '$2a$10$xJwL5v.nKZ5hYw6bJQqZNuYvjQ7s6fz6Jk9XbLd1Rc3g2sV1XyW0e', 'admin@fest.com', 'ADMIN');