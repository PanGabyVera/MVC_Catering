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
public class inventario_paquete {
    private String cod_inv;
    private String cod_paq;
    private int cantidad;

    public inventario_paquete() {
    }

    public inventario_paquete(String cod_inv, String cod_paq, int cantidad) {
        this.cod_inv = cod_inv;
        this.cod_paq = cod_paq;
        this.cantidad = cantidad;
    }

    public String getCod_inv() {
        return cod_inv;
    }

    public void setCod_inv(String cod_inv) {
        this.cod_inv = cod_inv;
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
