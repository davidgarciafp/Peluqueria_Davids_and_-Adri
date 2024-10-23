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
}