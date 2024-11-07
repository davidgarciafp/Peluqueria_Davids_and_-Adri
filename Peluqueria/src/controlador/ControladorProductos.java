package controlador;

import java.util.List;

import modelo.Productos;
import modelo.ProductosDAO;

public class ControladorProductos {
    private ProductosDAO productosDAO;

    // Constructor
    public ControladorProductos() {
        this.productosDAO = new ProductosDAO();
    }

    public List<Productos> mostrarProductos() {
        return productosDAO.mostrarProductos();
    }
    public boolean anyadirProductos(Productos productos) {
        return productosDAO.agregarProductos(productos);
    }

    public <BigDecimal> boolean modificarProductos(Integer idProducto, String codigoBarras,String nombreProducto, String marca, BigDecimal precioProducto, String descripcionProducto,  Integer cantidadDisponible, Integer productoGastado, Boolean productoActivo) {
        ProductosDAO productosDAO = new ProductosDAO();
        return productosDAO.actualizarProductos(idProducto, codigoBarras, nombreProducto, marca, precioProducto, descripcionProducto, cantidadDisponible, productoGastado, productoActivo);
    }

    public Productos buscarProducto(Integer idProducto) {
        return productosDAO.encontrarProducto(idProducto);
    }
    public List<Productos> mostrarProductosEliminados() {
        return productosDAO.mostrarProductosEliminados();
    }
}