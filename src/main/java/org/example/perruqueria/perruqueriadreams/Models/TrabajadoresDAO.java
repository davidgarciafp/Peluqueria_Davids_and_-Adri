package org.example.perruqueria.perruqueriadreams.Models;

import org.example.perruqueria.perruqueriadreams.Models.ConexionBaseDatos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrabajadoresDAO {

    public List<Trabajadores> mostrarTrabajadores() {
        String sqlMostrarTrabajadores = "SELECT * FROM trabajadores WHERE trabajador_activo = true AND id_trabajador != 1";
        List<Trabajadores> listaTrabajadores = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarTrabajadoresStmt = conn.prepareStatement(sqlMostrarTrabajadores);
            ResultSet rs = sqlMostrarTrabajadoresStmt.executeQuery()) {
            
            while (rs.next()) {
                Trabajadores trabajadores = new Trabajadores(
                    rs.getInt("id_trabajador"),
                    rs.getString("nombre_trabajador"),
                    rs.getString("apellido_trabajador"),
                    rs.getString("correo_trabajador"),
                    rs.getString("telefono_trabajador"),
                    rs.getString("contrasena"),
                    rs.getBoolean("trabajador_activo"),
                    rs.getBoolean("tipo_trabajador"),
                    rs.getBigDecimal("comision_producto"),
                    rs.getBigDecimal("comision_servicio")

                );
                listaTrabajadores.add(trabajadores); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return listaTrabajadores;
    }

    public Trabajadores identificarTrabajador(Integer id_trabajador, String contrasena) {
        String sqlComprovarTrabajadores = "SELECT * FROM trabajadores WHERE id_trabajador = ? AND contrasena = ?";
        Trabajadores trabajadores = null;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement psComprovarTrabajadores = conn.prepareStatement(sqlComprovarTrabajadores)) {

            psComprovarTrabajadores.setInt(1, id_trabajador);
            psComprovarTrabajadores.setString(2, contrasena);
            ResultSet rs = psComprovarTrabajadores.executeQuery();
            if (rs.next()) {
                // Creamos un nuevo objeto trabajador.
                trabajadores = new Trabajadores(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre_trabajador"),
                        rs.getString("apellido_trabajador"),
                        rs.getString("correo_trabajador"),
                        rs.getString("telefono_trabajador"),
                        rs.getString("contrasena"),
                        rs.getBoolean("trabajador_activo"),
                        rs.getBoolean("tipo_trabajador"),
                        rs.getBigDecimal("comision_producto"),
                        rs.getBigDecimal("comision_servicio")


                );
            } else {
                trabajadores = null;
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return trabajadores;
    }

    public boolean agregarTrabajadores(Trabajadores trabajadores) {
        String sqlAgregar = "INSERT INTO trabajadores (nombre_trabajador, apellido_trabajador, correo_trabajador, telefono_trabajador, contrasena, trabajador_activo, tipo_trabajador) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarTrabajador = conn.prepareStatement(sqlAgregar, Statement.RETURN_GENERATED_KEYS)) {
            psAgregarTrabajador.setString(1, trabajadores.getNombreTrabajador());
            psAgregarTrabajador.setString(2, trabajadores.getApellidoTrabajador());
            psAgregarTrabajador.setString(3, trabajadores.getCorreoTrabajador());
            psAgregarTrabajador.setString(4, trabajadores.getTelefonoTrabajador());
            psAgregarTrabajador.setString(5, trabajadores.getContrasena());
            psAgregarTrabajador.setBoolean(6, trabajadores.isTrabajadorActivo());
            psAgregarTrabajador.setBoolean(7, trabajadores.isTipoTrabajador());


            int filasAfectadas = psAgregarTrabajador.executeUpdate();
            resultado = filasAfectadas > 0;
            if (resultado) {
                try (ResultSet generatedKeys = psAgregarTrabajador.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        trabajadores.setIdTrabajador(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                throw new RuntimeException("Error al agregar trabajador: " + ex.getMessage());

            }
        }
        return resultado;
    }

    public boolean actualizarTrabajadores(Integer idTrabajador, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevoTelefono, String nuevaContrasena, boolean trabajadorActivo, Boolean trabajadorTipo, BigDecimal comisionServicio, BigDecimal comisionProducto) {

        String sqlActualizarTrabajadores = "UPDATE trabajadores SET nombre_trabajador = ?, apellido_trabajador = ?, correo_trabajador = ?, telefono_trabajador = ?, contrasena = ?, trabajador_activo = ?, tipo_trabajador = ?, comision_servicio = ?, comision_producto = ? WHERE id_trabajador = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarTrabajadores)) {
            
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoApellido);
            pstmt.setString(3, nuevoCorreo);
            pstmt.setString(4, nuevoTelefono);
            pstmt.setString(5, nuevaContrasena);
            pstmt.setBoolean(6, trabajadorActivo);
            pstmt.setBoolean(7, trabajadorTipo);
            pstmt.setBigDecimal(8, (java.math.BigDecimal) comisionProducto);
            pstmt.setBigDecimal(9, (java.math.BigDecimal) comisionServicio);
            pstmt.setInt(10, idTrabajador);
            
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

    public Trabajadores encontrarTrabajador(Integer idTrabajador) {
        Trabajadores trabajadores = null;
        String sql = "SELECT * FROM trabajadores WHERE id_trabajador = ?";
        
        try (Connection conn = ConexionBaseDatos.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1, idTrabajador);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                trabajadores = new Trabajadores(
                    rs.getInt("id_trabajador"),
                    rs.getString("nombre_trabajador"),
                    rs.getString("apellido_trabajador"),
                    rs.getString("correo_trabajador"),
                    rs.getString("telefono_trabajador"),
                    rs.getString("contrasena"),
                    rs.getBoolean("trabajador_activo"),
                    rs.getBoolean("tipo_trabajador"),
                    rs.getBigDecimal("comision_producto"),
                    rs.getBigDecimal("comision_servicio")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    public List<Trabajadores> mostrarTrabajadoresEliminados() {
        String sqlMostrarTrabajadores = "SELECT * FROM trabajadores WHERE trabajador_activo = false";
        List<Trabajadores> listaTrabajadores = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarTrabajadoresStmt = conn.prepareStatement(sqlMostrarTrabajadores);
            ResultSet rs = sqlMostrarTrabajadoresStmt.executeQuery()) {
            
            while (rs.next()) {
                Trabajadores trabajadores = new Trabajadores(
                    rs.getInt("id_trabajador"),
                    rs.getString("nombre_trabajador"),
                    rs.getString("apellido_trabajador"),
                    rs.getString("correo_trabajador"),
                    rs.getString("telefono_trabajador"),
                    rs.getString("contrasena"),
                    rs.getBoolean("trabajador_activo"),
                    rs.getBoolean("tipo_trabajador"),
                    rs.getBigDecimal("comision_producto"),
                    rs.getBigDecimal("comision_servicio")

                );
                listaTrabajadores.add(trabajadores); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaTrabajadores;
    }


    public Trabajadores identificarDreams(String contrasena) {
        String sqlComprovarTrabajadores = "SELECT * FROM trabajadores WHERE id_trabajador = 1 AND contrasena = ?";
        Trabajadores trabajadores = null;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement psComprovarTrabajadores = conn.prepareStatement(sqlComprovarTrabajadores)) {

            psComprovarTrabajadores.setString(1, contrasena);
            ResultSet rs = psComprovarTrabajadores.executeQuery();
            if (rs.next()) {
                // Creamos un nuevo objeto trabajador.
                trabajadores = new Trabajadores(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre_trabajador"),
                        rs.getString("apellido_trabajador"),
                        rs.getString("correo_trabajador"),
                        rs.getString("telefono_trabajador"),
                        rs.getString("contrasena"),
                        rs.getBoolean("trabajador_activo"),
                        rs.getBoolean("tipo_trabajador"),
                        rs.getBigDecimal("comision_producto"),
                        rs.getBigDecimal("comision_servicio")


                );
            } else {
                trabajadores = null;
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return trabajadores;
    }

    public int ventasDia(int idTrabajador, String diaVenta) {
        if (diaVenta == null || diaVenta.isEmpty()) {
            diaVenta = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        }
        String sql = "SELECT COUNT(*) AS totalVentas FROM ventas WHERE id_trabajador = ? AND dia_venta = ?";
        int totalVentas = 0;

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaVenta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalVentas = rs.getInt("totalVentas");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalVentas;
    }
    public int ventasSemana(int idTrabajador) {
        String sql = "SELECT COUNT(*) AS totalVentasSemana FROM ventas WHERE id_trabajador = ? AND dia_venta >= ?";
        int totalVentasSemana = 0;
        String diaInicio = LocalDate.now().minusDays(6).format(DateTimeFormatter.ISO_DATE); // Fecha de hace 7 días incluyendo hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalVentasSemana = rs.getInt("totalVentasSemana");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalVentasSemana;
    }

    public int ventas30Dias(int idTrabajador) {
        String sql = "SELECT COUNT(*) AS totalVentas30Dias FROM ventas WHERE id_trabajador = ? AND dia_venta >= ?";
        int totalVentas30Dias = 0;
        String diaInicio = LocalDate.now().minusDays(29).format(DateTimeFormatter.ISO_DATE); // Fecha de hace 30 días incluyendo hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalVentas30Dias = rs.getInt("totalVentas30Dias");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalVentas30Dias;
    }

    public int serviciosHoy(int idTrabajador) {
        String sql = "SELECT COUNT(*) AS totalServiciosHoy FROM servicio_realizados WHERE id_trabajador = ? AND dia_servicio_realizados = ?";
        int totalServiciosHoy = 0;
        String diaHoy = LocalDate.now().format(DateTimeFormatter.ISO_DATE); // Fecha de hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaHoy);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalServiciosHoy = rs.getInt("totalServiciosHoy");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalServiciosHoy;
    }
    public int serviciosSemana(int idTrabajador) {
        String sql = "SELECT COUNT(*) AS totalServiciosSemana FROM servicio_realizados WHERE id_trabajador = ? AND dia_servicio_realizados >= ?";
        int totalServiciosSemana = 0;
        String diaInicio = LocalDate.now().minusDays(6).format(DateTimeFormatter.ISO_DATE); // Fecha de hace 7 días incluyendo hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalServiciosSemana = rs.getInt("totalServiciosSemana");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalServiciosSemana;
    }

    public int servicios30Dias(int idTrabajador) {
        String sql = "SELECT COUNT(*) AS totalServicios30Dias FROM servicio_realizados WHERE id_trabajador = ? AND dia_servicio_realizados >= ?";
        int totalServicios30Dias = 0;
        String diaInicio = LocalDate.now().minusDays(29).format(DateTimeFormatter.ISO_DATE); // Fecha de hace 30 días incluyendo hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalServicios30Dias = rs.getInt("totalServicios30Dias");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalServicios30Dias;
    }

    public BigDecimal importeHoy(int idTrabajador) {
        String sql = "SELECT SUM(importe) AS totalImporteHoy FROM cobros WHERE id_trabajador = ? AND dia_cobro = ?";
        BigDecimal totalImporteHoy = BigDecimal.ZERO;
        String diaHoy = LocalDate.now().format(DateTimeFormatter.ISO_DATE); // Fecha de hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaHoy);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalImporteHoy = rs.getBigDecimal("totalImporteHoy");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalImporteHoy;
    }

    public BigDecimal importeSemana(int idTrabajador) {
        String sql = "SELECT SUM(importe) AS totalImporteSemana FROM cobros WHERE id_trabajador = ? AND dia_cobro >= ?";
        BigDecimal totalImporteSemana = BigDecimal.ZERO;
        String diaInicio = LocalDate.now().minusDays(6).format(DateTimeFormatter.ISO_DATE); // Fecha de hace 7 días incluyendo hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalImporteSemana = rs.getBigDecimal("totalImporteSemana");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalImporteSemana;
    }

    public BigDecimal importe30Dias(int idTrabajador) {
        String sql = "SELECT SUM(importe) AS totalImporte30Dias FROM cobros WHERE id_trabajador = ? AND dia_cobro >= ?";
        BigDecimal totalImporte30Dias = BigDecimal.ZERO;
        String diaInicio = LocalDate.now().minusDays(29).format(DateTimeFormatter.ISO_DATE); // Fecha de hace 30 días incluyendo hoy

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.setString(2, diaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalImporte30Dias = rs.getBigDecimal("totalImporte30Dias");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta. Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return totalImporte30Dias;
    }
}