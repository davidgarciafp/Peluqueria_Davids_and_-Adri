package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basedatos.ConexionBaseDatos;

public class ProductosDAO {

    public List<Productos> mostrarProductos() {
        String sqlMostrarProductos = "SELECT * FROM Productos";
        List<Productos> listaProductos = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarProductosStmt = conn.prepareStatement(sqlMostrarProductos);
            ResultSet rs = sqlMostrarProductosStmt.executeQuery()) {
            
            while (rs.next()) {
                Productos productos = new Productos(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("marca"),
                    rs.getBigDecimal("precio_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getInt("cantidad_disponible"),
                    rs.getInt("producto_gastado"),
                    rs.getBoolean("producto_activo")
                );
                listaProductos.add(productos); // Els agrreguem al array.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaProductos;
    }


    public boolean agregarProductos(Productos productos) {
        String sqlAgregar = "INSERT INTO productos (id_producto, nombre_producto, marca, precio_producto, descripcion_producto, cantidad_disponible, producto_gastado, producto_activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarProductos = conn.prepareStatement(sqlAgregar)) {
            psAgregarProductos.setInt(1, productos.getId_producto());
            psAgregarProductos.setString(2, productos.getNombre_producto());
            psAgregarProductos.setString(3, productos.getMarca());
            psAgregarProductos.setBigDecimal(4, productos.getPrecio_producto());
            psAgregarProductos.setString(5, productos.getDescripcion_producto());
            psAgregarProductos.setInt(7, productos.getCantidad_disponible());
            psAgregarProductos.setInt(8, productos.getProducto_gastado());
            psAgregarProductos.setBoolean(9, productos.isProducto_activo());

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

    public <BigDecimal> boolean actualizarProductos(Integer idProducto, String nombreProducto, String marca, BigDecimal precioProducto, String descripcionProducto,  Integer cantidadDisponible, Integer productoGastado, Boolean productoActivo) {

        String sqlActualizarServicios = "UPDATE productos SET id_producto = ?, nombre_producto = ?, marca = ?, precio_producto = ?, descripcion_producto = ?, cantidad_disponible = ?, producto_gastado = ?, producto_activo WHERE id_producto = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarServicios)) {
            
            pstmt.setInt(1, idProducto);
            pstmt.setString(2, nombreProducto);
            pstmt.setString(3, marca);
            pstmt.setBigDecimal(4, (java.math.BigDecimal) precioProducto);
            pstmt.setString(5, descripcionProducto);
            pstmt.setInt(6, cantidadDisponible);
            pstmt.setInt(7, productoGastado);
            pstmt.setBoolean(8, productoActivo);

            
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

    public Productos encontrarProducto(int idProducto) {
        Productos productos = null;
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        
        try (Connection conn = ConexionBaseDatos.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                productos = new Productos(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("marca"),
                    rs.getBigDecimal("precio_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getInt("cantidad_disponible"),
                    rs.getInt("producto_gastado"),
                    rs.getBoolean("producto_activo")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
}