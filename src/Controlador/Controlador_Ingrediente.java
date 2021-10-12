
package Controlador;

import Modelo.Ingredientes;
import Modelo.Modelo_ConexionBD;
import Vista.Vista_Ingrediente;
import Modelo.Modelo_Ingrediente;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import sun.swing.table.DefaultTableCellHeaderRenderer;


public class Controlador_Ingrediente extends Ingredientes{
    private Modelo_Ingrediente Modelo;
    private Vista_Ingrediente Vista;
    public Controlador_Ingrediente(Modelo_Ingrediente Modelo, Vista_Ingrediente Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;
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
                cargaListados(Vista.getTxtbu().getText());
            }
        };

   //Controlar los eventos de la vista
    Vista.getBtnli().addActionListener(l->cargaListados(""));
    Vista.getBtncr().addActionListener(l->cargarDialogo(11));
    Vista.getBtned().addActionListener(l->cargarDialogo(22));
    Vista.getBtnac1().addActionListener(l->GuardarIngrediente());
    Vista.getBtnac2().addActionListener(l->EditarIngrediente());
    Vista.getBtnel().addActionListener(l-> EliminarIngrediente());
//    Vista.getBtnim().addActionListener(l-> ImprimirRegistro());
    Vista.getBtnca().addActionListener(l->Vista.getDlging().setVisible(false));
    
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
        Vista.getTxtcoin().setText("");
        Vista.getTxtnom().setText("");
        if(origen==11){
            Vista.getDlging().setTitle("Crear Ingrediente");
            Vista.getBtnac2().setVisible(false);
            Vista.getBtnac1().setVisible(true);
        }
            if(origen==22){
            Vista.getDlging().setTitle("Editar Ingrediente");
            int cont = Vista.getTbling().getSelectedRow();
            if (cont != -1) {
            String cod_ingrediente = Vista.getTbling().getValueAt(cont, 0).toString();
                String nombre = Vista.getTbling().getValueAt(cont, 1).toString();
                
                Modelo_Ingrediente p1 = new Modelo_Ingrediente();
                p1.setCod_ingrediente(cod_ingrediente);
                Vista.getTxtcoin().setText(cod_ingrediente);
                Vista.getTxtnom().setText(nombre);
                Vista.getBtnac1().setVisible(false);
                Vista.getBtnac2().setVisible(true); 
        }
        
        }
        Vista.getDlging().setVisible(true);
    }
    

    private void cargaListados(String aguja){
    
        
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)Vista.getTbling().getModel();
        tblModel.setNumRows(0);
        List<Ingredientes> lista=Modelo.listaIngrediente(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           Vista.getTbling().setValueAt(per.getCod_ingrediente() , i.value , 0);
           Vista.getTbling().setValueAt(per.getNombre(), i.value , 1);
           i.value++;
          
        });

        
    }
    
    private void GuardarIngrediente(){
      String cod_ingrediente = Vista.getTxtcoin().getText();
      String nombre = Vista.getTxtnom().getText();
      
      Modelo_Ingrediente ingre = new Modelo_Ingrediente();
      ingre.setCod_ingrediente(cod_ingrediente);
      ingre.setNombre(nombre);
       
        if (ingre.grabar()) {
            cargaListados("");
            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Se guardo ");
        } else {
            JOptionPane.showMessageDialog(Vista, "No se logro guardar");
        }
    }
    public void EliminarIngrediente(){
        int ind=Vista.getTbling().getSelectedRow();
        
            String cod_ingrediente = Vista.getTbling().getValueAt(ind, 0).toString();
                Modelo_Ingrediente ingre = new Modelo_Ingrediente();
                ingre.setCod_ingrediente(cod_ingrediente);
                if(ingre.Eliminar()){
                    cargaListados("");
                   JOptionPane.showMessageDialog(Vista, "Se elimino");
                }else{
                   
                   JOptionPane.showMessageDialog(Vista, "No se pudo eliminar");
                }
        
    }
    public void EditarIngrediente() {
        int cont = Vista.getTbling().getSelectedRow();
        String cod_ingrediente = Vista.getTbling().getValueAt(cont, 0).toString();
        String nombre = Vista.getTxtnom().getText();
        
        Modelo_Ingrediente ingre = new Modelo_Ingrediente();
        ingre.setCod_ingrediente(cod_ingrediente);
        ingre.setNombre(nombre);
        if (ingre.Editar() == true) {
            cargaListados("");
            Vista.getDlging().setVisible(false);
            JOptionPane.showMessageDialog(Vista, "Registro actualizado");
        } else {
            JOptionPane.showMessageDialog(Vista, "Hubo un error");
        }
    }
}
