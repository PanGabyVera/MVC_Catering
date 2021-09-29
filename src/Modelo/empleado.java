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
public class empleado {
     private int cod_empeado;
      private String cargo;
       private int salario;

    public empleado(int cod_empeado, String cargo, int salario) {
        this.cod_empeado = cod_empeado;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getCod_empeado() {
        return cod_empeado;
    }

    public void setCod_empeado(int cod_empeado) {
        this.cod_empeado = cod_empeado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}
