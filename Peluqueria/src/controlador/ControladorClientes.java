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
}