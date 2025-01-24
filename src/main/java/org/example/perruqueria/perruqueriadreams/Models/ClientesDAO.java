package org.example.perruqueria.perruqueriadreams.Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
        String sqlAgregar = "INSERT INTO clientes (nombre_cliente, apellido_cliente, correo_cliente, telefono_cliente, proteccion_datos, descripcion_cliente) VALUES (?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarClientes = conn.prepareStatement(sqlAgregar)) {
            psAgregarClientes.setString(1, clientes.getNombreCliente());
            psAgregarClientes.setString(2, clientes.getApellidoCliente());
            psAgregarClientes.setString(3, clientes.getCorreoCliente());
            psAgregarClientes.setString(4, clientes.getTelefonoCliente());
            psAgregarClientes.setBoolean(5, clientes.isProteccionDatos());
            psAgregarClientes.setString(6, clientes.getDescripcionCliente());

            int filasAfectadas = psAgregarClientes.executeUpdate();
            resultado = filasAfectadas > 0; // La insercion es correcta

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

        String sqlActualizarClientes = "UPDATE clientes SET nombre_cliente = ?, apellido_cliente = ?, correo_cliente = ?, telefono_cliente = ?, proteccion_datos = ?, descripcion_cliente = ? WHERE id_cliente = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarClientes)) {
            
            pstmt.setString(1, nombreCliente);
            pstmt.setString(2, apellidoCliente);
            pstmt.setString(3, correoCliente);
            pstmt.setString(4, telefonoCliente);
            pstmt.setBoolean(5, proteccionDatos);
            pstmt.setString(6, descripcionCliente);
            pstmt.setInt(7, idCliente);


            
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

    public Clientes encontrarCliente(int idCliente) {
        Clientes clientes = null;
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        
        try (Connection conn = ConexionBaseDatos.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                clientes = new Clientes(
                    rs.getInt("id_cliente"),
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

    public String obtenerDescripcionCliente(Integer idCliente) {

        String sqlObtenerDescripcion = "SELECT descripcion_cliente FROM clientes WHERE id_cliente = ?";
        String descripcion = "";

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlObtenerDescripcion)) {

            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                descripcion = rs.getString("descripcion_cliente");
            }

        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return descripcion;
    }

    public boolean actualizarDescripcionFicha(Integer idCliente, String descripcion) {

        String sqlActualizarClientes = "UPDATE clientes SET descripcion_cliente = ? WHERE id_cliente = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sqlActualizarClientes)) {

            pstmt.setString(1, descripcion);
            pstmt.setInt(2, idCliente);

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

    public List<Object[]> obtenerFicha(int idCliente) {
        String sqlMostrarClientes = "SELECT c.id_cliente, sr.dia_servicio_realizados, s.descripcion_servicio, v.dia_venta, p.nombre_producto " +
                                    "FROM clientes c " +
                                    "LEFT JOIN servicio_realizados sr ON c.id_cliente = sr.id_cliente " +
                                    "LEFT JOIN servicios s ON sr.id_servicio = s.id_servicio " +
                                    "LEFT JOIN ventas v ON c.id_cliente = v.id_cliente " +
                                    "LEFT JOIN productos p ON v.id_producto = p.id_producto " +
                                    "WHERE c.id_cliente = ?";

        List<Clientes> listaClientes = new ArrayList<>();
        List<Servicios> listaServicios = new ArrayList<>();
        List<ServiciosRealizados> listaServiciosRealizados = new ArrayList<>();
        List<Ventas> listaVentas = new ArrayList<>();
        List<Productos> listaProductos = new ArrayList<>();

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement sqlMostrarClientesStmt = conn.prepareStatement(sqlMostrarClientes)) {
            sqlMostrarClientesStmt.setInt(1, idCliente);
            ResultSet rs = sqlMostrarClientesStmt.executeQuery();

            while (rs.next()) {
                // Agregar cliente
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                listaClientes.add(cliente);

                // Agregar servicios realizados
                if (rs.getDate("dia_servicio_realizados") != null) {
                    ServiciosRealizados servicioRealizado = new ServiciosRealizados(
                        rs.getInt("id_servicio_realizados"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_servicio"),
                        rs.getString("dia_servicio_realizados")
                    );
                    listaServiciosRealizados.add(servicioRealizado);
                }

                // Agregar ventas
                if (rs.getDate("dia_venta") != null) {
                    Ventas venta = new Ventas(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_producto"),
                        rs.getString("dia_venta")
                    );
                    listaVentas.add(venta);
                }

                // Agregar servicios
                if (rs.getString("descripcion_servicio") != null) {
                    Servicios servicio = new Servicios();
                    servicio.setDescripcionServicio(rs.getString("descripcion_servicio"));
                    listaServicios.add(servicio);
                }

                // Agregar productos
                if (rs.getString("nombre_producto") != null) {
                    Productos producto = new Productos();
                    producto.setNombreProducto(rs.getString("nombre_producto"));
                    listaProductos.add(producto);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        List<Object[]> resultado = new ArrayList<>();
        resultado.add(listaClientes.toArray());
        resultado.add(listaServicios.toArray());
        resultado.add(listaServiciosRealizados.toArray());
        resultado.add(listaVentas.toArray());
        resultado.add(listaProductos.toArray());

        return resultado;
    }
}
