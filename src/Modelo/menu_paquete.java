/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Pandora
 */
public class menu_paquete {
    private String cod_menu;
    private String cod_paq;
    private int cantidad;

    public menu_paquete() {
    }

    public menu_paquete(String cod_menu, String cod_paq, int cantidad) {
        this.cod_menu = cod_menu;
        this.cod_paq = cod_paq;
        this.cantidad = cantidad;
    }

    public String getCod_menu() {
        return cod_menu;
    }

    public void setCod_menu(String cod_menu) {
        this.cod_menu = cod_menu;
    }

    public String getCod_paq() {
        return cod_paq;
    }

    public void setCod_paq(String cod_paq) {
        this.cod_paq = cod_paq;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    

}
