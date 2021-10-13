
package Controlador;

import javax.swing.JDesktopPane;
import Modelo.Modelo_cliente;
import Modelo.Ingredientes;
import Modelo.Modelo_Ingrediente;
import Modelo.Modelo_Ingrediente_Menu;
import Modelo.Modelo_Menu;
import Modelo.Modelo_Menu_Paquete;
import Modelo.Modelo_Paquete;
import Vista.Vista_Cliente;
import Vista.Vista_Ingrediente;
import Vista.Vista_Menu;
import Vista.Vista_MenuPrincipal;
import Vista.Vista_Paquete;



public class Control_principal {
    
    private Vista_MenuPrincipal vst;

    public Control_principal(Vista_MenuPrincipal vst) {
        this.vst = vst;
        vst.setVisible(true);
    }
    
    public void iniciarControl(){
        vst.getJmicli().addActionListener(l->crudClienete());
        vst.getJmiing().addActionListener(l->crudIngrediente());
        vst.getJmimen().addActionListener(l->crudMenu());
        vst.getJmipaq().addActionListener(l->crudPaquete());
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
    private void crudMenu(){
        Modelo_Menu mome=new Modelo_Menu();
        Modelo_Ingrediente moin=new Modelo_Ingrediente();
        Modelo_Ingrediente_Menu moinme=new Modelo_Ingrediente_Menu();
        Vista_Menu vime=new Vista_Menu();
        vst.getJdpmen().add(vime);
        Controlador_Menu come=new Controlador_Menu(mome, vime,moin,moinme);
        come.iniciaControl();
    }
    private void crudPaquete(){
        Modelo_Menu mome=new Modelo_Menu();
        Modelo_Paquete mopa=new Modelo_Paquete();
        Modelo_Menu_Paquete momepa=new Modelo_Menu_Paquete();
        Vista_Paquete vipa=new Vista_Paquete();
        vst.getJdpmen().add(vipa);
        Controlador_Paquete come=new Controlador_Paquete(mopa,vipa,mome,momepa);
        come.iniciaControl();
    }
}
