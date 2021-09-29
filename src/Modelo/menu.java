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
public class menu {
     private String cod_menu;
      private String nombre;
       private int precio;
        private String tipo;

    public menu(String cod_menu, String nombre, int precio, String tipo) {
        this.cod_menu = cod_menu;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getCod_menu() {
        return cod_menu;
    }

    public void setCod_menu(String cod_menu) {
        this.cod_menu = cod_menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
