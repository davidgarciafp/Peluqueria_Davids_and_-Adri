CREATE DATABASE peluqueria;

USE peluqueria;

CREATE TABLE trabajadores (
    dni VARCHAR(9) PRIMARY KEY, -- Identificador único por cada servicio.
    nombre_trabajador VARCHAR(50), -- Nombre del trabajador.
    apellido_trabajador VARCHAR(50), -- Apellido del trabajador.
    correo_trabajador VARCHAR(255), -- Correo del trabajador.
    telefono_trabajador VARCHAR(15), -- Telefono del trabajador.
    contrasena VARCHAR(4), -- Contraseña del trabajador.
    trabajador_activo BOOLEAN, -- Si el trabajador sigue en la empresa (true) si no (false).
    tipo_trabajador BOOLEAN, -- Jefe (true), traballador normal (false).
    comision_productos DECIMAL(10,2), -- Comision que recibe el trabajador por cada producto.
    comision_servicios DECIMAL(10,2), -- Comision que recibe el trabajador por cada servicio.
);

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único por cada producto.
    nombre_producto VARCHAR(50), -- Nombre del producto.
    marca VARCHAR(50), -- Nombre de la marca.
    precio_producto DECIMAL(10,2), -- Precio del producto.
    descripcion_producto TEXT, -- Que el trabajador pueda escribir lo que quiera.
    cantidad_disponible INT, -- Cuantos productos quedan disponibles.
    producto_gastado INT CHECK (producto_gastado IN (0, 1, 2)), -- Producto Disponible = 0, Producto Gastado = 1, Producto Vendido = 2.
    producto_activo BOOLEAN -- Si el producto sigue activo (true) si no (false).
);

CREATE TABLE servicios (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único por cada servicio.
    precio_base DECIMAL(10,2), -- Precio del corte.
    descripcion_servicio TEXT, -- Que el trabajador pueda escribir lo que quiera del servicio o lo que ha agregado.
    servicio_activo BOOLEAN -- Si el servicios sigue activo (true) si no (false).
);

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único por cada producto.
    nombre_cliente VARCHAR(50), -- Nombre del cliente.
    apellido_cliente VARCHAR(50), -- Apellido del cliente.
    correo_cliente VARCHAR(255),  -- Correo del cliente.
    telefono_cliente VARCHAR(15), -- Telefono del cliente.
    protecion_datos BOOLEAN, -- Check de si ha firmado protección de datos.
    descripcion_cliente TEXT -- Que el trabajador pueda escribir lo que quiera del cliente.
);

CREATE TABLE ventas (
    id_ventas INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único por cada servicio.
    dni VARCHAR(9), -- Referencia a la tabla trabajadores.
    id_producto INT, -- Referencia a la tabla productos.
    id_cliente INT, -- Referencia a la tabla clientes.
    cantidad_vendida INT, -- La cantidad de productos que se han vendido.
    fecha_venta DATE, -- Fecha en la cual se gasto o se compro el producto.
    descripcion_venta TEXT, -- Un comentario que se quiera poner de la venta.
    venta_pagada BOOLEAN, -- Si la venta se ha pagado (true) o esta pendiente (false).
    FOREIGN KEY (dni) REFERENCES trabajadores(dni),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE servicio_realizados (
    servicio_realizados INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único por cada servicio.
    dni VARCHAR(9), -- Referencia a la tabla trabajadores.
    id_servicio INT, -- Referencia a la tabla servicios.
    id_cliente INT, -- Referencia a la tabla clientes.
    hora_inicio DATETIME, -- Fecha y hora en que el corte empezó.
    hora_final DATETIME, -- Fecha y hora en que el corte acabó.
    precio_extra DECIMAL(10,2),
    precio_total DECIMAL(10,2),
    comentario TEXT, -- Comentario del servicio realizado.
    realizado BOOLEAN, -- Si el servicio se ha hecho.
    pagado BOOLEAN, -- Si el servicio se ha pagado (true) o esta pendiente (false).
    FOREIGN KEY (dni) REFERENCES trabajadores(dni),
    FOREIGN KEY (id_servicio) REFERENCES servicios(id_servicio),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);


INSERT INTO clientes (nombre_cliente, apellido_cliente, correo_cliente, telefono_cliente, protecion_datos, descripcion_cliente) 
VALUES ('Otro', '', '', '', false, 'Cliente sin registrar.');