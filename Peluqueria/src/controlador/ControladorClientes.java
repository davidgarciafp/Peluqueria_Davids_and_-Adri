package controlador;

import java.util.List;

import modelo.Clientes;
import modelo.ClientesDAO;

public class ControladorClientes {
    private ClientesDAO clientesDAO;

    // Constructor
    public ControladorClientes() {
        this.clientesDAO = new ClientesDAO();
    }

    public List<Clientes> mostrarClientes() {
        return clientesDAO.mostrarClientes();
    }
    public boolean anyadirClientes(Clientes clientes) {
        return clientesDAO.agregarClientes(clientes);
    }

    public boolean modificarClientes(Integer idCliente, String nombreCliente, String apellidoCliente, String correoCliente, String telefonoCliente,  Boolean proteccionDatos, String descripcionCliente) {
        return clientesDAO.actualizarClientes(idCliente, nombreCliente, apellidoCliente, correoCliente, telefonoCliente, proteccionDatos, descripcionCliente);
    }

    public Clientes buscarClientes(Integer idCliente) {
        return clientesDAO.encontrarClientes(idCliente);
    }
}