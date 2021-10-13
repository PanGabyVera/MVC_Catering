
package Controlador;

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
import sun.swing.table.DefaultTableCellHeaderRenderer;


public class Controlador_Menu extends Menu{
    private Modelo_Menu Modelo;
    private Modelo_Ingrediente ModeloIngre;
    private Vista_Menu Vista;
    public Controlador_Menu(Modelo_Menu Modelo, Vista_Menu Vista, Modelo_Ingrediente ModeloIngre) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ModeloIngre = ModeloIngre;
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
    Vista.getBtned().addActionListener(l->cargarDialogo(22));
    Vista.getBtnsig().addActionListener(l->cargarDialogoEsing(33));
    Vista.getBtnsig().addActionListener(l->cargaListadosIngMe(""));
    Vista.getBtnsig().addActionListener(l->GuardarMenuP());
    Vista.getBtnfi().addActionListener(l->GuardarMenu());
    Vista.getBtnacep().addActionListener(l->EditarMenu());
    Vista.getBtnel().addActionListener(l-> EliminarMenu());
    Vista.getBtncaring().addActionListener(l->AgregramIng());
    Vista.getBtnca().addActionListener(l->EliminarMenuP());
    Vista.getBtnagre().addActionListener(l->GuardarEscing());
//    Vista.getBtnim().addActionListener(l-> ImprimirRegistro());
    Vista.getBtnca1().addActionListener(l->Vista.getDlgmen().setVisible(false));
    Vista.getBtnca().addActionListener(l->Vista.getDlgescing().setVisible(false));
    Vista.getBtnca().addActionListener(l->Vista.getDlgmen().setVisible(true));
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
        }
        Vista.getDlgescing().setVisible(true);
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
                if(ingre_menu.EliminarIng_menu()){
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
                if(ingre_menu.EliminarIng_menu()){
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
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
}
