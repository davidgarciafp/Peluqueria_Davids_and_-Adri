package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class VentasDAO {
    public boolean agregarVenta(Ventas venta) {
        String sqlAgregar = "INSERT INTO ventas (id_trabajador, id_producto, id_cliente, dia_venta, cantidad_vendida, descripcion_venta) VALUES (?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement psAgregarVenta = conn.prepareStatement(sqlAgregar, Statement.RETURN_GENERATED_KEYS)) {
            psAgregarVenta.setInt(1, venta.getIdTrabajador());
            psAgregarVenta.setInt(2, venta.getIdProducto());
            psAgregarVenta.setInt(3, venta.getIdCliente());
            psAgregarVenta.setString(4, venta.getDiaVenta());
            psAgregarVenta.setInt(5, venta.getCantidadVendida());
            psAgregarVenta.setString(6, venta.getDescripcionVenta());

            int filasAfectadas = psAgregarVenta.executeUpdate();
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
