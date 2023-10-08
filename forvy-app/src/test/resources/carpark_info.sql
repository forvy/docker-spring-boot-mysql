CREATE TABLE IF NOT EXISTS carpark_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carpark_number VARCHAR(255) NOT NULL,
    total_lots INT NOT NULL,
    available_lots INT NOT NULL,
    update_datetime TIMESTAMP NOT NULL
);

INSERT INTO carpark_info (carpark_number, total_lots, available_lots, update_datetime)
VALUES
    ('CP001', 100, 50, '2023-01-01 12:00:00'),
    ('CP002', 200, 100, '2023-01-01 12:00:00');
