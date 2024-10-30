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

    public <BigDecimal> boolean modificarProductos(Integer idProducto, String nombreProducto, String marca, BigDecimal precioProducto, String descripcionProducto,  Integer cantidadDisponible, Integer productoGastado, Boolean productoActivo) {
        return productosDAO.actualizarProductos(idProducto, nombreProducto, marca, precioProducto, descripcionProducto, cantidadDisponible, productoGastado, productoActivo);
    }

    public Productos buscarProducto(Integer idProducto) {
        return productosDAO.encontrarProducto(idProducto);
    }
}