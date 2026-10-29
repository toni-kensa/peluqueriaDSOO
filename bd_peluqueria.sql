CREATE DATABASE IF NOT EXISTS peluqueria;
USE peluqueria;

CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    contacto VARCHAR(100),
    categoria VARCHAR(50),
    promociones BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS servicios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    duracion INT NOT NULL,
    precio DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS turnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    servicio_id BIGINT NOT NULL,
    fecha VARCHAR(10) NOT NULL,
    hora VARCHAR(5) NOT NULL,
    estado VARCHAR(20),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (servicio_id) REFERENCES servicios(id)
);
