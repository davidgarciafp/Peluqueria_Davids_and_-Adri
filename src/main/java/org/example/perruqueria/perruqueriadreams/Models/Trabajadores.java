package org.example.perruqueria.perruqueriadreams.Models;

import java.math.BigDecimal;

public class Trabajadores {
    // Entidades
    private Integer idTrabajador;
    private String nombreTrabajador;
    private String apellidoTrabajador;
    private String correoTrabajador;
    private String telefonoTrabajador;
    private String contrasena;
    private boolean trabajadorActivo;
    private boolean tipoTrabajador;
    private BigDecimal comisionProducto;
    private BigDecimal comisionServicio;


    // Constructores
    public Trabajadores(Integer idTrabajador, String nombreTrabajador, String apellidoTrabajador, String correoTrabajador,
    String telefonoTrabajador, String contrasena, boolean trabajadorActivo, boolean tipoTrabajador,
    BigDecimal comisionProducto,  BigDecimal comisionServicio) {

        this.idTrabajador = idTrabajador;
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoTrabajador = apellidoTrabajador;
        this.correoTrabajador = correoTrabajador;
        this.telefonoTrabajador = telefonoTrabajador;
        this.contrasena = contrasena;
        this.trabajadorActivo = trabajadorActivo;
        this.tipoTrabajador = tipoTrabajador;
        this.comisionProducto = comisionProducto;
        this.comisionServicio = comisionServicio;

    }

    public Trabajadores(String nombreTrabajador, String apellidoTrabajador, String correoTrabajador,
                        String telefonoTrabajador, String contrasena, boolean trabajadorActivo, boolean tipoTrabajador) {

        this.nombreTrabajador = nombreTrabajador;
        this.apellidoTrabajador = apellidoTrabajador;
        this.correoTrabajador = correoTrabajador;
        this.telefonoTrabajador = telefonoTrabajador;
        this.contrasena = contrasena;
        this.trabajadorActivo = trabajadorActivo;
        this.tipoTrabajador = tipoTrabajador;
    }

    public Trabajadores() {} 

    // Setters
    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
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

    public void setComisionProducto(BigDecimal comisionProducto) {
        this.comisionProducto = comisionProducto;
    }
    public void setComisionServicio(BigDecimal comisionServicio) {
        this.comisionServicio = comisionServicio;
    }


    // Getters
    public Integer getIdTrabajador() {
        return idTrabajador;
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
        return comisionProducto;
    }

    public BigDecimal getComisionServicio() {
        return comisionServicio;
    }
}
