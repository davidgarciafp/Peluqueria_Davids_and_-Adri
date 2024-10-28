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

    public Trabajadores identificarTrabajador(String dni, String contrasena) {
        return trabajadoresDAO.identificarTrabajador(dni, contrasena);
    }

    public boolean anyadirTrabajadores(Trabajadores trabajadores) {
        return trabajadoresDAO.agregarTrabajadores(trabajadores);
    }

    public boolean modificarTrabajadores(String dni, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevoTelefono, String nuevaContrasena, boolean nuevoActivo, String nuevoTipo, double nuevaComision) {
        return trabajadoresDAO.actualizarTrabajadores(dni, nuevoNombre, nuevoApellido, nuevoCorreo, nuevoTelefono, nuevaContrasena, nuevoActivo, nuevoTipo, nuevaComision);
    }
}