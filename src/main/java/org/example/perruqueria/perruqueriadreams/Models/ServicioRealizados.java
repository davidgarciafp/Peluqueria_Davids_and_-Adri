package org.example.perruqueria.perruqueriadreams.Models;

import java.math.BigDecimal;

public class ServicioRealizados {
    // Entidades
    private Integer idServicioRealizados;
    private Integer idTrabajador;
    private Integer idServicio;
    private Integer diaServicioRealizados;
    private BigDecimal precioFinal;
    private String comentario;

    // Constructores
    public ServicioRealizados(Integer idServicioRealizados, Integer idTrabajador, Integer idServicio, Integer diaServicioRealizados, BigDecimal precioFinal, String comentario) {
        this.idServicioRealizados = idServicioRealizados;
        this.idTrabajador = idTrabajador;
        this.idServicio = idServicio;
        this.diaServicioRealizados = diaServicioRealizados;
        this.precioFinal = precioFinal;
        this.comentario = comentario;
    }

    public ServicioRealizados() {}

    // Setters
    public void setIdServicioRealizados(Integer idServicioRealizados) {
        this.idServicioRealizados = idServicioRealizados;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public void setDiaServicioRealizados(Integer diaServicioRealizados) {
        this.diaServicioRealizados = diaServicioRealizados;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    // Getters
    public Integer getIdServicioRealizados() {
        return idServicioRealizados;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public Integer getDiaServicioRealizados() {
        return diaServicioRealizados;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public String getComentario() {
        return comentario;
    }
}
