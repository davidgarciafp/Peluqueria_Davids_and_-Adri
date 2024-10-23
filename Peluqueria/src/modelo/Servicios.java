package modelo;

import java.math.BigDecimal;

public class Servicios {
    //Entidades
    private Integer id_servicio; 
    private BigDecimal precio_base;
    private String descripcion_servicio;
    private Boolean servicio_activo;


    //Constructores
    public Servicios(Integer id_servicio, BigDecimal precio_base, String descripcion_servicio, Boolean servicio_activo) {
        this.id_servicio = id_servicio;
        this.precio_base = precio_base;
        this.descripcion_servicio = descripcion_servicio;
        this.servicio_activo = servicio_activo;
    }
    //Setters
    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setPrecio_base(BigDecimal precio_servicio) {
        this.precio_base = precio_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public void setServicio_activo (Boolean servicio_activo) {
        this.servicio_activo = servicio_activo;
    }

    

    //Getters
    public Integer getId_servicio() {
        return id_servicio;
    }

    public BigDecimal getPrecio_base(){
        return precio_base;
    }

    public String getDescripcion_servicio(){
        return descripcion_servicio;
    }

    public Boolean isServicio_activo(){
        return  servicio_activo;
    }

    

}