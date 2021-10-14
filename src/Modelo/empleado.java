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
public class empleado extends persona{
      private String cargo;
       private String cod;
       private String nom_car;
       private int suel;

    public empleado() {
    }

    public empleado(String cargo, String cod, String nom_car, int suel, String ci, String nombres, String apellidos, String telefono, String correo, String direccion) {
        super(ci, nombres, apellidos, telefono, correo, direccion);
        this.cargo = cargo;
        this.cod = cod;
        this.nom_car = nom_car;
        this.suel = suel;
    }

    public String getNom_car() {
        return nom_car;
    }

    public void setNom_car(String nom_car) {
        this.nom_car = nom_car;
    }

    public int getSuel() {
        return suel;
    }

    public void setSuel(int suel) {
        this.suel = suel;
    }

    

    

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }


    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
}
