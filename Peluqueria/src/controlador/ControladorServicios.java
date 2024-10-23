package controlador;

import java.util.List;

import modelo.Servicios;
import modelo.ServiciosDAO;

public class ControladorServicios {
    private ServiciosDAO serviciosDAO;

    // Constructor
    public ControladorServicios() {
        this.serviciosDAO = new ServiciosDAO();
    }

    public List<Servicios> mostrarServicios() {
        return serviciosDAO.mostrarServicios();
    }
}