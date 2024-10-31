package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basedatos.ConexionBaseDatos;

public class ClientesDAO{

    public List<Clientes> mostrarClientes() {
        String sqlMostrarClientes = "SELECT * FROM clientes";
        List<Clientes> listaClientes = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarClientesStmt = conn.prepareStatement(sqlMostrarClientes);
            ResultSet rs = sqlMostrarClientesStmt.executeQuery()) {
            
            while (rs.next()) {
                Clientes clientes = new Clientes(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("apellido_cliente"),
                    rs.getString("correo_cliente"),
                    rs.getString("telefono_cliente"),
                    rs.getBoolean("proteccion_datos"),
                    rs.getString("descripcion_cliente")
                );
                listaClientes.add(clientes); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaClientes;
    }


    public boolean agregarClientes(Clientes clientes) {
        String sqlAgregar = "INSERT INTO clientes (id_cliente, nombre_cliente, apellido_cliente, correo_cliente, telefono_cliente, proteccion_datos, descripcion_cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarClientes = conn.prepareStatement(sqlAgregar)) {
            psAgregarClientes.setInt(1, clientes.getId_cliente());
            psAgregarClientes.setString(2, clientes.getNombre_cliente());
            psAgregarClientes.setString(3, clientes.getApellido_cliente());
            psAgregarClientes.setString(4, clientes.getCorreo_cliente());
            psAgregarClientes.setString(5, clientes.getTelefono_cliente());
            psAgregarClientes.setBoolean(7, clientes.isProteccion_datos());
            psAgregarClientes.setString(8, clientes.getDescripcion_cliente());

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

    public boolean actualizarClientes(Integer idCliente, String nombreCliente, String apellidoCliente, String correoCliente, String telefonoCliente,  Boolean proteccionDatos, String descripcionCliente) {

        String sqlActualizarClientes = "UPDATE clientes SET id_cliente = ?, nombre_cliente = ?, apellido_cliente = ?, correo_cliente = ?, telefono_cliente = ?, proteccion_datos = ?, descripcion_cliente = ? WHERE id_cliente = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarClientes)) {
            
            pstmt.setInt(1, idCliente);
            pstmt.setString(2, nombreCliente);
            pstmt.setString(3, apellidoCliente);
            pstmt.setString(4, correoCliente);
            pstmt.setString(5, telefonoCliente);
            pstmt.setBoolean(6, proteccionDatos);
            pstmt.setString(7, descripcionCliente);

            
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

    public Clientes encontrarClientes(int idCliente) {
        Clientes clientes = null;
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        
        try (Connection conn = ConexionBaseDatos.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                clientes = new Clientes(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_cliente"),
                    rs.getString("apellido_cliente"),
                    rs.getString("correo_cliente"),
                    rs.getString("telefono_cliente"),
                    rs.getBoolean("proteccion_datos"),
                    rs.getString("descripcion_cliente")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
