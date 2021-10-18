/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class Empleado_paquete {
    private String cod_emp;
    private String cod_paq;
    private String nombre;
    private int cantidad;

    public Empleado_paquete() {
    }

    public Empleado_paquete(String cod_emp, String cod_paq, String nombre,int cantidad) {
        this.cod_emp = cod_emp;
        this.cod_paq = cod_paq;
        this.nombre= nombre;
        this.cantidad=cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(String cod_emp) {
        this.cod_emp = cod_emp;
    }

    public String getCod_paq() {
        return cod_paq;
    }

    public void setCod_paq(String cod_paq) {
        this.cod_paq = cod_paq;
    }
    
}
