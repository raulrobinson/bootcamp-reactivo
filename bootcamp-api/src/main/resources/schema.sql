-- Tabla de tecnologías
CREATE TABLE IF NOT EXISTS bootcamp
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL
);
