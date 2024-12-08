package org.example.perruqueria.perruqueriadreams.Models;

public class Agenda {
    //Entidades
    private Integer idAgenda; 
    private String dia;
    private String hora;
    private String texto;
    private Integer idTrabajador;

    // Constructor
    public Agenda(Integer idAgenda, String dia, String hora, String texto, Integer idTrabajador) {
        this.idAgenda = idAgenda;
        this.dia = dia;
        this.hora = hora;
        this.texto = texto;
        this.idTrabajador = idTrabajador;
    }

    public Agenda(String hora) {
        this.hora = hora;
    }

    public Agenda() {
    }

    // Setters
    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    
    // Getters
    public Integer getIdAgenda() {
        return idAgenda;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getTexto() {
        return texto;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }
}
