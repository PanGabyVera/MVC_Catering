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
public class cliente extends persona{

    private String cod;
    public cliente() {
    }

    public cliente(String cod,String ci, String nombres, String apellidos) {
        super(ci, nombres, apellidos);
        this.cod = cod;
    }
    
    public cliente(String cod, String nombres, String apellidos, String telefono, String correo, String ci, String direccion) {
        super(nombres, apellidos, telefono, correo, ci, direccion);
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    
    
    
}
