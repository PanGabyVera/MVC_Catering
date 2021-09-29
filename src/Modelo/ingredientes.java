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
public class ingredientes {
     private int cod_ingrediente;
      private String nombre;

    public ingredientes(int cod_ingrediente, String nombre) {
        this.cod_ingrediente = cod_ingrediente;
        this.nombre = nombre;
    }

    public int getCod_ingrediente() {
        return cod_ingrediente;
    }

    public void setCod_ingrediente(int cod_ingrediente) {
        this.cod_ingrediente = cod_ingrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
