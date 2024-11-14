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

    public boolean anyadirServicios(Servicios servicios) {
        return serviciosDAO.agregarServicios(servicios);
    }

    public <BigDecimal> boolean modificarServicios(Integer idServicio, java.math.BigDecimal precioBase, String descripcionServicio, Boolean servicioActivo) {
        return serviciosDAO.actualizarServicios(idServicio, precioBase, descripcionServicio, servicioActivo);
    }

    public Servicios buscarServicio(Integer idServicio) {
        return serviciosDAO.encontrarServicio(idServicio);
    }

    public List<Servicios> mostrarServiciosEliminados() {
        return serviciosDAO.mostrarServiciosEliminados();
    }
}