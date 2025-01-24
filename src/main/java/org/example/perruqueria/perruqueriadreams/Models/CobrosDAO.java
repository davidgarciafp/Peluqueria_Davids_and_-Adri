package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CobrosDAO {
    public boolean agregarCobro(Cobros cobro) {
        String sqlAgregar = "INSERT INTO cobros (id_trabajador, id_cliente, dia_cobro, importe, efectivo, tarjeta, bizum, deudas, pagado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement psAgregarCobro = conn.prepareStatement(sqlAgregar, Statement.RETURN_GENERATED_KEYS)) {
            psAgregarCobro.setInt(1, cobro.getIdTrabajador());
            psAgregarCobro.setInt(2, cobro.getIdCliente());
            psAgregarCobro.setString(3, cobro.getDiaCobro());
            psAgregarCobro.setBigDecimal(4, cobro.getImporte());
            psAgregarCobro.setBigDecimal(5, cobro.getEfectivo());
            psAgregarCobro.setBigDecimal(6, cobro.getTarjeta());
            psAgregarCobro.setBigDecimal(7, cobro.getBizum());
            psAgregarCobro.setBigDecimal(8, cobro.getDeudas());
            psAgregarCobro.setBoolean(9, cobro.isPagado());
            int filasAfectadas = psAgregarCobro.executeUpdate();
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
