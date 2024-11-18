package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost/peluqueria";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() throws SQLException {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException error) {
            System.out.println("Error en la conexión con la base de datos. Error: " + error.getMessage());
        }
        return conexion;
    }
}