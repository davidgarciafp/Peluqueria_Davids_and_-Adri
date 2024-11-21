package org.example.perruqueria.perruqueriadreams.Models;

public class Clientes {
    //Entidades
    private Integer idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String correoCliente;
    private String telefonoCliente;
    private Boolean proteccionDatos;
    private String descripcionCliente;

    //Constructores
    public Clientes(Integer idCliente, String nombreCliente, String apellidoCliente,
                    String correoCliente, String telefonoCliente, Boolean proteccionDatos,
                    String descripcionCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.proteccionDatos = proteccionDatos;
        this.descripcionCliente = descripcionCliente;
    }

    public Clientes(String nombreCliente, String apellidoCliente,
                    String correoCliente, String telefonoCliente, Boolean proteccionDatos,
                    String descripcionCliente) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.proteccionDatos = proteccionDatos;
        this.descripcionCliente = descripcionCliente;
    }

    public Clientes() {}


    //Setters
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public void setProteccionDatos(Boolean proteccionDatos) {
        this.proteccionDatos = proteccionDatos;
    }

    public void setDescripcionCliente(String descripcionCliente) {
        this.descripcionCliente = descripcionCliente;
    }
    

    //Getters
    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public String getApellidoCliente(){
        return apellidoCliente;
    }

    public String getCorreoCliente(){
        return correoCliente;
    }

    public String getTelefonoCliente(){
        return telefonoCliente;
    }

    public Boolean isProteccionDatos(){
        return proteccionDatos;
    }

    public String getDescripcionCliente(){
        return descripcionCliente;
    }
}