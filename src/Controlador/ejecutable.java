/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo_cliente;
import Vista.Vista_Cliente;
import Vista.Vista_MenuPrincipal;

/**
 *
 * @author Pandora
 */
public class ejecutable {
    public static void main(String [] args){
     Vista_MenuPrincipal vis=new Vista_MenuPrincipal();
        Control_principal cp=new Control_principal(vis);
        cp.iniciarControl();
    }
     
    
}
