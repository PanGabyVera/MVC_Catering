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
public class Inventario_paquete {
    private String cod_paq;
    private String cod_inv;
    private int cantidad;
    private String nombre;

    public Inventario_paquete() {
    }

    public Inventario_paquete(String cod_paq, String cod_inv, int cantidad, String nombre) {
        this.cod_paq = cod_paq;
        this.cod_inv = cod_inv;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
