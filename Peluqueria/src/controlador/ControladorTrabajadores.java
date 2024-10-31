package controlador;

import java.util.List;

import modelo.Trabajadores;
import modelo.TrabajadoresDAO;

public class ControladorTrabajadores {
    private TrabajadoresDAO trabajadoresDAO;

    // Constructor
    public ControladorTrabajadores() {
        this.trabajadoresDAO = new TrabajadoresDAO();
    }

    public List<Trabajadores> mostrarTrabajadores() {
        return trabajadoresDAO.mostrarTrabajadores();
    }

    public boolean comprovarDNI(String dni) {
        return trabajadoresDAO.dniExiste(dni);
    }

    public Trabajadores identificarTrabajador(int id_trabajador, String contrasena) {
        return trabajadoresDAO.identificarTrabajador(id_trabajador, contrasena);
    }

    public boolean anyadirTrabajadores(Trabajadores trabajadores) {
        return trabajadoresDAO.agregarTrabajadores(trabajadores);
    }

    public <BigDecimal> boolean modificarTrabajadores(int id_trabajador, String dni, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevoTelefono, String nuevaContrasena, boolean trabajadorActivo, Boolean trabajadorTipo, java.math.BigDecimal comisionProductos, java.math.BigDecimal comisionServicios) {
        return trabajadoresDAO.actualizarTrabajadores(id_trabajador, dni, nuevoNombre, nuevoApellido, nuevoCorreo, nuevoTelefono, nuevaContrasena, trabajadorActivo, trabajadorTipo, comisionProductos, comisionServicios);
    }

    public Trabajadores buscarTrabajador(int id_trabajador) {
        return trabajadoresDAO.encontrarTrabajador(id_trabajador);
    }
}