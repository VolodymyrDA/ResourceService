CREATE TABLE IF NOT EXISTS orders
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    resource_id INT NOT NULL,
    quantity    INT NOT NULL,
    user_id     INT,
    hub_id      INT DEFAULT 0
);
