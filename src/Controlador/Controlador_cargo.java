/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo_cargo;
import Modelo.cargo;
import Vista.Vista_Cargo;
import Vista.Vista_Empleado;
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
public class Controlador_cargo {
    
    private Modelo_cargo modelo;
    private Vista_Cargo vista;
    private Vista_Empleado vis;

    public Controlador_cargo(Modelo_cargo modelo, Vista_Cargo vista) {
        this.modelo = modelo;
        this.vista = vista;
         vista.setTitle("CRUDCARGO");
        vista.setVisible(true);
        Cargarlistados("");
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
        vista.getBtnel().addActionListener(l -> EliminarCAR());
        vista.getBtnca().addActionListener(l -> vista.getDlgcar().setVisible(false));
        vista.getBttsalir().addActionListener(l -> vista.setVisible(false));

        //reporte y busqueda
        // vista.getBtnim().addActionListener(l->);
        vista.getTxtbu().addKeyListener(kl);
    }
     
     private void btcrear_editar(int origen) {
        if (origen == 1) {
            vista.getBtnac1().addActionListener(l -> GuardarCAR());
        } else if (origen == 2) {
            vista.getBtnac1().addActionListener(l -> EditarCAR());

        }
    }
    
    public void MostarVentana(int origen) {
        if (origen == 1) {
            btcrear_editar(origen);
            vista.getDlgcar().setTitle("Crear Cargo");
            vista.getDlgcar().setLocationRelativeTo(vista);
            vista.getDlgcar().setSize(450, 350);
            vista.getDlgcar().setVisible(true);

        } else if (origen == 2) {
            btcrear_editar(origen);
            vista.getDlgcar().setTitle("Editar Cargo");
            vista.getDlgcar().setLocationRelativeTo(vista);
            vista.getDlgcar().setSize(450, 350);
            vista.getDlgcar().setVisible(true);
            pasarDatos();
        } else {
            vista.getDlgcar().setVisible(false);
        }

    }
    
    public void pasarDatos() {
        int cont = vista.getTblcar().getSelectedRow();
        if (cont != -1) {
            //llenamos datos en la ventana emergente
            Modelo_cargo mc = new Modelo_cargo();
            mc.setCod_car(vista.getTblcar().getValueAt(cont, 0).toString());
            vista.getTxtcoca().setText(vista.getTblcar().getValueAt(cont, 0).toString().toUpperCase());
            vista.getTxtnomca().setText(vista.getTblcar().getValueAt(cont, 1).toString().toUpperCase());
            vista.getTxtsue().setText(vista.getTblcar().getValueAt(cont, 2).toString());
         
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }
    }
    
    public void Cargarlistados(String aguja) {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        DefaultTableModel tblModel; //Estructura JTbable

        tblModel = (DefaultTableModel) vista.getTblcar().getModel();
        tblModel.setNumRows(0);
        List<cargo> lista = modelo.Listacargo(aguja);
        int colum = tblModel.getColumnCount();//extraemos el# de colum
        Holder<Integer> a = new Holder<>(0);//contador
        //ORDENAR LOS DATOS DE LA TABLA
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        vista.getTblcar().setRowSorter(sorter);
        List<SortKey> sotk = new ArrayList<>();

        lista.stream().forEach(inve -> {

            tblModel.addRow(new Object[colum]);//para crear colum
            //ubcacion en la tabla
            vista.getTblcar().setValueAt(inve.getCod_car(), a.value, 0);
            vista.getTblcar().setValueAt(inve.getNom(), a.value, 1);
            vista.getTblcar().setValueAt(inve.getSueldo(), a.value, 2);
           
            a.value++;

        });
    }
    
    private void GuardarCAR() {

        modelo.setCod_car(vista.getTxtcoca().getText().toUpperCase());
        modelo.setNom(vista.getTxtnomca().getText().toUpperCase());
        int pre=Integer.valueOf(vista.getTxtsue().getText());
        modelo.setSueldo(pre);
         
            int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Guardar?", "Informacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.YES_NO_OPTION) {
                if (modelo.Crear()) {
                    JOptionPane.showMessageDialog(vista, "Se guardo correctamente..");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(vista, "Huvo un error al guardar");
                }
            }}
    

 public void EliminarCAR() {
        DefaultTableModel tblinv = (DefaultTableModel) vista.getTblcar().getModel();
        int fila = vista.getTblcar().getSelectedRow();
        if (fila != -1) {
            String id = tblinv.getValueAt(fila, 0).toString();
            Modelo_cargo car = new Modelo_cargo();
            int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Eliminar?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.YES_NO_OPTION) {
                if (car.Eliminar(id)) {
                    JOptionPane.showMessageDialog(vista, "Se elimino correctamente....");
                } else {
                    JOptionPane.showMessageDialog(vista, "Huvo un error el elimiar el registro");
                }
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila");
        }

    }
    
 public void EditarCAR() {
        int cont = vista.getTblcar().getSelectedRow();
        Modelo_cargo inv = new Modelo_cargo();
        inv.setCod_car(vista.getTblcar().getValueAt(cont, 0).toString().toUpperCase());
        inv.setNom(vista.getTxtnomca().getText().toUpperCase());
        int pre=Integer.valueOf(vista.getTxtsue().getText());
        inv.setSueldo(pre);
        
        int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Modificar los datos?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (inv.Editar(vista.getTxtcoca().getText())) {
                JOptionPane.showMessageDialog(vista, "Se modifico correctamente los datos");
                vista.getDlgcar().setVisible(false);
                Cargarlistados("");
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error al editar los datos");
            }
        }

    }
  private void Limpiar() {
        vista.getTxtcoca().setText("");
        vista.getTxtnomca().setText("");
        vista.getTxtsue().setText("");

    }
}
