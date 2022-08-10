/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Lucio
 */
public class Reservas {
    
    private int id_reserva, dni_cliente;
    private String patente;
    private Double precio_total;
    private String fecha_inicio, fecha_fin;

    public Reservas(int id_reserva, int dni_cliente, String patente, String fecha_inicio, String fecha_fin, Double precio_total) {
        this.id_reserva = id_reserva;
        this.dni_cliente = dni_cliente;
        this.patente = patente;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.precio_total = precio_total;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(int dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public Double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(Double precio_total) {
        this.precio_total = precio_total;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
    
}
