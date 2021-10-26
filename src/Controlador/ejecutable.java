
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
