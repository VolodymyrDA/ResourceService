CREATE TABLE IF NOT EXISTS subcategories
(
    subcategory_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id    INT          NOT NULL,
    name           VARCHAR(100) NOT NULL
);
