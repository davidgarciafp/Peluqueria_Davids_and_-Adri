package org.example.perruqueria.perruqueriadreams.Models;

import java.math.BigDecimal;

public class Productos {
    //Entidades
    private Integer idProducto;
    private String codigoBarras;
    private String nombreProducto;
    private String marca;
    private BigDecimal precioProducto;
    private String descripcionProducto;
    private Integer cantidadDisponible;
    private Integer productoGastado;
    private Boolean productoActivo;

    //Constructores
    public Productos(Integer idProducto, String codigoBarras, String nombreProducto, String marca, BigDecimal precioProducto,
                     String descripcionProducto, Integer cantidadDisponible, Integer productoGastado,
                     Boolean productoActivo) {
        this.idProducto = idProducto;
        this.codigoBarras = codigoBarras;
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.precioProducto = precioProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadDisponible = cantidadDisponible;
        this.productoGastado = productoGastado;
        this.productoActivo = productoActivo;
    }

    public Productos() {} 


    //Setters
    public void setId_producto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setProductoGastado(Integer productoGastado){
        this.productoGastado = productoGastado;
    }

    public void setProductoActivo(Boolean productoActivo) {
        this.productoActivo = productoActivo;
    }
    

    //Getters
    public Integer getIdProducto() {
        return idProducto;
    }
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getNombreProducto(){
        return nombreProducto;
    }

    public String getMarca(){
        return marca;
    }

    public BigDecimal getPrecioProducto(){
        return precioProducto;
    }

    public String getDescripcionProducto(){
        return descripcionProducto;
    }

    public Integer getCantidadDisponible(){
        return cantidadDisponible;
    }

    public Integer getProductoGastado(){
        return productoGastado;
    }

    public Boolean  isProductoActivo(){
        return productoActivo;
    }
}
