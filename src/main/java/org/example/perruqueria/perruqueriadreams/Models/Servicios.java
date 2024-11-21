package org.example.perruqueria.perruqueriadreams.Models;

import java.math.BigDecimal;

public class Servicios {
    //Entidades
    private Integer idServicio;
    private BigDecimal precioBase;
    private String descripcionServicio;
    private Boolean servicioActivo;


    //Constructores
    public Servicios(Integer idServicio, BigDecimal precioBase, String descripcionServicio, Boolean servicioActivo) {
        this.idServicio = idServicio;
        this.precioBase = precioBase;
        this.descripcionServicio = descripcionServicio;
        this.servicioActivo = servicioActivo;
    }
    public Servicios(BigDecimal precioBase, String descripcionServicio, Boolean servicioActivo) {
        this.precioBase = precioBase;
        this.descripcionServicio = descripcionServicio;
        this.servicioActivo = servicioActivo;
    }

    public Servicios() {} 


    //Setters
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public void setPrecioBase(BigDecimal precio_servicio) {
        this.precioBase = precio_servicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public void setServicioActivo(Boolean servicioActivo) {
        this.servicioActivo = servicioActivo;
    }

    

    //Getters
    public Integer getIdServicio() {
        return idServicio;
    }

    public BigDecimal getPrecioBase(){
        return precioBase;
    }

    public String getDescripcionServicio(){
        return descripcionServicio;
    }

    public Boolean isServicio_activo(){
        return servicioActivo;
    }

    

}