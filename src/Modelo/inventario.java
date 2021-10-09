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
public class inventario {
     private String cod_inventario;
      private String nombre;
       private int precio;

    public String getCod_inventario() {
        return cod_inventario;
    }

    public void setCod_inventario(String cod_inventario) {
        this.cod_inventario = cod_inventario;
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

    public inventario(String cod_inventario, String nombre, int precio) {
        this.cod_inventario = cod_inventario;
        this.nombre = nombre;
        this.precio = precio;
    }
}
