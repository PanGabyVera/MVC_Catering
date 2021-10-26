
package Controlador;

import Modelo.Ingrediente_menu;
import Modelo.Ingredientes;
import Modelo.Menu;
import Modelo.Modelo_ConexionBD;
import Modelo.Modelo_Ingrediente;
import Modelo.Modelo_Ingrediente_Menu;
import Vista.Vista_Menu;
import Modelo.Modelo_Menu;
import Vista.Vista_MenuPrincipal;
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


public class Controlador_Menu extends Menu{
    private Modelo_Menu Modelo;
    private Modelo_Ingrediente ModeloIngre;
    private Vista_Menu Vista;
    private Modelo_Ingrediente_Menu Modelo_Ingre_Men;
    public Controlador_Menu(Modelo_Menu Modelo, Vista_Menu Vista, Modelo_Ingrediente ModeloIngre,Modelo_Ingrediente_Menu Modelo_Ingre_Men) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ModeloIngre = ModeloIngre;
        this.Modelo_Ingre_Men =Modelo_Ingre_Men;
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
                cargaListadosMe(Vista.getTxtbu().getText());
            }
        };

   //Controlar los eventos de la vista
    Vista.getBtnli().addActionListener(l->cargaListadosMe(""));
    Vista.getBtncr().addActionListener(l->cargarDialogo(11));
    Vista.getBtned().addActionListener(l->cargarDialogoEsingEd(44));
    Vista.getBtned().addActionListener(l->cargaListadosIng_Menu(""));
    Vista.getBtnsig().addActionListener(l->cargarDialogoEsing(33));
    Vista.getBtnsig().addActionListener(l->cargaListadosIngMe(""));
    Vista.getBtnsig().addActionListener(l->GuardarMenuP());
    Vista.getBtnfi().addActionListener(l->GuardarMenu());
    Vista.getBtnacep().addActionListener(l->EditarMenu());
    Vista.getBtnel().addActionListener(l-> EliminarMenu());
    Vista.getBtncaring().addActionListener(l->AgregramIng());
    Vista.getBtnca().addActionListener(l->EliminarMenuP());
    Vista.getBtnagre().addActionListener(l->GuardarEscing());
    //EDITAR INGREDIENTE_MENU
    Vista.getBtncared().addActionListener(l->EditarIngMe());
    Vista.getBtnedime().addActionListener(l->EditarIngreMenu());
    Vista.getBtnsiged().addActionListener(l->cargarDialogoEsing(55));
    Vista.getBtnsiged().addActionListener(l->cargaListadosIngMe(""));
    Vista.getBtnelied().addActionListener(l->EliminarIngreMenu());
    Vista.getBtnagre1().addActionListener(l->GuardarEdcing());
    Vista.getBtnca3().addActionListener(l->cargaListadosIng_Menu(""));
    Vista.getBtnfi1().addActionListener(l->cargarDialogo(22));
    Vista.getBtnim().addActionListener(l-> ImprimirRegistro());
    Vista.getBtnca1().addActionListener(l->Vista.getDlgmen().setVisible(false));
    Vista.getBtnca2().addActionListener(l->Vista.getDlgedcing().setVisible(false));
    Vista.getBtnca().addActionListener(l->Vista.getDlgescing().setVisible(false));
    Vista.getBtnca().addActionListener(l->Vista.getDlgmen().setVisible(true));
    Vista.getBtnca3().addActionListener(l->Vista.getDlgescing().setVisible(false));
    //Controlador Buscar
    Vista.getTxtbu().addKeyListener(kl);
    
    
    }
    private void  ImprimirRegistro(){
        Modelo_ConexionBD con =new Modelo_ConexionBD();
        try {
        JasperReport jr= (JasperReport)JRLoader.loadObject(getClass().getResource("/Vista/reportes/ListaMenú.jasper"));
//        Map<String,Object> parametros= new HashMap<String, Object>();
//        String aguja = Vista.getTxtbu().getText();
//        parametros.put("paguja" , "%"+aguja+"%");
        JasperPrint jp= JasperFillManager.fillReport(jr, null,con.getCon());
        JasperViewer jv= new JasperViewer(jp);
        jv.setVisible(true);    
        } catch (JRException ex) {
            Logger.getLogger(Controlador_Menu.class.getName()).log(Level.SEVERE,null,ex);
        }      
    } 
    private void cargarDialogo(int origen){
        Vista.getDlging().setSize(600,350);
        Vista.getDlging().setLocationRelativeTo(Vista);
        Vista.getTxtcome().setText("");
        Vista.getTxtnom().setText("");
        Vista.getTxttip().setText("");
        Vista.getTxtpre().setText("");
        if(origen==11){
            Vista.getDlging().setTitle("Crear Ingrediente");
            Vista.getBtnacep().setVisible(false);
            Vista.getBtnsig().setVisible(true);
        }
            if(origen==22){
            Vista.getDlging().setTitle("Editar Ingrediente");
            int cont = Vista.getTblmen().getSelectedRow();
            if (cont != -1) {
                String cod_menu = Vista.getTblmen().getValueAt(cont, 0).toString();
                String nombre = Vista.getTblmen().getValueAt(cont, 1).toString();
                String tipo = Vista.getTblmen().getValueAt(cont, 2).toString();
                String precio = Vista.getTblmen().getValueAt(cont, 3).toString();
                
                Modelo_Menu p1 = new Modelo_Menu();
                p1.setCod_menu(cod_menu);
                Vista.getTxtcome().setText(cod_menu);
                Vista.getTxtnom().setText(nombre);
                Vista.getTxtpre().setText(precio);
                Vista.getTxttip().setText(tipo);
                Vista.getBtnsig().setVisible(false);
                Vista.getBtnacep().setVisible(true); 
        }
        
        }
        Vista.getDlgmen().setVisible(true);
    }
    private void cargarDialogoEsing(int origen){
        Vista.getDlgescing().setSize(650,400);
        Vista.getDlgescing().setLocationRelativeTo(Vista);
        Vista.getTxtcan().setText("");
        Vista.getTxtcoin().setText("");
        if(origen==33){
            Vista.getDlgescing().setTitle("Escoger Ingrediente");
            Vista.getBtnagre1().setVisible(false);
            Vista.getBtnagre().setVisible(true);
            Vista.getBtnca3().setVisible(false);
            Vista.getBtnca().setVisible(true);
            Vista.getBtnfi1().setVisible(false);
            Vista.getBtnfi().setVisible(true);
        }
        if(origen==55){
            Vista.getDlgescing().setTitle("Escoger Ingrediente");
            Vista.getBtnagre1().setVisible(true);
            Vista.getBtnagre().setVisible(false);
            Vista.getBtnca3().setVisible(true);
            Vista.getBtnca().setVisible(false);
            Vista.getBtnfi1().setVisible(true);
            Vista.getBtnfi().setVisible(false);
        }
        Vista.getDlgescing().setVisible(true);
    }
    private void cargarDialogoEsingEd(int origen){
        Vista.getDlgedcing().setSize(650,400);
        Vista.getDlgedcing().setLocationRelativeTo(Vista);
        Vista.getTxtcoined().setText("");
        Vista.getTxtcaned().setText("");
        if(origen==44){
            Vista.getDlgedcing().setTitle("Editar menú de paquete");
        }
        Vista.getDlgedcing().setVisible(true);
    }

    private void cargaListadosMe(String aguja){
    
        
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblmen().getModel();
        tblModel.setNumRows(0);
        List<Menu> lista=Modelo.listaMenu(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblmen().setValueAt(per.getCod_menu() , i.value , 0);
           Vista.getTblmen().setValueAt(per.getNombre(), i.value , 1);
           Vista.getTblmen().setValueAt(per.getTipo(), i.value , 2);
           Vista.getTblmen().setValueAt(per.getPrecio(), i.value , 3);           
           i.value++;
          
        });

        
    }
    private void cargaListadosIngMe(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblingme().getModel();
        tblModel.setNumRows(0);
        List<Ingredientes> lista=ModeloIngre.listaIngrediente(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblingme().setValueAt(per.getCod_ingrediente() , i.value , 0);
           Vista.getTblingme().setValueAt(per.getNombre(), i.value , 1);
           i.value++;
          
        });

        
    }
    
    private void GuardarMenu(){
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
            cargaListadosMe("");
//            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se guardo ");
            Vista.getDlgescing().setVisible(false);
//        } else {
//            JOptionPane.showMessageDialog(Vista, "No se logro guardar");
//        }
    }
    private void GuardarMenuP(){
      String cod_menu = Vista.getTxtcome().getText();
      String nombre = Vista.getTxtnom().getText();
      String tipo = Vista.getTxttip().getText();
      int precio = Integer.parseInt(Vista.getTxtpre().getText());
      
      Modelo_Menu menu = new Modelo_Menu();
      menu.setCod_menu(cod_menu);
      menu.setNombre(nombre);
      menu.setTipo(tipo);
      menu.setPrecio(precio);
       
        if (menu.grabarMen()) {
            
        } else {
            
        }
    }
    private void GuardarEscing(){
      String cod_menu = Vista.getTxtcome().getText();
      String cod_ing = Vista.getTxtcoin().getText();
      int cantidad = Integer.parseInt(Vista.getTxtcan().getText());
      
      Modelo_Ingrediente_Menu ingre_menu = new Modelo_Ingrediente_Menu();
      ingre_menu.setCod_menu(cod_menu);
      ingre_menu.setCod_ing(cod_ing);
      ingre_menu.setCantidad(cantidad);
       
        if (ingre_menu.grabarIng_menu()) {
            cargaListadosMe("");
            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
    public void AgregramIng(){
        int cont = Vista.getTblingme().getSelectedRow();
            if (cont != -1) {
                String cod_ingrediente = Vista.getTblingme().getValueAt(cont, 0).toString();
                
                Modelo_Ingrediente ingre = new Modelo_Ingrediente();
                ingre.setCod_ingrediente(cod_ingrediente);
                Vista.getTxtcoin().setText(cod_ingrediente);
        }
    }
    
    
    
    public void EliminarMenu(){
        int ind=Vista.getTblmen().getSelectedRow();
        
            String cod_menu = Vista.getTblmen().getValueAt(ind, 0).toString();
            Modelo_Ingrediente_Menu ingre_menu = new Modelo_Ingrediente_Menu();
                ingre_menu.setCod_menu(cod_menu);
                if(ingre_menu.EliminarIngre_menu()){
                    Modelo_Menu menu = new Modelo_Menu();
                    menu.setCod_menu(cod_menu);
                    if(menu.EliminarMe()){
                        cargaListadosMe("");
                        JOptionPane.showMessageDialog(Vista, "Se elimino");
                    }else{
                   
                        JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");
                    }   
                }else{                  
                    JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");
                }
        
    }
    public void EliminarMenuP(){
        
            String cod_menu = Vista.getTxtcome().getText();
            Modelo_Ingrediente_Menu ingre_menu = new Modelo_Ingrediente_Menu();
                ingre_menu.setCod_menu(cod_menu);
                if(ingre_menu.EliminarIngre_menu()){
                    Modelo_Menu menu = new Modelo_Menu();
                    menu.setCod_menu(cod_menu);
                    if(menu.EliminarMe()){
                        cargaListadosMe("");
                        JOptionPane.showMessageDialog(Vista, "Asignación de ingredientes cancelada");
                    }else{
                         JOptionPane.showMessageDialog(Vista, "No se pudo cancelar");   
                        
                    }   
                }else{                  
                    
                }
        
    }
    
    public void EditarMenu() {
        int cont = Vista.getTblmen().getSelectedRow();
        String cod_menu = Vista.getTblmen().getValueAt(cont, 0).toString();
        String nombre = Vista.getTxtnom().getText();
        String tipo = Vista.getTxttip().getText();
        int precio = Integer.parseInt(Vista.getTxtpre().getText());
        
        Modelo_Menu menu = new Modelo_Menu();
        menu.setCod_menu(cod_menu);
        menu.setNombre(nombre);
        menu.setTipo(tipo);
        menu.setPrecio(precio);
        if (menu.EditarMe() == true) {
            cargaListadosMe("");
            Vista.getDlging().setVisible(false);
            Vista.getDlgedcing().setVisible(false);
            Vista.getDlgescing().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
    //DIALOGO DE EDICION INGREDIENTE_MENU
    public void EditarIngreMenu() {
        int cont = Vista.getTblingmeed().getSelectedRow();
        String cod_menu = Vista.getTblingmeed().getValueAt(cont, 0).toString();
        String cod_ing = Vista.getTblingmeed().getValueAt(cont, 1).toString();
        int cantidad = Integer.parseInt(Vista.getTxtcaned().getText());
        
        Modelo_Ingrediente_Menu ingre_me = new Modelo_Ingrediente_Menu();
        ingre_me.setCod_menu(cod_menu) ; 
        ingre_me.setCod_ing(cod_ing);
        ingre_me.setCantidad(cantidad);
        if (ingre_me.EditarIng_menu() == true) {
            cargaListadosIng_Menu("");
            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
    public void EditarIngMe(){
        int cont = Vista.getTblingmeed().getSelectedRow();
            if (cont != -1) {
                String cod_ing = Vista.getTblingmeed().getValueAt(cont, 1).toString();
                String cantidad = Vista.getTblingmeed().getValueAt(cont, 2).toString();
                Modelo_Ingrediente_Menu ingre_me = new Modelo_Ingrediente_Menu();
                ingre_me.setCod_ing(cod_ing);
                Vista.getTxtcoined().setText(cod_ing);
                Vista.getTxtcaned().setText(cantidad);
        }
    }
    private void cargaListadosIng_Menu(String aguja){
 
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTblingmeed().getModel();
        tblModel.setNumRows(0);
        int cont = Vista.getTblmen().getSelectedRow();
        String cod_menu = Vista.getTblmen().getValueAt(cont, 0).toString();
        List<Ingrediente_menu> lista=Modelo_Ingre_Men.listaIngrediente_MenuBus(cod_menu);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTblingmeed().setValueAt(per.getCod_menu() , i.value , 0);
           Vista.getTblingmeed().setValueAt(per.getCod_ing(), i.value , 1);
           Vista.getTblingmeed().setValueAt(per.getCantidad(), i.value , 2);
           Vista.getTblingmeed().setValueAt(per.getNombre(), i.value , 3);
           i.value++;
          
        });   
    }
    public void EliminarIngreMenu(){
        int cont = Vista.getTblingmeed().getSelectedRow();
        String cod_menu = Vista.getTblingmeed().getValueAt(cont, 0).toString();    
        String cod_ing = Vista.getTxtcoined().getText();
            Modelo_Ingrediente_Menu ingre_menu = new Modelo_Ingrediente_Menu();
            ingre_menu.setCod_menu(cod_menu) ;   
            ingre_menu.setCod_ing(cod_ing);
                if(ingre_menu.EliminarIng_menu()){
                        cargaListadosIng_Menu("");
                        JOptionPane.showMessageDialog(Vista, "Eliminado");
                    }else{
                         JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");   
       
                    }   
        
    }
    private void GuardarEdcing(){
      int indmen=Vista.getTblmen().getSelectedRow();  
      String cod_menu = Vista.getTblmen().getValueAt(indmen, 0).toString();
      String cod_ing = Vista.getTxtcoin().getText();
      int cantidad = Integer.parseInt(Vista.getTxtcan().getText());
      
      Modelo_Ingrediente_Menu ingre_menu = new Modelo_Ingrediente_Menu();
      ingre_menu.setCod_menu(cod_menu);
      ingre_menu.setCod_ing(cod_ing);
      ingre_menu.setCantidad(cantidad);
       
        if (ingre_menu.grabarIng_menu()) {
            cargaListadosIng_Menu("");
            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se agrego ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro agregar");
        }
    }
}
