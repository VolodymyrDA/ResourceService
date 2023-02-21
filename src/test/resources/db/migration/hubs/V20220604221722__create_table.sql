CREATE TABLE IF NOT EXISTS hubs (
                                    resource_id INT NOT NULL,
                                    quantity INT NOT NULL,
                                    hub_id INT,
                                    PRIMARY KEY (resource_id, hub_id)
);