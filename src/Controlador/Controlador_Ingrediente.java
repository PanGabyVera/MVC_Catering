
package Controlador;

import Modelo.Ingredientes;
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
    Vista.getBtnac1().addActionListener(l->Guardarpersona());
    Vista.getBtnac2().addActionListener(l->EditarPersona());
    Vista.getBtnel().addActionListener(l-> EliminarPersona());
    Vista.getBtnim().addActionListener(l-> ImprimirRegistro());
    Vista.getBtnca().addActionListener(l->Vista.getDlging().setVisible(false));
    
    //Controlador Buscar
    Vista.getTxtbu().addKeyListener(kl);
    
    
    }
    private void  ImprimirRegistro(){
        ConectarBD con =new ConectarBD();
        try {
        JasperReport jr= (JasperReport)JRLoader.loadObject(getClass().getResource("/mvc/vista/reportes/ListaPersonas.jasper"));
        Map<String,Object> parametros= new HashMap<String, Object>();
        String aguja = vista.getTxtbu().getText();
        parametros.put("paguja" , "%"+aguja+"%");
        JasperPrint jp= JasperFillManager.fillReport(jr, parametros,con.getCon());
        JasperViewer jv= new JasperViewer(jp);
        jv.setVisible(true);    
        } catch (JRException ex) {
            Logger.getLogger(ControlPersona.class.getName()).log(Level.SEVERE,null,ex);
        }      
    } 
    private void cargarDialogo(int origen){
        vista.getDlgper().setSize(600,350);
        vista.getDlgper().setLocationRelativeTo(vista);
        vista.getTxtid().setText("");
        vista.getTxtnom().setText("");
        vista.getTxtape().setText("");
        vista.getDtcfe().setCalendar(null);
        vista.getTxtte().setText("");
        vista.getTxtse().setText("");
        vista.getTxtsu().setText("");
        vista.getTxtcu().setText("");
        vista.getLblfo().setIcon(null);
        if(origen==11){
            vista.getDlgper().setTitle("Crear Persona");
            vista.getBtnac1().setVisible(false);
            vista.getBtnac().setVisible(true);
        }
            if(origen==22){
            vista.getDlgper().setTitle("Editar Persona");
            int cont = vista.getTblper().getSelectedRow();
            if (cont != -1) {
            String idPersona = vista.getTblper().getValueAt(cont, 0).toString();
                String nombres = vista.getTblper().getValueAt(cont, 1).toString();
                String apellidos = vista.getTblper().getValueAt(cont, 2).toString();
                
                String telefono = vista.getTblper().getValueAt(cont, 4).toString();
                String sexo = vista.getTblper().getValueAt(cont, 5).toString();
                String sueldo = vista.getTblper().getValueAt(cont, 6).toString();
                String cupo = vista.getTblper().getValueAt(cont, 7).toString();
                ImageIcon imagen = null;
                if (vista.getTblper().getValueAt(cont, 8) != null) {
                    imagen = (ImageIcon) ((javax.swing.JLabel) vista.getTblper().getValueAt(cont, 8)).getIcon();
                }
                 ModeloPersona p1 = new ModeloPersona();
                p1.setIdpersona(idPersona);
                vista.getTxtid().setText(idPersona);
                vista.getTxtnom().setText(nombres);
                vista.getTxtape().setText(apellidos);
                vista.getTxtte().setText(telefono);
                vista.getTxtse().setText(sexo);
                vista.getDtcfe().setDate(new java.util.Date(p1.traerFecha().getTime()));
                //vista.getDtcfe().setText(fechanacimiento);
                vista.getTxtsu().setText(sueldo);
                vista.getTxtcu().setText(cupo);
                if (imagen != null) {
                    java.awt.Image i = imagen.getImage();
                    ImageIcon ime = new ImageIcon(i.getScaledInstance(vista.getLblfo().getWidth(), vista.getLblfo().getHeight(), java.awt.Image.SCALE_DEFAULT));
                    vista.getLblfo().setIcon(ime);
                } else {

                }
               
                vista.getLblfo().updateUI();
                vista.getBtnac().setVisible(false);
                vista.getBtnac1().setVisible(true);
        }
        
        }
        vista.getDlgper().setVisible(true);
    }
    
//    private void cargaLista(){
//    //Acciones necesarios para extraer los datos MODELO Y Mostrar en la Vista
//        DefaultTableModel tablaMd;
//        tablaMd=(DefaultTableModel)vista.getTblper().getModel();
//        tablaMd.setNumRows(0);
//        List<Persona> lista=modelo.listaPersonas("");
//        lista.stream().forEach(per->{
//         String[] fila={per.getIdpersona(),per.getNombres(),per.getApellidos(),String.valueOf(per.getEdad()),per.getTelefono(),per.getSexo(),per.getSueldo().toString(),String.valueOf(per.getCupo()),};
//         tablaMd.addRow(fila);
//        });
//        
//    }
    private void cargaListados(String aguja){
    
        vista.getTblper().setDefaultRenderer(Object.class, new ImagenTabla());
        vista.getTblper().setRowHeight(100);
        DefaultTableCellRenderer renderer= new DefaultTableCellHeaderRenderer();
        DefaultTableModel tblModel; 
        tblModel=(DefaultTableModel)vista.getTblper().getModel();
        tblModel.setNumRows(0);
        List<Persona> lista=modelo.listaPersonas(aguja);
        int ncols=tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lista.stream().forEach(per->{
        tblModel.addRow(new Object[ncols]);
           vista.getTblper().setValueAt(per.getIdpersona() , i.value , 0);
            vista.getTblper().setValueAt(per.getNombres(), i.value , 1);
            vista.getTblper().setValueAt(per.getApellidos(), i.value, 2);
            vista.getTblper().setValueAt( String.valueOf(per.getEdad()), i.value, 3);
            vista.getTblper().setValueAt(per.getTelefono(), i.value, 4);
            vista.getTblper().setValueAt(per.getSexo(), i.value, 5);
            vista.getTblper().setValueAt(per.getSueldo(), i.value, 6);
            vista.getTblper().setValueAt(per.getCupo(), i.value, 7);
        java.awt.Image img = per.getFoto();
           
           if(img!=null){
                Image newimg = img.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newimg);
                renderer.setIcon(icon);
                vista.getTblper().setValueAt(new JLabel(icon), i.value, 8);
           }else{
               vista.getTblper().setValueAt(null, i.value, 8);
           }
           i.value++;
          
        });

        
    }
    private void examinaFoto(){
        JFileChooser jfc= new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado=jfc.showOpenDialog(null);
        if(estado==JFileChooser.APPROVE_OPTION){
            try {
                Image miImagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
                        vista.getLblfo().getWidth(),
                        vista.getLblfo().getHeight(),
                        Image.SCALE_DEFAULT);
                Icon icon=new ImageIcon(miImagen);
                vista.getLblfo().setIcon(icon);
                vista.getLblfo().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ControlPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void Guardarpersona(){
      String idpersona = vista.getTxtid().getText();
      String nombre = vista.getTxtnom().getText();
      String apellido = vista.getTxtape().getText();
      
      Instant instant= vista.getDtcfe().getDate().toInstant();
      ZoneId zid= ZoneId.of("America/Guayaquil");
      ZonedDateTime zdt=ZonedDateTime.ofInstant(instant, zid);  
      Date fecha = Date.valueOf(zdt.toLocalDate());
      
      String telefono = vista.getTxtte().getText();
      String sexo = vista.getTxtse().getText();
      double sueldo = Double.parseDouble(vista.getTxtsu().getText());
      int cupo = Integer.parseInt(vista.getTxtcu().getText());
      
      
      
      
      ModeloPersona persona = new ModeloPersona();
      persona.setIdpersona(idpersona);
      persona.setNombres(nombre);
      persona.setApellidos(apellido);
      persona.setFechanacimiento(fecha);
      persona.setTelefono(telefono);
      persona.setSexo(sexo);
      persona.setSueldo(sueldo);
      persona.setCupo(cupo);
      
      ImageIcon ic= (ImageIcon)vista.getLblfo().getIcon();
        persona.setFoto(ic.getImage());
       
        if (persona.grabar()) {
            JOptionPane.showMessageDialog(vista, "No se logro guardar");
        } else {
            cargaListados("");
            vista.getDlgper().setVisible(false);
            JOptionPane.showMessageDialog(vista, "Se guardo la persona");
        }
    }
    public void EliminarPersona(){
        int ind=vista.getTblper().getSelectedRow();
        
            String idpersona = vista.getTblper().getValueAt(ind, 0).toString();
                ModeloPersona persona = new ModeloPersona();
                persona.setIdpersona(idpersona);
                if(persona.Eliminar()){
                    JOptionPane.showMessageDialog(vista, "No se pudo eliminar");  
                }else{
                   cargaListados("");
                   JOptionPane.showMessageDialog(vista, "Se elimino la persona");
                }
        
    }
    public void EditarPersona() {
        int cont = vista.getTblper().getSelectedRow();
        String idpersona = vista.getTblper().getValueAt(cont, 0).toString();
        String nombres = vista.getTxtnom().getText();
        String apellidos = vista.getTxtape().getText();
        
        Instant instant= vista.getDtcfe().getDate().toInstant();
        ZoneId zid= ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt=ZonedDateTime.ofInstant(instant, zid);  
        Date fechanacimiento = Date.valueOf(zdt.toLocalDate());
      
        String telefono = vista.getTxtte().getText();
        String sexo = vista.getTxtse().getText();
        double sueldo = Double.parseDouble(vista.getTxtsu().getText());
        int cupo = Integer.parseInt(vista.getTxtcu().getText());
        ModeloPersona persona = new ModeloPersona();
        persona.setIdpersona(idpersona);
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);
        persona.setFechanacimiento(fechanacimiento);
        persona.setTelefono(telefono);
        persona.setSexo(sexo);
        persona.setSueldo(sueldo);
        persona.setCupo(cupo);
        ImageIcon imagen = (ImageIcon) vista.getLblfo().getIcon();
        persona.setFoto(imagen.getImage());
        if (persona.Editar() == true) {
            JOptionPane.showMessageDialog(vista, "Hubo un error");
        } else {
            cargaListados("");
            vista.getDlgper().setVisible(false);
            JOptionPane.showMessageDialog(vista, "Registro actualizado");
        }
    }
}
