package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class ServiciosDAO {

    public List<Servicios> mostrarServicios() {
        String sqlMostrarServicios = "SELECT * FROM Servicios WHERE servicio_activo = true";
        List<Servicios> listaServicios = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarServiciosStmt = conn.prepareStatement(sqlMostrarServicios);
            ResultSet rs = sqlMostrarServiciosStmt.executeQuery()) {
            
            while (rs.next()) {
                Servicios servicios = new Servicios(
                    rs.getInt("id_servicio"),
                    rs.getBigDecimal("precio_base"),
                    rs.getString("descripcion_servicio"),
                    rs.getBoolean("servicio_activo")
                );
                listaServicios.add(servicios); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaServicios;
    }

    public boolean agregarServicios(Servicios servicios) {
        String sqlAgregar = "INSERT INTO servicios (precio_base, descripcion_servicio, servicio_activo) VALUES (?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement psAgregarServicio = conn.prepareStatement(sqlAgregar, Statement.RETURN_GENERATED_KEYS)) { 
            psAgregarServicio.setBigDecimal(1, servicios.getPrecio_base());
            psAgregarServicio.setString(2, servicios.getDescripcion_servicio());
            psAgregarServicio.setBoolean(3, servicios.isServicio_activo());
            // Ejecutar la inserción
            int filasAfectadas = psAgregarServicio.executeUpdate();
        
            // Verificar si la inserción fue exitosa
            resultado = filasAfectadas > 0;
            if (resultado) {
                try (ResultSet generatedKeys = psAgregarServicio.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        servicios.setId_servicio(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public <BigDecimal> boolean actualizarServicios(Integer idServicio, BigDecimal precioBase, String descripcionServicio, Boolean servicioActivo) {
        String sqlActualizarServicios = "UPDATE servicios SET precio_base = ?, descripcion_servicio = ?, servicio_activo = ? WHERE id_servicio = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarServicios)) {
            
            pstmt.setBigDecimal(1,(java.math.BigDecimal) precioBase);
            pstmt.setString(2, descripcionServicio);
            pstmt.setBoolean(3, servicioActivo);
            pstmt.setInt(4, idServicio);


            int filasActualizadas = pstmt.executeUpdate();
            resultado = filasActualizadas > 0;

        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public Servicios encontrarServicio(int idServicio) {

        Servicios servicios = null;
        String sql = "SELECT * FROM servicios WHERE id_servicio = ?";
        
        try (Connection conn = ConexionBaseDatos.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1,idServicio);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                servicios = new Servicios(
                    rs.getInt("id_servicio"),
                    rs.getBigDecimal("precio_base"),
                    rs.getString("descripcion_servicio"),
                    rs.getBoolean("servicio_activo")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return servicios;
    }

    public List<Servicios> mostrarServiciosEliminados() {
        String sqlMostrarServicios = "SELECT * FROM Servicios WHERE servicio_activo = false";
        List<Servicios> listaServicios = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarServiciosStmt = conn.prepareStatement(sqlMostrarServicios);
            ResultSet rs = sqlMostrarServiciosStmt.executeQuery()) {
            
            while (rs.next()) {
                Servicios servicios = new Servicios(
                    rs.getInt("id_servicio"),
                    rs.getBigDecimal("precio_base"),
                    rs.getString("descripcion_servicio"),
                    rs.getBoolean("servicio_activo")
                );
                listaServicios.add(servicios); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaServicios;
    }
}
