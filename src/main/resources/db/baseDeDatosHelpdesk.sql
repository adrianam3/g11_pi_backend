-- Crear tabla persona
CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    cedula VARCHAR(20) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fechacreacion TIMESTAMP NOT NULL
);

-- Crear tabla rol
CREATE TABLE rol (
    id SERIAL PRIMARY KEY,
    nombrerol VARCHAR(50) NOT NULL,
    fechacreacion TIMESTAMP NOT NULL,
    accesos TEXT
);

-- Crear tabla usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    idpersona INT NOT NULL,
    idrol INT NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    correoelectronico VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    fechacreacion TIMESTAMP NOT NULL,
    FOREIGN KEY (idpersona) REFERENCES persona(id),
    FOREIGN KEY (idrol) REFERENCES rol(id)
);

-- Crear tabla sla
CREATE TABLE sla (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tiemporespuesta INT NOT NULL,
    tiemporesolucion INT NOT NULL
);

-- Crear tabla ticket
CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    prioridad INT NOT NULL,
    fechacreacion TIMESTAMP NOT NULL,
    fechaactualizacion TIMESTAMP,
    fechacierre TIMESTAMP,
    usuariocreador INT NOT NULL,
    usuarioasignado INT,
    idsla INT,
    FOREIGN KEY (usuariocreador) REFERENCES usuario(id),
    FOREIGN KEY (usuarioasignado) REFERENCES usuario(id),
    FOREIGN KEY (idsla) REFERENCES sla(id)
);

-- Crear tabla encuesta
CREATE TABLE encuesta (
    id SERIAL PRIMARY KEY,
    idticket INT NOT NULL,
    idusuario INT NOT NULL,
    puntuacion INT NOT NULL,
    comentarios TEXT,
    FOREIGN KEY (idticket) REFERENCES ticket(id),
    FOREIGN KEY (idusuario) REFERENCES usuario(id)
);

-- Crear tabla reporte
CREATE TABLE reporte (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    datos TEXT NOT NULL,
    fechageneracion TIMESTAMP NOT NULL,
    generadopor INT NOT NULL,
    FOREIGN KEY (generadopor) REFERENCES usuario(id)
);

-- Crear tabla baseconocimiento
CREATE TABLE baseconocimiento (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    contenido TEXT NOT NULL,
    fechacreacion TIMESTAMP NOT NULL,
    fechaactualizacion TIMESTAMP,
    autor INT NOT NULL,
    FOREIGN KEY (autor) REFERENCES usuario(id)
);

-- Crear tabla integracion
CREATE TABLE integracion (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    configuracion TEXT NOT NULL
);

-- Crear tabla departamento
CREATE TABLE departamento (
    id SERIAL PRIMARY KEY,
    idusuario INT NOT NULL,
    idticket INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES usuario(id),
    FOREIGN KEY (idticket) REFERENCES ticket(id)
);

-- Crear tabla archivo
CREATE TABLE archivo (
    id SERIAL PRIMARY KEY,
    idticket INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fechasubida TIMESTAMP NOT NULL,
    usuariosubidor INT NOT NULL,
    contenido BYTEA NOT NULL,
    FOREIGN KEY (idticket) REFERENCES ticket(id),
    FOREIGN KEY (usuariosubidor) REFERENCES usuario(id)
);

-- Crear tabla notificacion
CREATE TABLE notificacion (
    id SERIAL PRIMARY KEY,
    idusuario INT NOT NULL,
    idticket INT NOT NULL,
    mensaje TEXT NOT NULL,
    destinatario VARCHAR(100) NOT NULL,
    tiponotificacion VARCHAR(50) NOT NULL,
    fechaenvio TIMESTAMP NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES usuario(id),
    FOREIGN KEY (idticket) REFERENCES ticket(id)
);
