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
       private int salario;

    public empleado() {
    }

    public empleado(String cargo, int salario, String nombres, String apellidos, String telefono, String correo, String ci, String direccion) {
        super(nombres, apellidos, telefono, correo, ci, direccion);
        this.cargo = cargo;
        this.salario = salario;
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
