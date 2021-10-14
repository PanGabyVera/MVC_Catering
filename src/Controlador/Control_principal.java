

package Controlador;

import javax.swing.JDesktopPane;
import Modelo.Modelo_cliente;
import Modelo.Ingredientes;
import Modelo.Modelo_Ingrediente;
import Modelo.Modelo_Ingrediente_Menu;
import Modelo.Modelo_Menu;
import Modelo.Modelo_Menu_Paquete;
import Modelo.Modelo_Paquete;
import Modelo.Modelo_cargo;
import Modelo.Modelo_empleado;
import Modelo.Modelo_inventario;
import Modelo.Modelo_pedido;
import Vista.Vista_Cargo;
import Vista.Vista_Cliente;
import Vista.Vista_Empleado;
import Vista.Vista_Ingrediente;
import Vista.Vista_Inventario;
import Vista.Vista_Menu;
import Vista.Vista_MenuPrincipal;
import Vista.Vista_Paquete;
import Vista.Vista_Pedido;

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
        vst.getJmiing().addActionListener(l->crudIngrediente());
        vst.getJmimen().addActionListener(l->crudMenu());
        vst.getJmiinv().addActionListener(l->crudInventario());
        vst.getJmiped().addActionListener(l->crudPedido());
        vst.getJmicar().addActionListener(l->crudCargo());
        vst.getJmiemp().addActionListener(l->crudEmpleado());
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
    
    private void crudInventario(){
        Modelo_inventario inv=new Modelo_inventario();
        Vista_Inventario vp=new Vista_Inventario();
        vst.getJdpmen().add(vp);
        Controlador_inventario cp=new Controlador_inventario(inv, vp);
        cp.iniciaControl();
    }
    
    private void crudPedido(){
        Modelo_pedido ped=new Modelo_pedido();
        Vista_Pedido vp=new Vista_Pedido();
        vst.getJdpmen().add(vp);
        Controlador_pedido cp=new Controlador_pedido(ped, vp);
        cp.iniciaControl();
    }
    
    private void crudCargo(){
        Modelo_cargo car=new Modelo_cargo();
        Vista_Cargo vc=new Vista_Cargo();
        vst.getJdpmen().add(vc);
        Controlador_cargo cc=new Controlador_cargo(car, vc);
        cc.iniciaControl();
    }
    
    private void crudEmpleado(){
        Modelo_empleado em=new Modelo_empleado();
        Vista_Empleado ve=new Vista_Empleado();
        vst.getJdpmen().add(ve);
        Controlador_empleado ce=new Controlador_empleado(em, ve);
        ce.iniciaControl();
    }
     
    
    
    
}