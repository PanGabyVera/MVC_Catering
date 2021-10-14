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
public class cargo {
    
    private String cod_car;
    private String nom;
    private int sueldo;

    public cargo() {
    }

    public cargo(String cod_car, String nom, int sueldo) {
        this.cod_car = cod_car;
        this.nom = nom;
        this.sueldo = sueldo;
    }

    public String getCod_car() {
        return cod_car;
    }

    public void setCod_car(String cod_car) {
        this.cod_car = cod_car;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
    
    
}
