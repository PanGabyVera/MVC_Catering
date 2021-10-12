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
public class Control_principal {
    
    private Vista_MenuPrincipal vst;

    public Control_principal(Vista_MenuPrincipal vst) {
        this.vst = vst;
        vst.setVisible(true);
    }
    
    public void iniciarControl(){
        vst.getJmicli().addActionListener(l->crudClienete());
    }
    
    private void crudClienete(){
        Modelo_cliente mp=new Modelo_cliente();
        Vista_Cliente vp=new Vista_Cliente();
        vst.getJdpmen().add(vp);
        Controlador_cliente cp=new Controlador_cliente(mp, vp);
        cp.iniciaControl();
    }
    
}
