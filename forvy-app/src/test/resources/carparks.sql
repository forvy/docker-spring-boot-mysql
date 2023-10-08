CREATE TABLE IF NOT EXISTS carparks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_park_no VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL
);

INSERT INTO carparks (car_park_no, address, latitude, longitude)
VALUES
    ('CP001', 'Address 1', 1.5, 1.5),
    ('CP002', 'Address 2', 3.0, 4.0);
