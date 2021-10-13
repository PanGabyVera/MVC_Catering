
package Controlador;

import Modelo.Ingrediente_menu;
import Modelo.Ingredientes;
import Modelo.Menu;
import Modelo.Menu_paquete;
import Modelo.Modelo_ConexionBD;
import Modelo.Modelo_Ingrediente;
import Modelo.Modelo_Ingrediente_Menu;
import Vista.Vista_Menu;
import Modelo.Modelo_Menu;
import Modelo.Modelo_Menu_Paquete;
import Modelo.Modelo_Paquete;
import Modelo.Paquete;
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
import sun.swing.table.DefaultTableCellHeaderRenderer;


public class Controlador_Paquete extends Paquete{
    private Modelo_Paquete Modelo;
    private Modelo_Menu ModeloMenu;
    private Vista_Paquete Vista;
    private Modelo_Menu_Paquete Modelo_Menu_Paquete;
    public Controlador_Paquete(Modelo_Paquete Modelo, Vista_Paquete Vista, Modelo_Menu ModeloMenu,Modelo_Menu_Paquete Modelo_Menu_Paquete) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ModeloMenu = ModeloMenu;
        this.Modelo_Menu_Paquete =Modelo_Menu_Paquete;
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
    Vista.getBtnfinme().addActionListener(l->GuardarPaquete());
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
    Vista.getBtnfi1().addActionListener(l->cargarDialogo(22));
//    Vista.getBtnim().addActionListener(l-> ImprimirRegistro());
    Vista.getBtnca4().addActionListener(l->Vista.getDlgpaq().setVisible(false));
    Vista.getBtnca2().addActionListener(l->Vista.getDlgedmen().setVisible(false));
    Vista.getBtncanmen().addActionListener(l->Vista.getDlgesmen().setVisible(false));
    Vista.getBtncanmen().addActionListener(l->Vista.getDlgpaq().setVisible(true));
    Vista.getBtnca3().addActionListener(l->Vista.getDlgesmen().setVisible(false));
    //Controlador Buscar
    Vista.getTxtbu().addKeyListener(kl);
    
    
    }
//    private void  ImprimirRegistro(){
//        Modelo_ConexionBD con =new Modelo_ConexionBD();
//        try {
//        JasperReport jr= (JasperReport)JRLoader.loadObject(getClass().getResource("/mvc/vista/reportes/ListaPersonas.jasper"));
//        Map<String,Object> parametros= new HashMap<String, Object>();
//        String aguja = vista.getTxtbu().getText();
//        parametros.put("paguja" , "%"+aguja+"%");
//        JasperPrint jp= JasperFillManager.fillReport(jr, parametros,con.getCon());
//        JasperViewer jv= new JasperViewer(jp);
//        jv.setVisible(true);    
//        } catch (JRException ex) {
//            Logger.getLogger(ControlPersona.class.getName()).log(Level.SEVERE,null,ex);
//        }      
//    } 
    private void cargarDialogo(int origen){
        Vista.getDlgpaq().setSize(600,350);
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
            Vista.getDlgesmen().setTitle("Escoger Ingrediente");
            Vista.getBtnagre1().setVisible(false);
            Vista.getBtnagrmen().setVisible(true);
            Vista.getBtnca3().setVisible(false);
            Vista.getBtncanmen().setVisible(true);
            Vista.getBtnfi1().setVisible(false);
            Vista.getBtnfinme().setVisible(true);
        }
        if(origen==55){
            Vista.getDlgesmen().setTitle("Escoger Ingrediente");
            Vista.getBtnagre1().setVisible(true);
            Vista.getBtnagrmen().setVisible(false);
            Vista.getBtnca3().setVisible(true);
            Vista.getBtncanmen().setVisible(false);
            Vista.getBtnfi1().setVisible(true);
            Vista.getBtnfinme().setVisible(false);
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
//      String cod_menu = Vista.getTxtcome().getText();
//      String nombre = Vista.getTxtnom().getText();
//      String tipo = Vista.getTxttip().getText();
//      int precio = Integer.parseInt(Vista.getTxtpre().getText());
//      
//      Modelo_Menu menu = new Modelo_Menu();
//      menu.setCod_menu(cod_menu);
//      menu.setNombre(nombre);
//      menu.setTipo(tipo);
//      menu.setPrecio(precio);
//       
//        if (menu.grabarMen()) {
            cargaListadosPa("");
//            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se guardo ");
            Vista.getDlgesmen().setVisible(false);
            Vista.getDlgedmen().setVisible(false);
            Vista.getDlgpaq().setVisible(false);
//        } else {
//            JOptionPane.showMessageDialog(Vista, "No se logro guardar");
//        }
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
            Modelo_Menu_Paquete menu_paqu = new Modelo_Menu_Paquete();
                menu_paqu.setCod_menu(cod_paq);
                if(menu_paqu.EliminarMenu_paq()){
                    Modelo_Paquete paquete = new Modelo_Paquete();
                    paquete.setCod_paquete(cod_paq);
                    if(paquete.EliminarPaq()){
                        cargaListadosPa("");
                        JOptionPane.showMessageDialog(Vista, "Se elimino");
                    }else{
                   
                        JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");
                    }   
                }else{                  
                    JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");
                }
        
    }
    public void EliminarMenuP(){
        
            String cod_paq = Vista.getTxtcomen().getText();
            Modelo_Menu_Paquete menu_paq = new Modelo_Menu_Paquete();
                menu_paq.setCod_paq(cod_paq);
                if(menu_paq.EliminarMenu_paq()){
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
        String cod_paq = Vista.getTblpaq().getValueAt(cont, 0).toString();
        String nombre = Vista.getTxtnom().getText();
        String tipo = Vista.getTxttip().getText();
//        int precio = Integer.parseInt(Vista.getTxtpre().getText());
        
        Modelo_Paquete paquete = new Modelo_Paquete();
        paquete.setCod_paquete(cod_paq);
        paquete.setNombre(nombre);
        paquete.setTipo(tipo);
//        paquete.setPrecio(precio);
        if (paquete.EditarPaq() == true) {
            cargaListadosPa("");
            Vista.getDlgpaq().setVisible(false);
            Vista.getDlgedmen().setVisible(false);
            Vista.getDlgesmen().setVisible(false);
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
        
        List<Menu_paquete> lista=Modelo_Menu_Paquete.listaMenu_Paquete(aguja);
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
}