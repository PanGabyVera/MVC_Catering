
package Controlador;

import Modelo.Empleado_paquete;
import Modelo.Ingrediente_menu;
import Modelo.Ingredientes;
import Modelo.Inventario_paquete;
import Modelo.Menu;
import Modelo.Menu_paquete;
import Modelo.Modelo_ConexionBD;
import Modelo.Modelo_Empleado_Paquete;
import Modelo.Modelo_Ingrediente;
import Modelo.Modelo_Ingrediente_Menu;
import Modelo.Modelo_Inventario_Paquete;
import Vista.Vista_Menu;
import Modelo.Modelo_Menu;
import Modelo.Modelo_Menu_Paquete;
import Modelo.Modelo_Paquete;
import Modelo.Modelo_empleado;
import Modelo.Modelo_inventario;
import Modelo.Paquete;
import Modelo.empleado;
import Modelo.inventario;
import Vista.Vista_MenuPrincipal;
import Vista.Vista_Paquete;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sun.swing.table.DefaultTableCellHeaderRenderer;


public class Controlador_Paquete extends Paquete{
    private Modelo_Paquete Modelo;
    private Modelo_Menu ModeloMenu;
    private Vista_Paquete Vista;
    private Modelo_Menu_Paquete Modelo_Menu_Paquete;
    private Modelo_inventario Modelo_inventario;
    private Modelo_Inventario_Paquete Modelo_Inventario_Paquete;
    private Modelo_empleado Modelo_empleado;
    private Modelo_Empleado_Paquete Modelo_Empleado_Paquete;
    public Controlador_Paquete(Modelo_Paquete Modelo, Vista_Paquete Vista, Modelo_Menu ModeloMenu,Modelo_Menu_Paquete Modelo_Menu_Paquete,
        Modelo_inventario Modelo_inventario, Modelo_Inventario_Paquete Modelo_Inventario_Paquete, 
        Modelo_Empleado_Paquete Modelo_Empleado_Paquete,Modelo_empleado Modelo_empleado) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ModeloMenu = ModeloMenu;
        this.Modelo_Menu_Paquete =Modelo_Menu_Paquete;
        this.Modelo_inventario=Modelo_inventario;
        this.Modelo_Inventario_Paquete=Modelo_Inventario_Paquete;
        this.Modelo_Empleado_Paquete=Modelo_Empleado_Paquete;
        this.Modelo_empleado=Modelo_empleado;
        Vista.setVisible(true);
    }
    public void iniciaControl(){
     KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                cargaListadosPa(Vista.getTxtbu().getText());
            }
        };

   //Controlar los eventos de la vista
    Vista.getBtnli().addActionListener(l->cargaListadosPa(""));
    Vista.getBtncr().addActionListener(l->cargarDialogo(11));
    Vista.getBtnsig().addActionListener(l->cargarDialogoEsmen(33));
    Vista.getBtnsig().addActionListener(l->cargaListadosMenPa(""));
    Vista.getBtnsig().addActionListener(l->GuardarMenuP());
    Vista.getBtnfinal().addActionListener(l->GuardarPaquete());
    Vista.getBtnacep().addActionListener(l->EditarPaquete());
    Vista.getBtnel().addActionListener(l-> EliminarPaquete());
    Vista.getBtncarmen().addActionListener(l->AgregramMen());
    Vista.getBtncanmen().addActionListener(l->EliminarMenuP());
    Vista.getBtnagrmen().addActionListener(l->GuardarEscmen());
    //EDITAR INGREDIENTE_MENU
    Vista.getBtned().addActionListener(l->cargarDialogoEsmenEd(44));
    Vista.getBtned().addActionListener(l->cargaListadosMen_Paq(""));
    Vista.getBtncared().addActionListener(l->EditarMenPaquCant());
    Vista.getBtnedime().addActionListener(l->EditarMenuPaqu());
    Vista.getBtnsiged().addActionListener(l->cargarDialogoEsmen(55));
    Vista.getBtnsiged().addActionListener(l->cargaListadosMenPa(""));
    Vista.getBtnelied().addActionListener(l->EliminarMenuPaqueEd());
    Vista.getBtnagre1().addActionListener(l->GuardarEdmen());
    Vista.getBtnca3().addActionListener(l->cargaListadosMen_Paq(""));
//    Vista.getBtnfi1().addActionListener(l->cargarDialogo(22));
    Vista.getBtnim().addActionListener(l-> ImprimirRegistro());
    
    //RELACIÓN INVENTARIO-PAQUETE
    Vista.getBtnsigme().addActionListener(l->cargarDialogoEsinv(66));
    Vista.getBtnsigme().addActionListener(l->cargaListadosInventar(""));
    Vista.getBtnagrin().addActionListener(l->GuardarEscinv());
    Vista.getBtncarinv().addActionListener(l->AgregrarInv());
    Vista.getBtncainv().addActionListener(l->EliminarInveCancelar());
    Vista.getBtncainv().addActionListener(l->Vista.getDlgesinv().setVisible(false));
    Vista.getBtncainv().addActionListener(l->Vista.getDlgesmen().setVisible(true));
    Vista.getBtnca6().addActionListener(l->Vista.getDlgesinv().setVisible(false));
    Vista.getBtnca6().addActionListener(l->Vista.getDlgedinv().setVisible(true));
    
    //EDICIÓN INVENTARIO_PAQUETE
    Vista.getBtnfi1().addActionListener(l->cargarDialogoEsinvEd(88));
    Vista.getBtnfi1().addActionListener(l->cargaListadosInven_Paq(""));
    Vista.getBtncared1().addActionListener(l->EditarInvPaquCant());
    Vista.getBtnedime1().addActionListener(l->EditarInvenPaqu());
    Vista.getBtnsiged1().addActionListener(l->cargarDialogoEsinv(77));
    Vista.getBtnsiged1().addActionListener(l->cargaListadosInventar(""));
    Vista.getBtnelied1().addActionListener(l->EliminarInventaPaqueEd());
    Vista.getBtnagre2().addActionListener(l->GuardarEdInv());
    Vista.getBtnca6().addActionListener(l->cargaListadosInven_Paq(""));
    Vista.getBtnca5().addActionListener(l->Vista.getDlgedinv().setVisible(false));
    Vista.getBtnca5().addActionListener(l->Vista.getDlgesmen().setVisible(true));
    
    //RELACIÓN EMPLEADO-PAQUETE
    Vista.getBtnsigin().addActionListener(l->cargarDialogoEsemp(5));
    Vista.getBtnsigin().addActionListener(l->cargaListadosEmpleado(""));
    Vista.getBtnagremp().addActionListener(l->GuardarEscemp());
//    Vista.getBtncarinv().addActionListener(l->AgregrarInv());
    Vista.getBtncaemp().addActionListener(l->EliminarEmpleCancelar());
    Vista.getBtncaemp().addActionListener(l->Vista.getDlgesemp().setVisible(false));
    Vista.getBtncaemp().addActionListener(l->Vista.getDlgesinv().setVisible(true));
    Vista.getBtnca7().addActionListener(l->Vista.getDlgesemp().setVisible(false));
    Vista.getBtnca7().addActionListener(l->Vista.getDlgedemp().setVisible(true));
    
    //EDICIÓN EMPLEADO_PAQUETE
    Vista.getBtnfi2().addActionListener(l->cargarDialogoEsempEd(7));
    Vista.getBtnfi2().addActionListener(l->cargaListadosEmple_Paq(""));
    Vista.getBtncared1().addActionListener(l->EditarInvPaquCant());
    Vista.getBtnedime1().addActionListener(l->EditarInvenPaqu());
    Vista.getBtnsiged2().addActionListener(l->cargarDialogoEsemp(100));
    Vista.getBtnsiged2().addActionListener(l->cargaListadosEmpleado(""));
    Vista.getBtnelied2().addActionListener(l->EliminarEmpleaPaqueEd());
    Vista.getBtnagre3().addActionListener(l->GuardarEdEmp());
    Vista.getBtnca6().addActionListener(l->cargaListadosEmple_Paq(""));
    Vista.getBtnca8().addActionListener(l->Vista.getDlgesinv().setVisible(true));
    Vista.getBtnca8().addActionListener(l->Vista.getDlgedemp().setVisible(false));
    
    //
    Vista.getBtnca4().addActionListener(l->Vista.getDlgpaq().setVisible(false));
    Vista.getBtnca2().addActionListener(l->Vista.getDlgedmen().setVisible(false));
    Vista.getBtncanmen().addActionListener(l->Vista.getDlgesmen().setVisible(false));
    Vista.getBtncanmen().addActionListener(l->Vista.getDlgpaq().setVisible(true));
    Vista.getBtnca3().addActionListener(l->Vista.getDlgesmen().setVisible(false));
    Vista.getBtnca3().addActionListener(l->Vista.getDlgedmen().setVisible(true));
    Vista.getBtncainv().addActionListener(l->Vista.getDlgesinv().setVisible(false));
    Vista.getBtnfinal().addActionListener(l->Vista.getDlgpaq().setVisible(false));
    Vista.getBtnfinal().addActionListener(l->Vista.getDlgesmen().setVisible(false));
    Vista.getBtnfinal().addActionListener(l->Vista.getDlgesinv().setVisible(false));
    Vista.getBtnfinal().addActionListener(l->Vista.getDlgesemp().setVisible(false));
    Vista.getBtnfi4().addActionListener(l->Vista.getDlgesemp().setVisible(false));
    Vista.getBtnfi4().addActionListener(l->Vista.getDlgesinv().setVisible(false));
    Vista.getBtnfi4().addActionListener(l->Vista.getDlgesmen().setVisible(false));
    Vista.getBtnatra().addActionListener(l->Vista.getDlgcos().setVisible(false));
    Vista.getBtnatra().addActionListener(l->Vista.getDlgesemp().setVisible(true));
    Vista.getBtnfi4().addActionListener(l->cargarDialogoCosto(123));
    Vista.getBtnfi4().addActionListener(l->cargaListadosCostoMen(""));
    Vista.getBtnfi4().addActionListener(l->cargaListadosCostoInv(""));
    Vista.getBtnfi4().addActionListener(l->cargaListadosCostoEmp(""));
    Vista.getBtnfi3().addActionListener(l->cargarDialogoCosto(234));
    Vista.getBtnfi3().addActionListener(l->cargaListadosCostoMenEd(""));
    Vista.getBtnfi3().addActionListener(l->cargaListadosCostoInvEd(""));
    Vista.getBtnfi3().addActionListener(l->cargaListadosCostoEmpEd(""));
//    Vista.getBtnfinal1().addActionListener(l->GuardarPaquete());
    Vista.getBtnfinal1().addActionListener(l->cargarDialogo(22));
    
    //Controlador Buscar
    Vista.getTxtbu().addKeyListener(kl);
    
    
    }
    private void  ImprimirRegistro(){
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
    private void cargarDialogo(int origen){
        Vista.getDlgpaq().setSize(520,280);
        Vista.getDlgpaq().setLocationRelativeTo(Vista);
        Vista.getTxtcopa().setText("");
        Vista.getTxtnom().setText("");
        Vista.getTxttip().setText("");
//        Vista.getTxtpre().setText("");
        if(origen==11){
            Vista.getDlgpaq().setTitle("Crear Paquete");
            Vista.getBtnacep().setVisible(false);
            Vista.getBtnsig().setVisible(true);
        }
            if(origen==22){
            Vista.getDlgpaq().setTitle("Editar Paquete");
            int cont = Vista.getTblpaq().getSelectedRow();
            if (cont != -1) {
                String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
                String nombre = Vista.getTblpaq().getValueAt(cont, 1).toString();
                String tipo = Vista.getTblpaq().getValueAt(cont, 2).toString();
//                String precio = Vista.getTblpaq().getValueAt(cont, 3).toString();
                
                Modelo_Menu p1 = new Modelo_Menu();
                p1.setCod_menu(cod_paq);
                Vista.getTxtcopa().setText(cod_paq);
                Vista.getTxtnom().setText(nombre);
//                Vista.getTxtpre().setText(precio);
                Vista.getTxttip().setText(tipo);
                Vista.getBtnsig().setVisible(false);
                Vista.getBtnacep().setVisible(true); 
        }
        
        }
        Vista.getDlgpaq().setVisible(true);
    }
    private void cargarDialogoEsmen(int origen){
        Vista.getDlgesmen().setSize(650,400);
        Vista.getDlgesmen().setLocationRelativeTo(Vista);
        Vista.getTxtcanmen().setText("");
        Vista.getTxtcomen().setText("");
        if(origen==33){
            Vista.getDlgesmen().setTitle("Escoger Menú");
            Vista.getBtnagre1().setVisible(false);
            Vista.getBtnagrmen().setVisible(true);
            Vista.getBtnca3().setVisible(false);
            Vista.getBtncanmen().setVisible(true);
            Vista.getBtnfi1().setVisible(false);
            Vista.getBtnsigme().setVisible(true);
        }
        if(origen==55){
            Vista.getDlgesmen().setTitle("Escoger Menú");
            Vista.getBtnagre1().setVisible(true);
            Vista.getBtnagrmen().setVisible(false);
            Vista.getBtnca3().setVisible(true);
            Vista.getBtncanmen().setVisible(false);
            Vista.getBtnfi1().setVisible(true);
            Vista.getBtnsigme().setVisible(false);
            
        }
        Vista.getDlgesmen().setVisible(true);
    }

    private void cargaListadosPa(String aguja){

        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblpaq().getModel();
        tblModel.setNumRows(0);
        List<Paquete> lista=Modelo.listaPaquete(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblpaq().setValueAt(per.getCod_paquete() , i.value , 0);
           Vista.getTblpaq().setValueAt(per.getNombre(), i.value , 1);
           Vista.getTblpaq().setValueAt(per.getTipo(), i.value , 2);
           Vista.getTblpaq().setValueAt(per.getPrecio(), i.value , 3);           
           i.value++;
          
        });

        
    }
    private void cargaListadosMenPa(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblesmen().getModel();
        tblModel.setNumRows(0);
        List<Menu> lista = ModeloMenu.listaMenu(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblesmen().setValueAt(per.getCod_menu() , i.value , 0);
           Vista.getTblesmen().setValueAt(per.getNombre(), i.value , 1);
           Vista.getTblesmen().setValueAt(per.getTipo() , i.value , 2);
           Vista.getTblesmen().setValueAt(per.getPrecio(), i.value , 3);
           i.value++;
          
        });

        
    }
    
    private void GuardarPaquete(){
        String cod_pa = Vista.getTxtcopa().getText();
        int cont = Vista.getTblmencos().getSelectedRow();
        int contin = Vista.getTblinvcos().getSelectedRow();
        int contem = Vista.getTblempcos().getSelectedRow();
        String pre = Vista.getTblmencos().getValueAt(cont, 0).toString();
        String invpre = Vista.getTblinvcos().getValueAt(contin, 0).toString();
        String emppre = Vista.getTblempcos().getValueAt(contem, 0).toString();
        int multi = Integer.parseInt(pre); 
        int multi2 = Integer.parseInt(invpre); 
        int multi3 = Integer.parseInt(emppre); 
        int multi4= multi+multi2+multi3;
        
      
        Modelo_Paquete paq = new Modelo_Paquete();
        paq.setCod_paquete(cod_pa);
        paq.setPrecio(multi4); 
        if (paq.EditarPaPre()) {
            cargaListadosPa("");
//            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se guardo ");
            Vista.getDlgesmen().setVisible(false);
            Vista.getDlgedmen().setVisible(false);
            
            Vista.getDlgcos().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro guardar");
        }
    }
    private void GuardarMenuP(){
      String cod_paq = Vista.getTxtcopa().getText();
      String nombre = Vista.getTxtnom().getText();
      String tipo = Vista.getTxttip().getText();
//      int precio = Integer.parseInt(Vista.getTxtpre().getText());
      
      Modelo_Paquete paquete = new Modelo_Paquete();
      paquete.setCod_paquete(cod_paq);
      paquete.setNombre(nombre);
      paquete.setTipo(tipo);
//      menu.setPrecio(precio);
       
        if (paquete.grabarPaq()) {
            
        } else {
            
        }
    }
    private void GuardarEscmen(){
      String cod_pa = Vista.getTxtcopa().getText();
      String cod_men = Vista.getTxtcomen().getText();
      int cantidad = Integer.parseInt(Vista.getTxtcanmen().getText());
      
      Modelo_Menu_Paquete menu_paquete = new Modelo_Menu_Paquete();
      menu_paquete.setCod_paq(cod_pa);
      menu_paquete.setCod_menu(cod_men);
      menu_paquete.setCantidad(cantidad);
       
        if (menu_paquete.grabarMenu_paq()) {
            cargaListadosPa("");
            
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
    public void AgregramMen(){
        int cont = Vista.getTblesmen().getSelectedRow();
            if (cont != -1) {
                String cod_menu = Vista.getTblesmen().getValueAt(cont, 0).toString();
                
                Modelo_Menu menu = new Modelo_Menu();
                menu.setCod_menu(cod_menu);
                Vista.getTxtcomen().setText(cod_menu);
        }
    }
    public void EliminarPaquete(){
        int ind=Vista.getTblpaq().getSelectedRow();
        
            String cod_paq = Vista.getTblpaq().getValueAt(ind, 0).toString();
            Modelo_Inventario_Paquete inve_paq = new Modelo_Inventario_Paquete();
            inve_paq.setCod_paq(cod_paq);
            if(inve_paq.EliminarInv_paquete()){
                Modelo_Menu_Paquete menu_paqu = new Modelo_Menu_Paquete();
                menu_paqu.setCod_paq(cod_paq);
                if(menu_paqu.EliminarMenu_paquete()){
                    Modelo_Empleado_Paquete emp_paqu = new Modelo_Empleado_Paquete();
                    emp_paqu.setCod_paq(cod_paq);
                    if(emp_paqu.EliminarEmple_paquete()){
                        Modelo_Paquete paque = new Modelo_Paquete();
                        paque.setCod_paquete(cod_paq);
                            if(paque.EliminarPaq()){
                                cargaListadosPa("");
                                JOptionPane.showMessageDialog(Vista, "Se elimino");
                            }else{
                                JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");         
                            } 
                    }else{
                        JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");
                    }
                }else{                  
                    JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");
                }
            }else{
                JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");
            }
        
    }
    public void EliminarMenuP(){
        
            String cod_paq = Vista.getTxtcopa().getText();
            Modelo_Menu_Paquete menu_paq = new Modelo_Menu_Paquete();
                menu_paq.setCod_paq(cod_paq);
                if(menu_paq.EliminarMenu_paquete()){
                    Modelo_Paquete paque = new Modelo_Paquete();
                    paque.setCod_paquete(cod_paq);
                    if(paque.EliminarPaq()){
                        cargaListadosPa("");
                        JOptionPane.showMessageDialog(Vista, "Asignación de menu cancelada");
                    }else{
                         JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");   
                        
                    }   
                }else{                  
                    
                }
        
    }
    public void EditarPaquete() {
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paque = Vista.getTblpaq().getValueAt(cont, 0).toString();
        String nombre = Vista.getTxtnom().getText();
        String tipo = Vista.getTxttip().getText();
        int contme = Vista.getTblmencos().getSelectedRow();
        int contin = Vista.getTblinvcos().getSelectedRow();
        int contem = Vista.getTblempcos().getSelectedRow();
        String pre = Vista.getTblmencos().getValueAt(contme, 0).toString();
        String invpre = Vista.getTblinvcos().getValueAt(contin, 0).toString();
        String emppre = Vista.getTblempcos().getValueAt(contem, 0).toString();
        int multi = Integer.parseInt(pre); 
        int multi2 = Integer.parseInt(invpre); 
        int multi3 = Integer.parseInt(emppre); 
        int multi4= multi+multi2+multi3;
        
        Modelo_Paquete paquete = new Modelo_Paquete();
        paquete.setCod_paquete(cod_paque);
        paquete.setNombre(nombre);
        paquete.setTipo(tipo);
        paquete.setPrecio(multi4);
        if (paquete.EditarPaq() == true) {
            cargaListadosPa("");
            Vista.getDlgpaq().setVisible(false);
            Vista.getDlgedmen().setVisible(false);
            Vista.getDlgesmen().setVisible(false);
            Vista.getDlgedinv().setVisible(false);
            Vista.getDlgesinv().setVisible(false);
            Vista.getDlgedemp().setVisible(false);
            Vista.getDlgesemp().setVisible(false);
            Vista.getDlgcos().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
    //DIALOGO DE EDICION INGREDIENTE_MENU
    public void EditarMenuPaqu() {
        int cont = Vista.getTblmenpaqed().getSelectedRow();
        String cod_paq = Vista.getTblmenpaqed().getValueAt(cont, 0).toString();
        String cod_men = Vista.getTblmenpaqed().getValueAt(cont, 1).toString();
        int cantidad = Integer.parseInt(Vista.getTxtcaned().getText());
        
        Modelo_Menu_Paquete men_paq = new Modelo_Menu_Paquete();
        men_paq.setCod_paq(cod_paq) ; 
        men_paq.setCod_menu(cod_men);
        men_paq.setCantidad(cantidad);
        if (men_paq.EditarMenu_paq() == true) {
            cargaListadosMen_Paq("");
            Vista.getDlgpaq().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
    public void EditarMenPaquCant(){
        int cont = Vista.getTblmenpaqed().getSelectedRow();
            if (cont != -1) {
                String cod_menu = Vista.getTblmenpaqed().getValueAt(cont, 1).toString();
                String cantidad = Vista.getTblmenpaqed().getValueAt(cont, 2).toString();
                Modelo_Menu_Paquete men_paqu = new Modelo_Menu_Paquete();
                men_paqu.setCod_menu(cod_menu);
                Vista.getTxtcomeed().setText(cod_menu);
                Vista.getTxtcaned().setText(cantidad);
        }
    }
    private void cargaListadosMen_Paq(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblmenpaqed().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        List<Menu_paquete> lista=Modelo_Menu_Paquete.listaMenu_PaqueteBus(cod_paq);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblmenpaqed().setValueAt(per.getCod_paq() , i.value , 0);
           Vista.getTblmenpaqed().setValueAt(per.getCod_menu(), i.value , 1);
           Vista.getTblmenpaqed().setValueAt(per.getCantidad(), i.value , 2);
           Vista.getTblmenpaqed().setValueAt(per.getNombre(), i.value , 3);
           i.value++;
          
        });   
    }
    public void EliminarMenuPaqueEd(){
        int cont = Vista.getTblmenpaqed().getSelectedRow();
        String cod_paq = Vista.getTblmenpaqed().getValueAt(cont, 0).toString();    
        String cod_men = Vista.getTxtcomeed().getText();
            Modelo_Menu_Paquete menu_paqu = new Modelo_Menu_Paquete();
            menu_paqu.setCod_paq(cod_paq) ;   
            menu_paqu.setCod_menu(cod_men);
                if(menu_paqu.EliminarMenu_paq()){
                        cargaListadosMen_Paq("");
                        JOptionPane.showMessageDialog(Vista, "Eliminado");
                    }else{
                         JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");   
       
                    }   
        
    }
    private void GuardarEdmen(){
      int ind=Vista.getTblpaq().getSelectedRow();
        
      String cod_paq = Vista.getTblpaq().getValueAt(ind, 0).toString();
      String cod_men = Vista.getTxtcomen().getText();
      int cantidad = Integer.parseInt(Vista.getTxtcanmen().getText());
      
      Modelo_Menu_Paquete menu_paque = new Modelo_Menu_Paquete();
      menu_paque.setCod_paq(cod_paq);
      menu_paque.setCod_menu(cod_men);
      menu_paque.setCantidad(cantidad);
       
        if (menu_paque.grabarMenu_paq()) {
            cargaListadosMen_Paq("");
            Vista.getDlgpaq().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
    private void cargarDialogoEsmenEd(int origen){
        Vista.getDlgedmen().setSize(650,400);
        Vista.getDlgedmen().setLocationRelativeTo(Vista);
        Vista.getTxtcomeed().setText("");
        Vista.getTxtcaned().setText("");
        if(origen==44){
            Vista.getDlgedmen().setTitle("Editar menú de paquete");
        }
        Vista.getDlgedmen().setVisible(true);
    }
    //INVENTARIO-PAQUETE
    private void cargarDialogoEsinv(int origen){
        Vista.getDlgesinv().setSize(650,400);
        Vista.getDlgesinv().setLocationRelativeTo(Vista);
        Vista.getTxtcaninv().setText("");
        Vista.getTxtcoin().setText("");
        if(origen==66){
            Vista.getDlgesinv().setTitle("Escoger Inventario");
            Vista.getBtnagre2().setVisible(false);
            Vista.getBtnagrin().setVisible(true);
            Vista.getBtnca6().setVisible(false);
            Vista.getBtncainv().setVisible(true);
            Vista.getBtnfi2().setVisible(false);
            Vista.getBtnsigin().setVisible(true);
        }
        if(origen==77){
            Vista.getDlgesmen().setTitle("Escoger Inventario");
            Vista.getBtnagre2().setVisible(true);
            Vista.getBtnagrin().setVisible(false);
            Vista.getBtnca6().setVisible(true);
            Vista.getBtncainv().setVisible(false);
            Vista.getBtnfi2().setVisible(true);
            Vista.getBtnsigin().setVisible(false);
        }
        Vista.getDlgesinv().setVisible(true);
    }
    private void cargaListadosInventar(String aguja){
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblesinv().getModel();
        tblModel.setNumRows(0);
        List<inventario> lista = Modelo_inventario.Listainventario(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblesinv().setValueAt(per.getCod_inventario() , i.value , 0);
           Vista.getTblesinv().setValueAt(per.getNombre(), i.value , 1);
           Vista.getTblesinv().setValueAt(per.getPrecio() , i.value , 2);
           i.value++;
        });
    }
    private void GuardarEscinv(){
      String cod_pa = Vista.getTxtcopa().getText();
      String cod_inv = Vista.getTxtcoin().getText();
      int cantidad = Integer.parseInt(Vista.getTxtcaninv().getText());
      
      Modelo_Inventario_Paquete invent_paquete = new Modelo_Inventario_Paquete();
      invent_paquete.setCod_paq(cod_pa);
      invent_paquete.setCod_inv(cod_inv);
      invent_paquete.setCantidad(cantidad);
       
        if (invent_paquete.grabarInv_paq()) {
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
    public void AgregrarInv(){
        int cont = Vista.getTblesinv().getSelectedRow();
            if (cont != -1) {
                String cod_inv = Vista.getTblesinv().getValueAt(cont, 0).toString();
                
                Modelo_inventario inventar = new Modelo_inventario();
                inventar.setCod_inventario(cod_inv);
                Vista.getTxtcoin().setText(cod_inv);
        }
    }
    public void EliminarInveCancelar(){
        
            String cod_paq = Vista.getTxtcopa().getText();
            Modelo_Inventario_Paquete inve_paq = new Modelo_Inventario_Paquete();
            inve_paq.setCod_paq(cod_paq);
            if(inve_paq.EliminarInv_paquete()){
                  JOptionPane.showMessageDialog(Vista, "Asignación de inventario cancelada");
            }else{
                JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");
            }       
                                
    }
    //EDICION INVENTARIO-PAQUETE
    public void EditarInvenPaqu() {
        int cont = Vista.getTblinvpaqed().getSelectedRow();
        String cod_paq = Vista.getTblinvpaqed().getValueAt(cont, 0).toString();
        String cod_inv = Vista.getTblinvpaqed().getValueAt(cont, 1).toString();
        int cantidad = Integer.parseInt(Vista.getTxtcaned1().getText());
        
        Modelo_Inventario_Paquete inve_paq = new Modelo_Inventario_Paquete();
        inve_paq.setCod_paq(cod_paq) ; 
        inve_paq.setCod_inv(cod_inv);
        inve_paq.setCantidad(cantidad);
        if (inve_paq.EditarInv_paq() == true) {
            cargaListadosInven_Paq("");
            Vista.getDlgpaq().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
    public void EditarInvPaquCant(){
        int cont = Vista.getTblinvpaqed().getSelectedRow();
            if (cont != -1) {
                String cod_inv = Vista.getTblinvpaqed().getValueAt(cont, 1).toString();
                String cantidad = Vista.getTblinvpaqed().getValueAt(cont, 2).toString();
                Modelo_Inventario_Paquete inve_paqu = new Modelo_Inventario_Paquete();
                inve_paqu.setCod_inv(cod_inv);
                Vista.getTxtcoined().setText(cod_inv);
                Vista.getTxtcaned1().setText(cantidad);
        }
    }
    private void cargaListadosInven_Paq(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblinvpaqed().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        List<Inventario_paquete> lista=Modelo_Inventario_Paquete.listaInventario_PaqueteBus(cod_paq);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblinvpaqed().setValueAt(per.getCod_paq() , i.value , 0);
           Vista.getTblinvpaqed().setValueAt(per.getCod_inv(), i.value , 1);
           Vista.getTblinvpaqed().setValueAt(per.getCantidad(), i.value , 2);
           Vista.getTblinvpaqed().setValueAt(per.getNombre(), i.value , 3);
           i.value++;
          
        });   
    }
    public void EliminarInventaPaqueEd(){
        int cont = Vista.getTblinvpaqed().getSelectedRow();
        String cod_paq = Vista.getTblinvpaqed().getValueAt(cont, 0).toString();    
        String cod_inv = Vista.getTxtcoined().getText();
            Modelo_Inventario_Paquete invent_paqu = new Modelo_Inventario_Paquete();
            invent_paqu.setCod_paq(cod_paq) ;   
            invent_paqu.setCod_inv(cod_inv);
                if(invent_paqu.EliminarInv_paq()){
                        cargaListadosInven_Paq("");
                        JOptionPane.showMessageDialog(Vista, "Eliminado");
                    }else{
                         JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");   
       
                    }      
    }
    private void GuardarEdInv(){
      int ind=Vista.getTblpaq().getSelectedRow();
        
      String cod_paq = Vista.getTblpaq().getValueAt(ind, 0).toString();
      String cod_inv = Vista.getTxtcoin().getText();
      int cantidad = Integer.parseInt(Vista.getTxtcaninv().getText());
      
      Modelo_Inventario_Paquete inve_paque = new Modelo_Inventario_Paquete();
      inve_paque.setCod_paq(cod_paq);
      inve_paque.setCod_inv(cod_inv);
      inve_paque.setCantidad(cantidad);
       
        if (inve_paque.grabarInv_paq()) {
            cargaListadosInven_Paq("");
            Vista.getDlgpaq().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
    private void cargarDialogoEsinvEd(int origen){
        Vista.getDlgedinv().setSize(650,450);
        Vista.getDlgedinv().setLocationRelativeTo(Vista);
        Vista.getTxtcoined().setText("");
        Vista.getTxtcaned1().setText("");
        if(origen==88){
            Vista.getDlgedinv().setTitle("Editar inventario de paquete");
        }
        Vista.getDlgedinv().setVisible(true);
    }
    //EMPLEADO-PAQUETE
    private void cargarDialogoEsemp(int origen){
        Vista.getDlgesemp().setSize(650,400);
        Vista.getDlgesemp().setLocationRelativeTo(Vista);
        if(origen==5){
            Vista.getDlgesemp().setTitle("Escoger Empleado");
            Vista.getBtnagre3().setVisible(false);
            Vista.getBtnagremp().setVisible(true);
            Vista.getBtnca7().setVisible(false);
            Vista.getBtncaemp().setVisible(true);
            Vista.getBtnfi3().setVisible(false);
            Vista.getBtnfi4().setVisible(true);
        }
        if(origen==100){
            Vista.getDlgesemp().setTitle("Escoger Empleado");
            Vista.getBtnagre3().setVisible(true);
            Vista.getBtnagremp().setVisible(false);
            Vista.getBtnca7().setVisible(true);
            Vista.getBtncaemp().setVisible(false);
            Vista.getBtnfi3().setVisible(true);
            Vista.getBtnfi4().setVisible(false);
            
        }
        Vista.getDlgesemp().setVisible(true);
    }
    private void cargaListadosEmpleado(String aguja){
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblesemp().getModel();
        tblModel.setNumRows(0);
        List<empleado> lista = Modelo_empleado.ListaEmpleados(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblesemp().setValueAt(per.getCi() , i.value , 0);
           Vista.getTblesemp().setValueAt(per.getNom_car(), i.value , 1);
           i.value++;
        });
    }
    private void GuardarEscemp(){
      int cont = Vista.getTblesemp().getSelectedRow();
      String cod_emp = Vista.getTblesemp().getValueAt(cont, 0).toString();
      String cod_pa = Vista.getTxtcopa().getText();
      
      Modelo_Empleado_Paquete emp_paquete = new Modelo_Empleado_Paquete();
      emp_paquete.setCod_paq(cod_pa);
      emp_paquete.setCod_emp(cod_emp);
       
        if (emp_paquete.grabarEmpleado_paq()) {
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }

    public void EliminarEmpleCancelar(){
        
            String cod_paq = Vista.getTxtcopa().getText();
            Modelo_Empleado_Paquete emple_paq = new Modelo_Empleado_Paquete();
            emple_paq.setCod_paq(cod_paq);
            if(emple_paq.EliminarEmple_paquete()){
                  JOptionPane.showMessageDialog(Vista, "Asignación de empleado cancelada");
            }else{
                JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");
            }       
                                
    }
    

    //EDICION INVENTARIO-PAQUETE
    private void cargaListadosEmple_Paq(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblemppaqed().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        List<Empleado_paquete> lista=Modelo_Empleado_Paquete.listaEmpleado_PaqueteBus(cod_paq);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblemppaqed().setValueAt(per.getCod_paq() , i.value , 0);
           Vista.getTblemppaqed().setValueAt(per.getCod_emp(), i.value , 1);
           Vista.getTblemppaqed().setValueAt(per.getNombre(), i.value , 2);
           i.value++;
          
        });   
    }
    public void EliminarEmpleaPaqueEd(){
        int cont = Vista.getTblemppaqed().getSelectedRow();
        String cod_paq = Vista.getTblemppaqed().getValueAt(cont, 0).toString();
        String cod_emp = Vista.getTblemppaqed().getValueAt(cont, 1).toString(); 
            Modelo_Empleado_Paquete emple_paqu = new Modelo_Empleado_Paquete();
            emple_paqu.setCod_paq(cod_paq) ;   
            emple_paqu.setCod_emp(cod_emp);
                if(emple_paqu.EliminarEmple_paq()){
                        cargaListadosEmple_Paq("");
                        JOptionPane.showMessageDialog(Vista, "Eliminado");
                    }else{
                         JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");   
       
                    }      
    }
    private void GuardarEdEmp(){
      int ind=Vista.getTblpaq().getSelectedRow();
      int ind2=Vista.getTblesemp().getSelectedRow();
        
      String cod_paq = Vista.getTblpaq().getValueAt(ind, 0).toString();
      String cod_emp = Vista.getTblesemp().getValueAt(ind2, 0).toString();
      
      Modelo_Empleado_Paquete emple_paque = new Modelo_Empleado_Paquete();
      emple_paque.setCod_paq(cod_paq);
      emple_paque.setCod_emp(cod_emp);
       
        if (emple_paque.grabarEmpleado_paq()) {
            cargaListadosEmple_Paq("");
            Vista.getDlgpaq().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
    private void cargarDialogoEsempEd(int origen){
        Vista.getDlgedemp().setSize(650,450);
        Vista.getDlgedemp().setLocationRelativeTo(Vista);
        if(origen==7){
            Vista.getDlgedemp().setTitle("Editar empleado de paquete");
        }
        Vista.getDlgedemp().setVisible(true);
    }
    //costo
    private void cargarDialogoCosto(int origen){
        Vista.getDlgcos().setSize(500,280);
        Vista.getDlgcos().setLocationRelativeTo(Vista);
        if(origen==123){
            Vista.getDlgcos().setTitle("Costo");
            Vista.getBtnfinal1().setVisible(false);
            Vista.getBtnfinal().setVisible(true);
        }
        if(origen==234){
            Vista.getDlgcos().setTitle("Costo");
            Vista.getBtnfinal1().setVisible(true);
             Vista.getBtnfinal().setVisible(false);
        }
        Vista.getDlgcos().setVisible(true);
    }
    private void cargaListadosCostoMen(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblmencos().getModel();
        tblModel.setNumRows(0);
        String cod_pa = Vista.getTxtcopa().getText();
        List<Menu_paquete> lista=Modelo_Menu_Paquete.listaMenu_PaqueteCos(cod_pa);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblmencos().setValueAt(per.getCantidad() , i.value , 0);
           i.value++;
          
        });   
    }
    private void cargaListadosCostoInv(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblinvcos().getModel();
        tblModel.setNumRows(0);
        String cod_pa = Vista.getTxtcopa().getText();
        List<Inventario_paquete> lista=Modelo_Inventario_Paquete.listaInventario_PaqueteCos(cod_pa);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblinvcos().setValueAt(per.getCantidad() , i.value , 0);
           i.value++;
          
        });   
    }
    private void cargaListadosCostoEmp(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblempcos().getModel();
        tblModel.setNumRows(0);
        String cod_pa = Vista.getTxtcopa().getText();
        List<Empleado_paquete> lista=Modelo_Empleado_Paquete.listaEmpleado_PaqueteCos(cod_pa);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblempcos().setValueAt(per.getCantidad() , i.value , 0);
           i.value++;
          
        });   
    }
    //costo-edicion
    private void cargaListadosCostoMenEd(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblmencos().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        List<Menu_paquete> lista=Modelo_Menu_Paquete.listaMenu_PaqueteCos(cod_paq);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblmencos().setValueAt(per.getCantidad() , i.value , 0);
           i.value++;
          
        });   
    }
    private void cargaListadosCostoInvEd(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblinvcos().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        List<Inventario_paquete> lista=Modelo_Inventario_Paquete.listaInventario_PaqueteCos(cod_paq);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblinvcos().setValueAt(per.getCantidad() , i.value , 0);
           i.value++;
          
        });   
    }
    private void cargaListadosCostoEmpEd(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblempcos().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblpaq().getSelectedRow();
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        List<Empleado_paquete> lista=Modelo_Empleado_Paquete.listaEmpleado_PaqueteCos(cod_paq);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblempcos().setValueAt(per.getCantidad() , i.value , 0);
           i.value++;
          
        });   
    }
}
