package org.example.perruqueria.perruqueriadreams.Models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ServiciosRealizados {
    // Entidades
    private Integer idServicioRealizados;
    private Integer idServicio;
    private Integer idCliente;
    private Integer idTrabajador;
    private String diaServicioRealizados;
    private BigDecimal precioFinal;
    private String comentario;

    // Constructores
    public ServiciosRealizados(Integer idServicioRealizados, Integer idServicio, Integer idCliente, Integer idTrabajador, String diaServicioRealizados, BigDecimal precioFinal, String comentario) {
        this.idServicioRealizados = idServicioRealizados;
        this.idServicio = idServicio;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.diaServicioRealizados = diaServicioRealizados;
        this.precioFinal = precioFinal;
        this.comentario = comentario;
    }

    public ServiciosRealizados(Integer idServicio, Integer idCliente, Integer idTrabajador, String diaServicioRealizados, BigDecimal precioFinal, String comentario) {
        this.idServicio = idServicio;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.diaServicioRealizados = diaServicioRealizados;
        this.precioFinal = precioFinal;
        this.comentario = comentario;
    }

    public ServiciosRealizados() {}

    // Setters
    public void setIdServicioRealizados(Integer idServicioRealizados) {
        this.idServicioRealizados = idServicioRealizados;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public void setDiaServicioRealizados(String diaServicioRealizados) {
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public String getDiaServicioRealizados() {
        return diaServicioRealizados;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public String getComentario() {
        return comentario;
    }
}
