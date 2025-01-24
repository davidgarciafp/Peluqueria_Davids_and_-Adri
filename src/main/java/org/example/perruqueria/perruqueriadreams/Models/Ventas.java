package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Date;
import java.time.LocalDate;

public class Ventas {
    private Integer idVenta;
    private Integer idTrabajador;
    private Integer idProducto;
    private Integer idCliente;
    private String diaVenta;
    private Integer cantidadVendida;
    private String descripcionVenta;

    public Ventas(Integer idTrabajador, Integer idProducto, Integer idCliente, String diaVenta, Integer cantidadVendida, String descripcionVenta) {
        this.idTrabajador = idTrabajador;
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.diaVenta = diaVenta;
        this.cantidadVendida = cantidadVendida;
        this.descripcionVenta = descripcionVenta;
    }
    public Ventas(Integer idTrabajador, Integer idProducto, Integer idCliente, String diaVenta) {
        this.idTrabajador = idTrabajador;
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.diaVenta = diaVenta;
    }

    public Ventas() {

    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDiaVenta() {
        return diaVenta;
    }

    public void setDiaVenta(String diaVenta) {
        this.diaVenta = diaVenta;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getDescripcionVenta() {
        return descripcionVenta;
    }

    public void setDescripcionVenta(String descripcionVenta) {
        this.descripcionVenta = descripcionVenta;
    }
}
