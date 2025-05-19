-- Tabla de capacidades
CREATE TABLE IF NOT EXISTS capabilities
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de asociación: un bootcamp puede tener muchas capacidades
CREATE TABLE IF NOT EXISTS capabilities_bootcamps
(
    capability_id BIGINT NOT NULL,
    bootcamp_id BIGINT NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (bootcamp_id, capability_id),

    -- Relación con tabla capabilities
    CONSTRAINT fk_capability
        FOREIGN KEY (capability_id) REFERENCES capabilities (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);