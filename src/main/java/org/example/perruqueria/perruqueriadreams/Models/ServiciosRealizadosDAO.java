package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.*;

public class ServiciosRealizadosDAO {
    public boolean agregarServicioRealizado(ServiciosRealizados servicioRealizado) {
        String sqlAgregar = "INSERT INTO servicio_realizados (id_trabajador, id_servicio, id_cliente, dia_servicio_realizados, precio_final, comentario) VALUES (?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement psAgregarServicioRealizado = conn.prepareStatement(sqlAgregar, Statement.RETURN_GENERATED_KEYS)) {
            psAgregarServicioRealizado.setInt(1, servicioRealizado.getIdTrabajador());
            psAgregarServicioRealizado.setInt(2, servicioRealizado.getIdServicio());
            psAgregarServicioRealizado.setInt(3, servicioRealizado.getIdCliente());
            psAgregarServicioRealizado.setString(4, servicioRealizado.getDiaServicioRealizados());
            psAgregarServicioRealizado.setBigDecimal(5, servicioRealizado.getPrecioFinal());
            psAgregarServicioRealizado.setString(6, servicioRealizado.getComentario());

            int filasAfectadas = psAgregarServicioRealizado.executeUpdate();
            resultado = filasAfectadas > 0; // La insercion es correcta

        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return resultado;
    }
}
