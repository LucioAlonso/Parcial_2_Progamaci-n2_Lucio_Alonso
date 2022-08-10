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
public class Vehiculos {
    
    private String patente;
    private String marca;
    private int modelo;
    private String color;
    private int tanque;
    private Double precio;
    private boolean estado;
            

    
    public Vehiculos(String patente, String marca, int modelo, String color, int tanque, Double precio, boolean estado){
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tanque = tanque;
        this.precio = precio;
        this.estado = estado;


    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTanque() {
        return tanque;
    }

    public void setTanque(int tanque) {
        this.tanque = tanque;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}