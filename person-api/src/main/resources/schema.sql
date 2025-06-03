-- Tabla de personas
CREATE TABLE IF NOT EXISTS persons
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL UNIQUE,
    email   VARCHAR(90) NOT NULL,
    age     INT NOT NULL
);

-- Tabla de asociación: un bootcamp puede tener muchas persons
CREATE TABLE IF NOT EXISTS persons_bootcamp
(
    person_id BIGINT NOT NULL,
    bootcamp_id BIGINT NOT NULL,
    PRIMARY KEY (person_id, bootcamp_id),

    -- Relación con tabla persons
    CONSTRAINT fk_person
        FOREIGN KEY (person_id) REFERENCES persons (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);
