package org.example.perruqueria.perruqueriadreams.Models;

import java.math.BigDecimal;
import java.util.Date;

public class Cobros {
    // Atributos
    private Integer idCobros; // Identificador único por cada cobro.
    private Integer idTrabajador;
    private Integer idCliente; // Referencia a la tabla clientes.
    private String diaCobro; // Fecha del cobro.
    private BigDecimal importe; // Importe total del cobro.
    private BigDecimal efectivo; // Importe pagado en efectivo.
    private BigDecimal tarjeta; // Importe pagado con tarjeta.
    private BigDecimal bizum; // Importe pagado por Bizum.
    private BigDecimal deudas; // Importe de deudas pendientes.
    private boolean pagado; // Estado del pago (true si se ha pagado, false si está pendiente).

    // Constructor
    public Cobros(Integer idCobros, Integer idTrabajador, Integer idCliente, String diaCobro, BigDecimal importe, BigDecimal efectivo, BigDecimal tarjeta, BigDecimal bizum, BigDecimal deudas, boolean pagado) {
        this.idCobros = idCobros;
        this.idTrabajador = idTrabajador;
        this.idCliente = idCliente;
        this.diaCobro = diaCobro;
        this.importe = importe;
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
        this.bizum = bizum;
        this.deudas = deudas;
        this.pagado = pagado;
    }

    public Cobros(Integer idTrabajador, Integer idCliente, String diaCobro, BigDecimal importe, BigDecimal efectivo, BigDecimal tarjeta, BigDecimal bizum, BigDecimal deudas, boolean pagado) {
        this.idTrabajador = idTrabajador;
        this.idCliente = idCliente;
        this.diaCobro = diaCobro;
        this.importe = importe;
        this.efectivo = efectivo;
        this.tarjeta = tarjeta;
        this.bizum = bizum;
        this.deudas = deudas;
        this.pagado = pagado;
    }

    public Cobros() {}

    private String nombreClienteTemporal;
    public String getNombreClienteTemporal() { return nombreClienteTemporal; }
    public void setNombreClienteTemporal(String nombreClienteTemporal) {
        this.nombreClienteTemporal = nombreClienteTemporal;
    }
    // Getters y Setters
    public Integer getIdCobros() {
        return idCobros;
    }

    public void setIdCobros(Integer idCobros) {
        this.idCobros = idCobros;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDiaCobro() {
        return diaCobro;
    }

    public void setDiaCobro(String diaCobro) {
        this.diaCobro = diaCobro;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(BigDecimal tarjeta) {
        this.tarjeta = tarjeta;
    }

    public BigDecimal getBizum() {
        return bizum;
    }

    public void setBizum(BigDecimal bizum) {
        this.bizum = bizum;
    }

    public BigDecimal getDeudas() {
        return deudas;
    }

    public void setDeudas(BigDecimal deudas) {
        this.deudas = deudas;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}