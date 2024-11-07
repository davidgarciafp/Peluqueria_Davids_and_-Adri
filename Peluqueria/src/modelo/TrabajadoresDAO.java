package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;



import basedatos.ConexionBaseDatos;

public class TrabajadoresDAO {

    public List<Trabajadores> mostrarTrabajadores() {
        String sqlMostrarTrabajadores = "SELECT * FROM trabajadores WHERE trabajador_activo = true";
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
        String sqlAgregar = "INSERT INTO trabajadores (nombre_trabajador, apellido_trabajador, correo_trabajador, telefono_trabajador, contrasena, trabajador_activo, tipo_trabajador, comision_producto, comision_servicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            psAgregarTrabajador.setBigDecimal(8, trabajadores.getComisionProducto());
            psAgregarTrabajador.setBigDecimal(9, trabajadores.getComisionServicio());


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

    public boolean actualizarTrabajadores(Integer idTrabajador, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevoTelefono, String nuevaContrasena, boolean trabajadorActivo, Boolean trabajadorTipo, java.math.BigDecimal comisionProducto,  java.math.BigDecimal comisionServicio) {

        String sqlActualizarTrabajadores = "UPDATE trabajadores SET nombre_trabajador = ?, apellido_trabajador = ?, correo_trabajador = ?, telefono_trabajador = ?, contrasena = ?, trabajador_activo = ?, tipo_trabajador = ?, comision_producto = ?, comision_servicio = ? WHERE id_trabajador = ?";
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
}