

package Controlador;

import javax.swing.JDesktopPane;
import Modelo.Modelo_cliente;
import Modelo.Ingredientes;
import Modelo.Modelo_ConexionBD;
import Modelo.Modelo_Empleado_Paquete;
import Modelo.Modelo_Ingrediente;
import Modelo.Modelo_Ingrediente_Menu;
import Modelo.Modelo_Inventario_Paquete;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
        vst.getJmipaq().addActionListener(l->crudPaquete());
        vst.getJmiimpmen().addActionListener(l->ImprimirMeú());
        vst.getJmiimping().addActionListener(l->ImprimirIngrediente());
        vst.getJmiimppaq().addActionListener(l->ImprimirPaquete());
  
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
        Modelo_inventario moinv=new Modelo_inventario();
        Modelo_Inventario_Paquete moinpa=new Modelo_Inventario_Paquete();
        Modelo_Empleado_Paquete moempa=new Modelo_Empleado_Paquete();
        Modelo_empleado moem=new Modelo_empleado();
        Vista_Paquete vipa=new Vista_Paquete();
        vst.getJdpmen().add(vipa);
        Controlador_Paquete come=new Controlador_Paquete(mopa,vipa,mome,momepa,moinv,moinpa,moempa,moem);
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
        Modelo_cargo ca=new Modelo_cargo(); 
        Vista_Empleado ve=new Vista_Empleado();
        vst.getJdpmen().add(ve);
        Controlador_empleado ce=new Controlador_empleado(em, ve,ca);
        ce.iniciaControl();
    }
     
    private void  ImprimirMeú(){
        Modelo_ConexionBD con =new Modelo_ConexionBD();
        try {
        JasperReport jr= (JasperReport)JRLoader.loadObject(getClass().getResource("/Vista/reportes/ListaMenú.jasper"));
//        Map<String,Object> parametros= new HashMap<String, Object>();
//        String aguja = "lux";
//        parametros.put("paguja" , "%"+aguja+"%");
        JasperPrint jp= JasperFillManager.fillReport(jr, null,con.getCon());
        JasperViewer jv= new JasperViewer(jp);
        jv.setVisible(true);    
        } catch (JRException ex) {
            Logger.getLogger(Controlador_Menu.class.getName()).log(Level.SEVERE,null,ex);
        }      
    } 
    private void  ImprimirIngrediente(){
        Modelo_ConexionBD con =new Modelo_ConexionBD();
        try {
        JasperReport jr= (JasperReport)JRLoader.loadObject(getClass().getResource("/Vista/reportes/ListaIngrediente.jasper"));
//        Map<String,Object> parametros= new HashMap<String, Object>();
//        String aguja = vista.getTxtbu().getText();
//        parametros.put("paguja" , "%"+aguja+"%");
        JasperPrint jp= JasperFillManager.fillReport(jr, null,con.getCon());
        JasperViewer jv= new JasperViewer(jp);
        jv.setVisible(true);    
        } catch (JRException ex) {
            Logger.getLogger(Controlador_Ingrediente.class.getName()).log(Level.SEVERE,null,ex);
        }      
    } 
    private void  ImprimirPaquete(){
        Modelo_ConexionBD con =new Modelo_ConexionBD();
        try {
        JasperReport jr= (JasperReport)JRLoader.loadObject(getClass().getResource("/vista/reportes/ListaPaquete1.jasper"));
//        Map<String,Object> parametros= new HashMap<String, Object>();
//        String aguja = vista.getTxtbu().getText();
//        parametros.put("paguja" , "%"+aguja+"%");
        JasperPrint jp= JasperFillManager.fillReport(jr, null,con.getCon());
        JasperViewer jv= new JasperViewer(jp);
        jv.setVisible(true);    
        } catch (JRException ex) {
            Logger.getLogger(Controlador_Paquete.class.getName()).log(Level.SEVERE,null,ex);
        }      
    } 
    
}