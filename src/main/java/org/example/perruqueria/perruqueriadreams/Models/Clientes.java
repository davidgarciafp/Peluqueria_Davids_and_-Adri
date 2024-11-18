package org.example.perruqueria.perruqueriadreams.Models;

public class Clientes {
    //Entidades
    private Integer id_cliente; 
    private String nombre_cliente;
    private String apellido_cliente;
    private String correo_cliente;
    private String telefono_cliente;
    private Boolean proteccion_datos;
    private String descripcion_cliente;

    //Constructores
    public Clientes(Integer id_cliente, String nombre_cliente, String apellido_cliente,
            String correo_cliente, String telefono_cliente, Boolean proteccion_datos,
            String descripcion_cliente) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.correo_cliente = correo_cliente;
        this.telefono_cliente = telefono_cliente;
        this.proteccion_datos = proteccion_datos;
        this.descripcion_cliente = descripcion_cliente;
    }

    public Clientes() {} 


    //Setters
    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public void setCorreo_cliente(String  correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public void setTelefono_cliente(String  telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public void setProteccion_datos (Boolean proteccion_datos) {
        this.proteccion_datos = proteccion_datos;
    }

    public void setDescripcion_cliente (String  descripcion_cliente) {
        this.descripcion_cliente = descripcion_cliente;
    }
    

    //Getters
    public Integer getId_cliente() {
        return id_cliente;
    }

    public String getNombre_cliente(){
        return nombre_cliente;
    }

    public String getApellido_cliente(){
        return apellido_cliente;
    }

    public String getCorreo_cliente(){
        return correo_cliente;
    }

    public String getTelefono_cliente(){
        return telefono_cliente;
    }

    public Boolean isProteccion_datos(){
        return  proteccion_datos;
    }

    public String  getDescripcion_cliente(){
        return descripcion_cliente;
    }

    

}