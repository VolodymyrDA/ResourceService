CREATE TABLE IF NOT EXISTS users
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100) NOT NULL,
    phone       VARCHAR(15)  NOT NULL,
    date        TIMESTAMP    NOT NULL,
    description VARCHAR(500) NOT NULL,
    location_id INT,
    password    VARCHAR(72),
    active      BOOLEAN,
    role_id     INT
);


