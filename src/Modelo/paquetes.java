/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author USER
 */
public class paquetes {
     private int cod_paquete;
      private String nombre;
       private String tipo;
        private int precio;

    public paquetes(int cod_paquete, String nombre, String tipo, int precio) {
        this.cod_paquete = cod_paquete;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getCod_paquete() {
        return cod_paquete;
    }

    public void setCod_paquete(int cod_paquete) {
        this.cod_paquete = cod_paquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}
