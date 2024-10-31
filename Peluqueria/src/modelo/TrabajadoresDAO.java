package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basedatos.ConexionBaseDatos;

public class TrabajadoresDAO {

    public List<Trabajadores> mostrarTrabajadores() {
        String sqlMostrarTrabajadores = "SELECT * FROM Trabajadores";
        List<Trabajadores> listaTrabajadores = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarTrabajadoresStmt = conn.prepareStatement(sqlMostrarTrabajadores);
            ResultSet rs = sqlMostrarTrabajadoresStmt.executeQuery()) {
            
            while (rs.next()) {
                Trabajadores trabajadores = new Trabajadores(
                    rs.getInt("id_trabajador"),
                    rs.getString("dni"),
                    rs.getString("nombre_trabajador"),
                    rs.getString("apellido_trabajador"),
                    rs.getString("correo_trabajador"),
                    rs.getString("telefono_trabajador"),
                    rs.getString("contrasena"),
                    rs.getBoolean("trabajador_activo"),
                    rs.getBoolean("tipo_trabajador"),
                    rs.getBigDecimal("comision_productos"),
                    rs.getBigDecimal("comision_servicios")
                );
                listaTrabajadores.add(trabajadores); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaTrabajadores;
    }

    public boolean dniExiste(String dni) {
        boolean resultado = false;
        String sqlDniExiste = "SELECT COUNT(*) FROM Trabajadores WHERE dni = ?";

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sqlDniExiste)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = rs.getInt(1) > 1; // Retorna true si el DNI existe
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public Trabajadores identificarTrabajador(int id_trabajador, String contrasena) {
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
                    rs.getString("dni"),
                    rs.getString("nombre_trabajador"),
                    rs.getString("apellido_trabajador"),
                    rs.getString("correo_trabajador"),
                    rs.getString("telefono_trabajador"),
                    rs.getString("contrasena"),
                    rs.getBoolean("trabajador_activo"),
                    rs.getBoolean("tipo_trabajador"),
                    rs.getBigDecimal("comision_productos"),
                    rs.getBigDecimal("comision_servicios")
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
        String sqlAgregar = "INSERT INTO trabajadores (id_trabajador, dni, nombre_trabajador, apellido_trabajador, correo_trabajador, telefono_trabajador, contrasena, trabajador_activo, tipo_trabajador, comision_productos, comision_servicios) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarTrabajador = conn.prepareStatement(sqlAgregar)) {
            psAgregarTrabajador.setInt(1, trabajadores.getId_trabajador());
            psAgregarTrabajador.setString(2, trabajadores.getDni());
            psAgregarTrabajador.setString(3, trabajadores.getNombreTrabajador());
            psAgregarTrabajador.setString(4, trabajadores.getApellidoTrabajador());
            psAgregarTrabajador.setString(5, trabajadores.getCorreoTrabajador());
            psAgregarTrabajador.setString(6, trabajadores.getTelefonoTrabajador());
            psAgregarTrabajador.setString(7, trabajadores.getContrasena());
            psAgregarTrabajador.setBoolean(8, trabajadores.isTrabajadorActivo());
            psAgregarTrabajador.setBoolean(9, trabajadores.isTipoTrabajador());
            psAgregarTrabajador.setBigDecimal(10, trabajadores.getComision_productos());
            psAgregarTrabajador.setBigDecimal(11, trabajadores.getComision_servicios());
            psAgregarTrabajador.executeUpdate();

            resultado = true; // La insercion es correcta
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public <BigDecimal> boolean actualizarTrabajadores(int id_trabajador, String dni, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevoTelefono, String nuevaContrasena, boolean trabajadorActivo, Boolean trabajadorTipo, java.math.BigDecimal comisionProductos, java.math.BigDecimal comisionServicios) {
        String sqlActualizarTrabajadores = "UPDATE trabajadores SET dni = ? nombre_trabajador = ?, apellido_trabajador = ?, correo_trabajador = ?, telefono_trabajador = ?, contrasena = ?, trabajador_activo = ?, tipo_trabajador = ?, comision_productos = ?, comision_servicios = ? WHERE id_trabajador = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarTrabajadores)) {
            
            pstmt.setString(1, dni);
            pstmt.setString(2, nuevoNombre);
            pstmt.setString(3, nuevoApellido);
            pstmt.setString(4, nuevoCorreo);
            pstmt.setString(5, nuevoTelefono);
            pstmt.setString(6, nuevaContrasena);
            pstmt.setBoolean(7, trabajadorActivo);
            pstmt.setBoolean(8, trabajadorTipo);
            pstmt.setBigDecimal(9, (java.math.BigDecimal) comisionProductos);
            pstmt.setBigDecimal(10, (java.math.BigDecimal) comisionServicios);
            pstmt.setInt(11, id_trabajador);
            
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

    public Trabajadores encontrarTrabajador(int id_trabajador) {
        Trabajadores trabajadores = null;
        String sql = "SELECT * FROM trabajadores WHERE id_trabajador = ?";
        
        try (Connection conn = ConexionBaseDatos.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1, id_trabajador);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                trabajadores = new Trabajadores(
                    rs.getInt("id_trabajador"),
                    rs.getString("dni"),
                    rs.getString("nombre_trabajador"),
                    rs.getString("apellido_trabajador"),
                    rs.getString("correo_trabajador"),
                    rs.getString("telefono_trabajador"),
                    rs.getString("contrasena"),
                    rs.getBoolean("trabajador_activo"),
                    rs.getBoolean("tipo_trabajador"),
                    rs.getBigDecimal("comision_productos"),
                    rs.getBigDecimal("comision_servicios")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trabajadores;
    }
}