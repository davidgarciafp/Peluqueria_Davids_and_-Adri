package modelo;

import java.math.BigDecimal;

public class Trabajadores {
    // Entidades
    private String dni;
    private String nombreTrabajador;
    private String apellidoTrabajador;
    private String correoTrabajador;
    private String telefonoTrabajador;
    private String contrasena;
    private boolean trabajadorActivo;
    private boolean tipoTrabajador;
    private BigDecimal comision;


    // Constructores
    public Trabajadores(String dni, String nombreTrabajador, String apellidoTrabajador, String correoTrabajador,
    String telefonoTrabajador, String contrasena, boolean trabajadorActivo, boolean tipoTrabajador,
    BigDecimal comision) {
        this.dni = dni;
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoTrabajador = apellidoTrabajador;
        this.correoTrabajador = correoTrabajador;
        this.telefonoTrabajador = telefonoTrabajador;
        this.contrasena = contrasena;
        this.trabajadorActivo = trabajadorActivo;
        this.tipoTrabajador = tipoTrabajador;
        this.comision = comision;
    }

    // Setters
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public void setApellidoTrabajador(String apellidoTrabajador) {
        this.apellidoTrabajador = apellidoTrabajador;
    }

    public void setCorreoTrabajador(String correoTrabajador) {
        this.correoTrabajador = correoTrabajador;
    }

    public void setTelefonoTrabajador(String telefonoTrabajador) {
        this.telefonoTrabajador = telefonoTrabajador;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTrabajadorActivo(boolean trabajadorActivo) {
        this.trabajadorActivo = trabajadorActivo;
    }

    public void setTipoTrabajador(boolean tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }



    // Getters
    public String getDni() {
        return dni;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public String getApellidoTrabajador() {
        return apellidoTrabajador;
    }

    public String getCorreoTrabajador() {
        return correoTrabajador;
    }

    public String getTelefonoTrabajador() {
        return telefonoTrabajador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean isTrabajadorActivo() {
        return trabajadorActivo;
    }

    public boolean isTipoTrabajador() {
        return tipoTrabajador;
    }

    public BigDecimal getComision() {
        return comision;
    }
}
