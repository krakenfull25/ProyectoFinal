-- Crear base de datos
DROP DATABASE IF EXISTS bdconcesionario;
CREATE DATABASE bdconcesionario;
USE bdconcesionario;

-- Tabla Marcas
CREATE TABLE marcas (
	idMarca INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	paisOrigen VARCHAR(100),
	fundacion YEAR
);

-- Tabla Coches
CREATE TABLE coches (
	idCoche INT AUTO_INCREMENT PRIMARY KEY,
	modelo VARCHAR(100),
	anio YEAR,
	precio DECIMAL(10,2),
	tipoMotor VARCHAR(50),
	idMarca INT,
	FOREIGN KEY (idMarca) REFERENCES marcas(idMarca)
);

-- Tabla Accesorios
CREATE TABLE accesorios (
	idAccesorio INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	descripcion TEXT
);

-- Tabla intermedia CocheAccesorio (N:M)
CREATE TABLE cocheaccesorio (
	idRegistro INT AUTO_INCREMENT PRIMARY KEY,
	idCoche INT,
	idAccesorio INT,
	FOREIGN KEY (idCoche) REFERENCES coches(idCoche),
	FOREIGN KEY (idAccesorio) REFERENCES accesorios(idAccesorio) ON DELETE SET NULL
);

-- Insertar datos en Marcas
INSERT INTO marcas (nombre, paisOrigen, fundacion) VALUES
('Toyota', 'Japon', 1937),
('Ford', 'EE.UU.', 1903),
('BMW', 'Alemania', 1916);

-- Insertar datos en Coches
INSERT INTO coches (modelo, anio, precio, tipoMotor, idMarca) VALUES
('Corolla', 2022, 22000.00, 'Gasolina', 1),
('Mustang', 2023, 45000.00, 'Gasolina', 2),
('X5', 2021, 60000.00, 'Híbrido', 3);

-- Insertar datos en Accesorios
INSERT INTO accesorios (nombre, descripcion) VALUES
('GPS', 'Sistema de navegación integrado'),
('Cámara Trasera', 'Cámara para asistencia de reversa'),
('Techo Solar', 'Techo corredizo de cristal');

-- Insertar datos en CocheAccesorio
INSERT INTO cocheaccesorio (idCoche,idAccesorio) VALUES
(1, 1),  -- Corolla tiene GPS
(1, 2),  -- Corolla tiene Cámara Trasera
(2, 2),  -- Mustang tiene Cámara Trasera
(2, 3),  -- Mustang tiene Techo Solar
(3, 1),  -- X5 tiene GPS
(3, 2),  -- X5 tiene Cámara Trasera
(3, 3);  -- X5 tiene Techo Solar



