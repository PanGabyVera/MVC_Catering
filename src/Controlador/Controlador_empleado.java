/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo_cargo;
import Modelo.Modelo_empleado;
import Modelo.cargo;
import Modelo.empleado;
import Vista.Vista_Cargo;
import Vista.Vista_Empleado;
import Vista.Vista_MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.ws.Holder;

/**
 *
 * @author Pandora
 */
public class Controlador_empleado {

    private Modelo_empleado modelo;
    private Vista_Empleado vista;
    private Modelo_cargo mo_crago;

    public Controlador_empleado(Modelo_empleado modelo, Vista_Empleado vista, Modelo_cargo cargo) {
        this.modelo = modelo;
        this.vista = vista;
        this.mo_crago = cargo;
        vista.setTitle("CRUD EMPLEADO");
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
        vista.getBtnel().addActionListener(l -> EliminarEM());
        vista.getBtnca().addActionListener(l -> vista.getDlgemp().setVisible(false));
        vista.getjBttsalir().addActionListener(l -> vista.setVisible(false));
        //dialogo del cargo
        vista.getBttBuscar().addActionListener(l -> MostarVentana(3));
        vista.getBttagregar().addActionListener(l -> pasarCargo());
        vista.getBttatras().addActionListener(l -> vista.getDlgCargo().setVisible(false));
        //reporte y busqueda
        // vista.getBtnim().addActionListener(l->);
        vista.getTxtbu().addKeyListener(kl);
    }

    private void btcrear_editar(int origen) {
        if (origen == 1) {
            vista.getBtnac().addActionListener(l -> GuardarEM());
        } else if (origen == 2) {
            vista.getBtnac().addActionListener(l -> EditarEM());

        }
    }

    public void MostarVentana(int origen) {
        if (origen == 1) {
            btcrear_editar(origen);
            vista.getDlgemp().setTitle("Crear registro de empleado");
            vista.getDlgemp().setLocationRelativeTo(vista);
            vista.getDlgemp().setSize(580, 450);
            vista.getDlgemp().setVisible(true);

        } else if (origen == 2) {
            btcrear_editar(origen);
            vista.getDlgemp().setTitle("Editar empleado");
            vista.getDlgemp().setLocationRelativeTo(vista);
            vista.getDlgemp().setSize(580, 480);
            vista.getDlgemp().setVisible(true);
            pasarDatos();
        } else if (origen == 3) {
            vista.getDlgCargo().setTitle("Selecciòn de cargo");
            CargarCargo("");
            vista.getDlgCargo().setLocationRelativeTo(vista);
            vista.getDlgCargo().setSize(450, 350);
            vista.getDlgCargo().setVisible(true);

        } else {
            vista.getDlgemp().setVisible(false);
        }
    }

    public void pasarDatos() {
        int cont = vista.getTblemp().getSelectedRow();
        if (cont != -1) {
            //llenamos datos en la ventana emergente
            Modelo_empleado mc = new Modelo_empleado();
            mc.setCi(vista.getTblemp().getValueAt(cont, 1).toString());
            vista.getTxtcoem().setText(vista.getTblemp().getValueAt(cont, 0).toString().toUpperCase());
            vista.getTxtced().setText(vista.getTblemp().getValueAt(cont, 1).toString().toUpperCase());
            vista.getJtxtnombre().setText(vista.getTblemp().getValueAt(cont, 2).toString().toUpperCase());
            vista.getJtxtapellidos().setText(vista.getTblemp().getValueAt(cont, 3).toString().toUpperCase());
            vista.getJtxttelefono().setText(vista.getTblemp().getValueAt(cont, 4).toString().toUpperCase());
            vista.getJtxtcorreo().setText(vista.getTblemp().getValueAt(cont, 5).toString().toUpperCase());
            vista.getJtxtdirecion().setText(vista.getTblemp().getValueAt(cont, 6).toString().toUpperCase());
            vista.getTxtcargo().setText(vista.getTblemp().getValueAt(cont, 7).toString().toUpperCase());
            vista.getLbNombreca1().setText(vista.getTblemp().getValueAt(cont, 8).toString().toUpperCase());
            vista.getLbsueldoca().setText(vista.getTblemp().getValueAt(cont, 9).toString());

        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }
    }

    public void pasarCargo() {
        int cont = vista.getTblcargos().getSelectedRow();
        if (cont != -1) {
            //llenamos datos en la ventana emergente
            Modelo_cargo mc = new Modelo_cargo();
            mc.setCod_car(vista.getTblcargos().getValueAt(cont, 0).toString());
            vista.getTxtcargo().setText(vista.getTblcargos().getValueAt(cont, 0).toString().toUpperCase());
            vista.getLbNombreca1().setText(vista.getTblcargos().getValueAt(cont, 1).toString().toUpperCase());
            vista.getLbsueldoca().setText(vista.getTblcargos().getValueAt(cont, 2).toString().toUpperCase());
            vista.getDlgCargo().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }
    }

    public void CargarCargo(String aguja) {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        DefaultTableModel tblModel; //Estructura JTbable

        tblModel = (DefaultTableModel) vista.getTblcargos().getModel();
        tblModel.setNumRows(0);
        List<cargo> lista = mo_crago.Listacargo(aguja);
        int colum = tblModel.getColumnCount();//extraemos el# de colum
        Holder<Integer> a = new Holder<>(0);//contador
        //ORDENAR LOS DATOS DE LA TABLA
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        vista.getTblcargos().setRowSorter(sorter);
        List<SortKey> sotk = new ArrayList<>();

        lista.stream().forEach(inve -> {

            tblModel.addRow(new Object[colum]);//para crear colum
            //ubcacion en la tabla
            vista.getTblcargos().setValueAt(inve.getCod_car(), a.value, 0);
            vista.getTblcargos().setValueAt(inve.getNom(), a.value, 1);
            vista.getTblcargos().setValueAt(inve.getSueldo(), a.value, 2);

            a.value++;

        });

    }

    public void Cargarlistados(String aguja) {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();

        DefaultTableModel tblModel; //Estructura JTbable
        tblModel = (DefaultTableModel) vista.getTblemp().getModel();
        tblModel.setNumRows(0);
        List<empleado> lista = modelo.ListaEmpleados(aguja);
       // List<cargo> lis = mo_crago.Listacargo(aguja);
        int colum = tblModel.getColumnCount();//extraemos el# de colum
        Holder<Integer> a = new Holder<>(0);//contador
        //ORDENAR LOS DATOS DE LA TABLA
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        vista.getTblemp().setRowSorter(sorter);
        List<SortKey> sotk = new ArrayList<>();
        lista.stream().forEach(emp -> {

            tblModel.addRow(new Object[colum]);//para crear colum
            //ubcacion en la tabla

            vista.getTblemp().setValueAt(emp.getCod(), a.value, 0);
            vista.getTblemp().setValueAt(emp.getCi(), a.value, 1);
            vista.getTblemp().setValueAt(emp.getNombres(), a.value, 2);
            vista.getTblemp().setValueAt(emp.getApellidos(), a.value, 3);
            vista.getTblemp().setValueAt(emp.getTelefono(), a.value, 4);
            vista.getTblemp().setValueAt(emp.getCorreo(), a.value, 5);
            vista.getTblemp().setValueAt(emp.getDireccion(), a.value, 6);
            vista.getTblemp().setValueAt(emp.getCargo(), a.value, 7);
            vista.getTblemp().setValueAt(emp.getNom_car(), a.value, 8);
            int su=Integer.valueOf(emp.getSuel());
            vista.getTblemp().setValueAt(su, a.value, 9);
            
            a.value++;
        });

    }

    private void GuardarEM() {

        modelo.setCod(vista.getTxtcoem().getText().toUpperCase());
        modelo.setCi(vista.getTxtced().getText());
        modelo.setNombres(vista.getJtxtnombre().getText().toUpperCase());
        modelo.setApellidos(vista.getJtxtapellidos().getText().toUpperCase());
        modelo.setTelefono(vista.getJtxttelefono().getText());
        modelo.setCorreo(vista.getJtxtcorreo().getText());
        modelo.setDireccion(vista.getJtxtdirecion().getText().toUpperCase());
        modelo.setCargo(vista.getTxtcargo().getText().toUpperCase());
        modelo.setNom_car(vista.getLbNombreca1().getText().toUpperCase());
        int su = Integer.valueOf(vista.getLbsueldoca().getText());
        modelo.setSuel(su);

        int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Guardar?", "Informacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (modelo.Crear()) {
                JOptionPane.showMessageDialog(vista, "Se guardo correctamente..");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error al guardar");
            }
        }
    }

    public void EliminarEM() {
        DefaultTableModel tblPersonas = (DefaultTableModel) vista.getTblemp().getModel();
        int fila = vista.getTblemp().getSelectedRow();
        if (fila != -1) {
            String id = tblPersonas.getValueAt(fila, 1).toString();
            Modelo_empleado em = new Modelo_empleado();
            int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Eliminar?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.YES_NO_OPTION) {
                if (em.Eliminar(id)) {
                    JOptionPane.showMessageDialog(vista, "Se elimino correctamente....");
                } else {
                    JOptionPane.showMessageDialog(vista, "Huvo un error el elimiar el registro");
                }
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila");
        }

    }

    public void EditarEM() {
        int cont = vista.getTblemp().getSelectedRow();
        Modelo_empleado e = new Modelo_empleado();
        e.setCi(vista.getTblemp().getValueAt(cont, 0).toString().toUpperCase());
        e.setNombres(vista.getJtxtnombre().getText().toUpperCase());
        e.setApellidos(vista.getJtxtapellidos().getText().toUpperCase());
        e.setTelefono(vista.getJtxttelefono().getText().toUpperCase());
        e.setCorreo(vista.getJtxtcorreo().getText().toUpperCase());
        e.setDireccion(vista.getJtxtdirecion().getText().toUpperCase());
        e.setCod(vista.getTxtcoem().getText().toUpperCase());
        e.setCargo(vista.getTxtcargo().getText().toUpperCase());/*
        e.setNom_car(vista.getLbNombreca1().getText().toUpperCase());
        int a =Integer.valueOf(vista.getLbsueldoca().getText().toUpperCase());
        e.setSuel(a);*/

        int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Modificar los datos?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (e.Editar(vista.getTxtced().getText())) {
                JOptionPane.showMessageDialog(vista, "Se modifico correctamente los datos");
                vista.getDlgemp().setVisible(false);
                Cargarlistados("");
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error al editar los datos");
            }
        }

    }

    private void Limpiar() {
        vista.getTxtcoem().setText("");
        vista.getTxtced().setText("");
        vista.getJtxtnombre().setText("");
        vista.getJtxtapellidos().setText("");
        vista.getJtxttelefono().setText("");
        vista.getJtxtcorreo().setText("");
        vista.getJtxtdirecion().setText("");
        vista.getTxtcargo().setText("");

    }
}
