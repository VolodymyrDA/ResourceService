CREATE TABLE IF NOT EXISTS resources
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    subcategorie_id INT          NOT NULL
);
