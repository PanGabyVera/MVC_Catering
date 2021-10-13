/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo_inventario;
import Modelo.inventario;
import Vista.Vista_Inventario;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.Holder;

/**
 *
 * @author Pandora
 */
public class Controlador_inventario {
    private Modelo_inventario modelo;
    private Vista_Inventario vista;

    public Controlador_inventario(Modelo_inventario modelo, Vista_Inventario vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setTitle("CRUD INVENTARIO");
        vista.setVisible(true);
        Cargarlistados("");
        vista.getAvi1().setVisible(false);
        vista.getAvi2().setVisible(false);
        vista.getAvi3().setVisible(false);
    }
    
     public void iniciaControl() {
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Cargarlistados(vista.getTxtbu().getText());
                vista.getTxtbu().setText(vista.getTxtbu().getText().toUpperCase());

            }
        };
        //Controlar los eventos de los botones
        vista.getBtnli().addActionListener(l -> Cargarlistados(""));
        vista.getBtncr().addActionListener(l -> MostarVentana(1));
        vista.getBtned().addActionListener(l -> MostarVentana(2));
        vista.getBtnel().addActionListener(l -> EliminarInv());
        vista.getBtnca().addActionListener(l -> vista.getDlginv().setVisible(false));
        vista.getjBttsalir().addActionListener(l -> vista.setVisible(false));

        //reporte y busqueda
        // vista.getBtnim().addActionListener(l->);
        vista.getTxtbu().addKeyListener(kl);
    }
    
     private void btcrear_editar(int origen) {
        if (origen == 1) {
            vista.getBtnac1().addActionListener(l -> GuardarInv());
        } else if (origen == 2) {
            vista.getBtnac1().addActionListener(l -> EditarInv());

        }
    }
    
    public void MostarVentana(int origen) {
        if (origen == 1) {
            btcrear_editar(origen);
            vista.getDlginv().setTitle("Crear Inventario");
            vista.getDlginv().setLocationRelativeTo(vista);
            vista.getDlginv().setSize(358, 370);
            vista.getDlginv().setVisible(true);

        } else if (origen == 2) {
            btcrear_editar(origen);
            vista.getDlginv().setTitle("Editar Inventario");
            vista.getDlginv().setLocationRelativeTo(vista);
            vista.getDlginv().setSize(358, 370);
            vista.getDlginv().setVisible(true);
            pasarDatos();
        } else {
            vista.getDlginv().setVisible(false);
        }

    }
    
    public void pasarDatos() {
        int cont = vista.getTblinv().getSelectedRow();
        if (cont != -1) {
            //llenamos datos en la ventana emergente
            Modelo_inventario mc = new Modelo_inventario();
            mc.setCod_inventario(vista.getTblinv().getValueAt(cont, 0).toString());
            vista.getTxtcoin().setText(vista.getTblinv().getValueAt(cont, 0).toString().toUpperCase());
            vista.getTxtnom().setText(vista.getTblinv().getValueAt(cont, 1).toString().toUpperCase());
            vista.getTxtpre().setText(vista.getTblinv().getValueAt(cont, 2).toString());
         
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }
    }
    
    private void Cargarlistados(String aguja) {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        DefaultTableModel tblModel; //Estructura JTbable

        tblModel = (DefaultTableModel) vista.getTblinv().getModel();
        tblModel.setNumRows(0);
        List<inventario> lista = modelo.Listainventario(aguja);
        int colum = tblModel.getColumnCount();//extraemos el# de colum
        Holder<Integer> a = new Holder<>(0);//contador
        //ORDENAR LOS DATOS DE LA TABLA
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        vista.getTblinv().setRowSorter(sorter);
        List<SortKey> sotk = new ArrayList<>();

        lista.stream().forEach(inve -> {

            tblModel.addRow(new Object[colum]);//para crear colum
            //ubcacion en la tabla
            vista.getTblinv().setValueAt(inve.getCod_inventario(), a.value, 0);
            vista.getTblinv().setValueAt(inve.getNombre(), a.value, 1);
            vista.getTblinv().setValueAt(inve.getPrecio(), a.value, 2);
           
            a.value++;

        });
    }
     
    private void GuardarInv() {

        modelo.setCod_inventario(vista.getTxtcoin().getText().toUpperCase());
        modelo.setNombre(vista.getTxtnom().getText().toUpperCase());
        Double pre=Double.parseDouble(vista.getTxtpre().getText());
        modelo.setPrecio(pre);
         
       // if (Validaciones() == false) {

            int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Guardar?", "Informacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.YES_NO_OPTION) {
                if (modelo.Crear()) {
                    JOptionPane.showMessageDialog(vista, "Se guardo correctamente..");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(vista, "Huvo un error al guardar");
                }
            }
      //  } else {
            JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos");
        //}

    }
    
    public void EliminarInv() {
        DefaultTableModel tblinv = (DefaultTableModel) vista.getTblinv().getModel();
        int fila = vista.getTblinv().getSelectedRow();
        if (fila != -1) {
            String idPersona = tblinv.getValueAt(fila, 1).toString();
            Modelo_inventario cli = new Modelo_inventario();
            int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Eliminar?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.YES_NO_OPTION) {
                if (cli.Eliminar(idPersona)) {
                    JOptionPane.showMessageDialog(vista, "Se elimino correctamente....");
                } else {
                    JOptionPane.showMessageDialog(vista, "Huvo un error el elimiar el registro");
                }
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila");
        }

    }
    
     public void EditarInv() {
        int cont = vista.getTblinv().getSelectedRow();
        Modelo_inventario inv = new Modelo_inventario();
        inv.setCod_inventario(vista.getTblinv().getValueAt(cont, 1).toString().toUpperCase());
        inv.setNombre(vista.getTxtnom().getText().toUpperCase());
        Double pre=Double.parseDouble(vista.getTxtpre().getText());
        inv.setPrecio(pre);
        
        int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Modificar los datos?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (inv.Editar(vista.getTxtcoin().getText())) {
                JOptionPane.showMessageDialog(vista, "Se modifico correctamente los datos");
                vista.getDlginv().setVisible(false);
                Cargarlistados("");
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error al editar los datos");
            }
        }

    }
    
     private void Limpiar() {
        vista.getTxtcoin().setText("");
        vista.getTxtnom().setText("");
        vista.getTxtpre().setText("");

    }
     
      private boolean Validaciones() {

        if (vista.getTxtcoin().getText().isEmpty()) {
            vista.getAvi1().setVisible(true);

            if (vista.getTxtnom().getText().trim().isEmpty()) {
                vista.getAvi2().setVisible(true);

                if (vista.getTxtpre().getText().trim().isEmpty()) {
                    vista.getAvi3().setVisible(true);
                    return true;
                }}}
        return false;
      }
}
