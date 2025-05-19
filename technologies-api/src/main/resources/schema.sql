-- Tabla de tecnologías
CREATE TABLE IF NOT EXISTS technologies
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de asociación: una capacidad puede tener muchas tecnologías
CREATE TABLE IF NOT EXISTS technologies_capabilities
(
    technology_id BIGINT NOT NULL,
    capability_id BIGINT NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (technology_id, capability_id),

    -- Relación con tabla technologies
    CONSTRAINT fk_technology
        FOREIGN KEY (technology_id) REFERENCES technologies (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);
