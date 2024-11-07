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

    public Trabajadores identificarTrabajador(Integer idTrabajador, String contrasena) {
        return trabajadoresDAO.identificarTrabajador(idTrabajador, contrasena);
    }

    public boolean anyadirTrabajadores(Trabajadores trabajadores) {
        return trabajadoresDAO.agregarTrabajadores(trabajadores);
    }

    public boolean modificarTrabajadores(Integer idTrabajador, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevoTelefono, String nuevaContrasena, boolean nuevoActivo, Boolean trabajadorTipo, java.math.BigDecimal comision_producto,  java.math.BigDecimal comision_servicio) {

        return trabajadoresDAO.actualizarTrabajadores(idTrabajador, nuevoNombre, nuevoApellido, nuevoCorreo, nuevoTelefono, nuevaContrasena, nuevoActivo, trabajadorTipo, comision_producto, comision_servicio);
    }

    public Trabajadores buscarTrabajador(Integer idTrabajador) {
        return trabajadoresDAO.encontrarTrabajador(idTrabajador);
    }

    public List<Trabajadores> mostrarTrabajadoresEliminados() {
        return trabajadoresDAO.mostrarTrabajadoresEliminados();
    }

    public Trabajadores identificarInicio(String contrasena) {
        return trabajadoresDAO.identificarDreams(contrasena);
    }
}