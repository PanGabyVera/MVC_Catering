
package Controlador;

import javax.swing.JDesktopPane;
import Modelo.Modelo_cliente;
import Modelo.Ingredientes;
import Modelo.Modelo_Ingrediente;
import Vista.Vista_Cliente;
import Vista.Vista_Ingrediente;
import Vista.Vista_MenuPrincipal;



public class Control_principal {
    
    private Vista_MenuPrincipal vst;

    public Control_principal(Vista_MenuPrincipal vst) {
        this.vst = vst;
        vst.setVisible(true);
    }
    
    public void iniciarControl(){
        vst.getJmicli().addActionListener(l->crudClienete());
        vst.getJmiing().addActionListener(l->crudIngrediente());
    }
    
    private void crudClienete(){
        Modelo_cliente mp=new Modelo_cliente();
        Vista_Cliente vp=new Vista_Cliente();
        vst.getJdpmen().add(vp);
        Controlador_cliente cp=new Controlador_cliente(mp, vp);
        cp.iniciaControl();
    }
    
    private void crudIngrediente(){
        Modelo_Ingrediente moin=new Modelo_Ingrediente();
        Vista_Ingrediente viin=new Vista_Ingrediente();
        vst.getJdpmen().add(viin);
        Controlador_Ingrediente coin=new Controlador_Ingrediente(moin, viin);
        coin.iniciaControl();
    }
}
