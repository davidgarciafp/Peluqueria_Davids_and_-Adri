package modelo;

import java.math.BigDecimal;

public class Productos {
    //Entidades
    private Integer id_producto; 
    private String nombre_producto;
    private String marca;
    private BigDecimal precio_producto;
    private String descripcion_producto;
    private Integer cantidad_disponible;
    private Integer producto_gastado;
    private Boolean producto_activo;

    //Constructores
    public Productos(Integer id_producto, String nombre_producto, String marca, BigDecimal precio_producto,
            String descripcion_producto, Integer cantidad_disponible, Integer producto_gastado,
            Boolean producto_activo) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.marca = marca;
        this.precio_producto = precio_producto;
        this.descripcion_producto = descripcion_producto;
        this.cantidad_disponible = cantidad_disponible;
        this.producto_gastado = producto_gastado;
        this.producto_activo = producto_activo;
    }

    public Productos() {} 


    //Setters
    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecio_producto(BigDecimal  precio_producto) {
        this.precio_producto = precio_producto;
    }

    public void setDescripcion_producto(String  descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public void setCantidad_disponible (Integer cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public void setProducto_gastado (Integer producto_gastado){
        this.producto_gastado = producto_gastado;
    }

    public void setProducto_activo (Boolean  producto_activo) {
        this.producto_activo = producto_activo;
    }
    

    //Getters
    public Integer getId_producto() {
        return id_producto;
    }

    public String getNombre_producto(){
        return nombre_producto;
    }

    public String getMarca(){
        return marca;
    }

    public BigDecimal getPrecio_producto(){
        return precio_producto;
    }

    public String getDescripcion_producto(){
        return descripcion_producto;
    }

    public Integer getCantidad_disponible(){
        return cantidad_disponible;
    }

    public Integer getProducto_gastado(){
        return  producto_gastado;
    }

    public Boolean  isProducto_activo(){
        return producto_activo;
    }
}
