package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public List<Cobros> obtenerCobros() {
        List<Cobros> listaCobros = new ArrayList<>();
        String sql = "SELECT c.dia_cobro, c.id_cliente, cl.nombre_cliente, " +
                     "c.importe, c.deudas, c.pagado " +
                     "FROM cobros c " +
                     "LEFT JOIN clientes cl ON c.id_cliente = cl.id_cliente WHERE c.pagado = 1";
    
        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Cobros cobro = new Cobros();
    
                // Asignamos los valores directamente al objeto Cobros
                cobro.setDiaCobro(rs.getString("dia_cobro"));
                cobro.setIdCliente(rs.getInt("id_cliente")); // Almacena el ID del cliente
                cobro.setImporte(rs.getBigDecimal("importe"));
                cobro.setDeudas(rs.getBigDecimal("deudas"));
                cobro.setPagado(rs.getBoolean("pagado"));
    
                // Campo derivado para mostrar el nombre del cliente (no guardado en la BD)
                cobro.setNombreClienteTemporal(rs.getString("nombre_cliente"));
    
                listaCobros.add(cobro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return listaCobros;
    }
    public List<Cobros> obtenerCobrosSinPagar() {
        List<Cobros> listaCobros = new ArrayList<>();
        String sql = "SELECT c.dia_cobro, c.id_cliente, cl.nombre_cliente, " +
                     "c.importe, c.deudas, c.pagado " +
                     "FROM cobros c " +
                     "LEFT JOIN clientes cl ON c.id_cliente = cl.id_cliente WHERE c.pagado = 0";
    
        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Cobros cobro = new Cobros();
    
                // Asignamos los valores directamente al objeto Cobros
                cobro.setDiaCobro(rs.getString("dia_cobro"));
                cobro.setIdCliente(rs.getInt("id_cliente")); // Almacena el ID del cliente
                cobro.setImporte(rs.getBigDecimal("importe"));
                cobro.setDeudas(rs.getBigDecimal("deudas"));
                cobro.setPagado(rs.getBoolean("pagado"));
    
                // Campo derivado para mostrar el nombre del cliente (no guardado en la BD)
                cobro.setNombreClienteTemporal(rs.getString("nombre_cliente"));
    
                listaCobros.add(cobro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return listaCobros;
    }

    public boolean modificarCobro(Cobros cobro) {
        String sql = "UPDATE cobros SET dia_cobro = ?, importe = ?, deudas = ?, pagado = ? WHERE id_cobros = ?";
        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, cobro.getDiaCobro());
            pstmt.setBigDecimal(2, cobro.getImporte());
            pstmt.setBigDecimal(3, cobro.getDeudas());
            pstmt.setBoolean(4, cobro.isPagado());
            pstmt.setInt(5, cobro.getIdCobros()); // Asegúrate de que el modelo tiene este ID
    
            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}
