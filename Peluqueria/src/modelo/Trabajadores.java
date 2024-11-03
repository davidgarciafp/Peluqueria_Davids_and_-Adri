package modelo;

import java.math.BigDecimal;

public class Trabajadores {
    // Entidades
    private Integer id_trabajador;
    private String nombreTrabajador;
    private String apellidoTrabajador;
    private String correoTrabajador;
    private String telefonoTrabajador;
    private String contrasena;
    private boolean trabajadorActivo;
    private boolean tipoTrabajador;
    private BigDecimal comision_producto;
    private BigDecimal comision_servicio;


    // Constructores
    public Trabajadores(Integer id_trabajador, String nombreTrabajador, String apellidoTrabajador, String correoTrabajador,
    String telefonoTrabajador, String contrasena, boolean trabajadorActivo, boolean tipoTrabajador,
    BigDecimal comision_producto,  BigDecimal comision_servicio) {

        this.id_trabajador = id_trabajador;
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoTrabajador = apellidoTrabajador;
        this.correoTrabajador = correoTrabajador;
        this.telefonoTrabajador = telefonoTrabajador;
        this.contrasena = contrasena;
        this.trabajadorActivo = trabajadorActivo;
        this.tipoTrabajador = tipoTrabajador;
        this.comision_producto = comision_producto;
        this.comision_servicio = comision_servicio;

    }

    public Trabajadores() {} 

    // Setters
    public void setIdTrabajador(Integer id_trabajador) {
        this.id_trabajador = id_trabajador;
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

    public void setComisionProducto(BigDecimal comision_producto) {
        this.comision_producto = comision_producto;
    }
    public void setComisionServicio(BigDecimal comision_servicio) {
        this.comision_servicio = comision_servicio;
    }

    public void setComision_servicios(BigDecimal comision_servicios) {
        this.comision_servicios = comision_servicios;
    }


    // Getters
    public Integer getIdTrabajador() {
        return id_trabajador;
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

    public BigDecimal getComisionProducto() {
        return comision_producto;
    }

    public BigDecimal getComisionServicio() {
        return comision_servicio;
    }
}
