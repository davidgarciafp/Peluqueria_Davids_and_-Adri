package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import basedatos.ConexionBaseDatos;

public class ProductosDAO {

    public List<Productos> mostrarProductos() {
        String sqlMostrarProductos = "SELECT * FROM Productos where producto_activo = true";
        List<Productos> listaProductos = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarProductosStmt = conn.prepareStatement(sqlMostrarProductos);
            ResultSet rs = sqlMostrarProductosStmt.executeQuery()) {
            
            while (rs.next()) {
                Productos productos = new Productos(
                    rs.getInt("id_producto"),
                    rs.getString("codigo_barras"),
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
        String sqlAgregar = "INSERT INTO productos (codigo_barras, nombre_producto, marca, precio_producto, descripcion_producto, cantidad_disponible, producto_gastado, producto_activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarProductos = conn.prepareStatement(sqlAgregar, Statement.RETURN_GENERATED_KEYS)) {
            psAgregarProductos.setString(1, productos.getCodigo_barras());
            psAgregarProductos.setString(2, productos.getNombre_producto());
            psAgregarProductos.setString(3, productos.getMarca());
            psAgregarProductos.setBigDecimal(4, productos.getPrecio_producto());
            psAgregarProductos.setString(5, productos.getDescripcion_producto());
            psAgregarProductos.setInt(6, productos.getCantidad_disponible());
            psAgregarProductos.setInt(7, productos.getProducto_gastado()!= null ? productos.getProducto_gastado() : 0);
            psAgregarProductos.setBoolean(8, true);

            int filasAfectadas = psAgregarProductos.executeUpdate();
            resultado = filasAfectadas > 0; // La insercion es correcta
       
            if (resultado) {
                try (ResultSet generatedKeys = psAgregarProductos.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productos.setId_producto(generatedKeys.getInt(1));
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

    public <BigDecimal> boolean actualizarProductos(Integer idProducto, String codigoBarras,String nombreProducto, String marca, BigDecimal precioProducto, String descripcionProducto,  Integer cantidadDisponible, Integer productoGastado, Boolean productoActivo) {

        String sqlActualizarProductos = "UPDATE productos SET codigo_barras = ?, nombre_producto = ?, marca = ?, precio_producto = ?, descripcion_producto = ?, cantidad_disponible = ?, producto_gastado = ?, producto_activo = ? WHERE id_producto = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarProductos)) {
            pstmt.setString(1, codigoBarras);
            pstmt.setString(2, nombreProducto);
            pstmt.setString(3, marca);
            pstmt.setBigDecimal(4, (java.math.BigDecimal) precioProducto);
            pstmt.setString(5, descripcionProducto);
            pstmt.setInt(6, cantidadDisponible);
            pstmt.setInt(7, productoGastado);
            pstmt.setBoolean(8, productoActivo);
            pstmt.setInt(9, idProducto);

            if (productoGastado < 0 || productoGastado > 2) {
                throw new IllegalArgumentException("El valor de Producto Gastado debe ser 0, 1 o 2");
            }

            
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
                    rs.getString("codigo_barras"),
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
    public List<Productos> mostrarProductosEliminados() {
        String sqlMostrarProductos = "SELECT * FROM Productos where producto_activo = false";
        List<Productos> listaProductos = new ArrayList<>(); // Creamos un array para todos los trabajadores que existan en la BD.
        
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarProductosStmt = conn.prepareStatement(sqlMostrarProductos);
            ResultSet rs = sqlMostrarProductosStmt.executeQuery()) {
            
            while (rs.next()) {
                Productos productos = new Productos(
                    rs.getInt("id_producto"),
                    rs.getString("codigo_barras"),
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
}