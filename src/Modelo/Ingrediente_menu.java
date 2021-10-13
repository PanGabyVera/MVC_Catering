/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Ingrediente_menu {
   private String cod_menu;
    private String cod_ing;
    private int cantidad;
    private String nombre;

    public Ingrediente_menu() {
    }

    public Ingrediente_menu(String cod_menu, String cod_ing, int cantidad, String nombre) {
        this.cod_menu = cod_menu;
        this.cod_ing = cod_ing;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public String getCod_menu() {
        return cod_menu;
    }

    public void setCod_menu(String cod_menu) {
        this.cod_menu = cod_menu;
    }

    public String getCod_ing() {
        return cod_ing;
    }

    public void setCod_ing(String cod_ing) {
        this.cod_ing = cod_ing;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
 
}
