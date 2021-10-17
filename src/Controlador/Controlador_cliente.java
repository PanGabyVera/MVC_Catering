package Controlador;

import Modelo.Modelo_cliente;
import Modelo.cliente;
import Vista.Vista_Cliente;
import Vista.Vista_MenuPrincipal;
import com.sun.xml.internal.txw2.TXW;
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
public class Controlador_cliente {

    private Modelo_cliente modelo;
    private Vista_Cliente vista;

    public Controlador_cliente(Modelo_cliente modelo, Vista_Cliente vista) {
        this.modelo = modelo;
        this.vista = vista;
        //SOLAMENTE INICIALIZAR ELEMENTOS.
        vista.setTitle("CRUD CLIENTE");
        vista.setVisible(true);
        Cargarlistados("");

        vista.getAviso2().setVisible(false);
        vista.getAviso3().setVisible(false);
        vista.getAviso4().setVisible(false);
        vista.getAviso5().setVisible(false);
        vista.getAviso6().setVisible(false);
        vista.getAviso7().setVisible(false);
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
        vista.getBtnel().addActionListener(l -> EliminarCli());
        vista.getBtnca().addActionListener(l -> vista.getDlgcli().setVisible(false));
        vista.getjBttSalir().addActionListener(l -> vista.setVisible(false));

        //reporte y busqueda
        // vista.getBtnim().addActionListener(l->);
        vista.getTxtbu().addKeyListener(kl);
    }

    private void btcrear_editar(int origen) {
        if (origen == 1) {
            vista.getBtnac().addActionListener(l -> GuardarCli());
        } else if (origen == 2) {
            vista.getBtnac().addActionListener(l -> EditarCli());

        }
    }

    public void MostarVentana(int origen) {
        if (origen == 1) {
            btcrear_editar(origen);
            vista.getDlgcli().setTitle("Crear registro de cliente");
            vista.getDlgcli().setLocationRelativeTo(vista);
            vista.getDlgcli().setSize(358, 410);
            vista.getDlgcli().setVisible(true);

        } else if (origen == 2) {
            btcrear_editar(origen);
            vista.getDlgcli().setTitle("Editar cliente");
            vista.getDlgcli().setLocationRelativeTo(vista);
            vista.getDlgcli().setSize(358, 410);
            vista.getDlgcli().setVisible(true);
            pasarDatos();
        } else {
            vista.getDlgcli().setVisible(false);
        }

    }

    public void pasarDatos() {
        int cont = vista.getTblcli().getSelectedRow();
        if (cont != -1) {
            //llenamos datos en la ventana emergente
            Modelo_cliente mc = new Modelo_cliente();
            mc.setCi(vista.getTblcli().getValueAt(cont, 0).toString());
           // vista.getTxtcocli().setText(vista.getTblcli().getValueAt(cont, 0).toString().toUpperCase());
            vista.getTxtced().setText(vista.getTblcli().getValueAt(cont, 0).toString().toUpperCase());
            vista.getJtxtnombre().setText(vista.getTblcli().getValueAt(cont, 1).toString().toUpperCase());
            vista.getJtxtapellidos().setText(vista.getTblcli().getValueAt(cont, 2).toString().toUpperCase());
            vista.getJtxttelefono().setText(vista.getTblcli().getValueAt(cont, 3).toString().toUpperCase());
            vista.getJtxtcorreo().setText(vista.getTblcli().getValueAt(cont, 4).toString().toUpperCase());
            vista.getJtxtdirecion().setText(vista.getTblcli().getValueAt(cont, 5).toString().toUpperCase());

        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }
    }

    private void Cargarlistados(String aguja) {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        DefaultTableModel tblModel; //Estructura JTbable

        tblModel = (DefaultTableModel) vista.getTblcli().getModel();
        tblModel.setNumRows(0);
        List<cliente> lista = modelo.ListaClientes(aguja);
        int colum = tblModel.getColumnCount();//extraemos el# de colum
        Holder<Integer> a = new Holder<>(0);//contador
        //ORDENAR LOS DATOS DE LA TABLA
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        vista.getTblcli().setRowSorter(sorter);
        List<SortKey> sotk = new ArrayList<>();

        lista.stream().forEach(clie -> {

            tblModel.addRow(new Object[colum]);//para crear colum
            //ubcacion en la tabla
            //vista.getTblcli().setValueAt(clie.getCod(), a.value, 0);
            vista.getTblcli().setValueAt(clie.getCi(), a.value, 0);
            vista.getTblcli().setValueAt(clie.getNombres(), a.value, 1);
            vista.getTblcli().setValueAt(clie.getApellidos(), a.value, 2);
            vista.getTblcli().setValueAt(clie.getTelefono(), a.value, 3);
            vista.getTblcli().setValueAt(clie.getCorreo(), a.value, 4);
            vista.getTblcli().setValueAt(clie.getDireccion(), a.value, 5);

            a.value++;

        });
    }

    private void GuardarCli() {

       // modelo.setCod(vista.getTxtcocli().getText().toUpperCase());
        modelo.setCi(vista.getTxtced().getText());
        modelo.setNombres(vista.getJtxtnombre().getText().toUpperCase());
        modelo.setApellidos(vista.getJtxtapellidos().getText().toUpperCase());
        modelo.setTelefono(vista.getJtxttelefono().getText());
        modelo.setCorreo(vista.getJtxtcorreo().getText());
        modelo.setDireccion(vista.getJtxtdirecion().getText().toUpperCase());
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
       // } else {
         //   JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos");
        //}

    }

    public void EliminarCli() {
        DefaultTableModel tblPersonas = (DefaultTableModel) vista.getTblcli().getModel();
        int fila = vista.getTblcli().getSelectedRow();
        if (fila != -1) {
            String idPersona = tblPersonas.getValueAt(fila, 0).toString();
            Modelo_cliente cli = new Modelo_cliente();
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

    public void EditarCli() {
        int cont = vista.getTblcli().getSelectedRow();
        Modelo_cliente clie = new Modelo_cliente();
        clie.setCi(vista.getTblcli().getValueAt(cont, 0).toString().toUpperCase());
        clie.setNombres(vista.getJtxtnombre().getText().toUpperCase());
        clie.setApellidos(vista.getJtxtapellidos().getText().toUpperCase());
        clie.setTelefono(vista.getJtxttelefono().getText().toUpperCase());
        clie.setCorreo(vista.getJtxtcorreo().getText().toUpperCase());
        clie.setDireccion(vista.getJtxtdirecion().getText().toUpperCase());

        int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Modificar los datos?", "Información", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (clie.Editar(vista.getTxtced().getText())) {
                JOptionPane.showMessageDialog(vista, "Se modifico correctamente los datos");
                vista.getDlgcli().setVisible(false);
                Cargarlistados("");
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error al editar los datos");
            }
        }

    }

    private void Limpiar() {
        vista.getTxtced().setText("");
        vista.getJtxtnombre().setText("");
        vista.getJtxtapellidos().setText("");
        vista.getJtxttelefono().setText("");
        vista.getJtxtcorreo().setText("");
        vista.getJtxtdirecion().setText("");

    }

    private boolean Validaciones() {

            if (vista.getTxtced().getText().trim().isEmpty()) {
                vista.getAviso2().setVisible(true);

                if (vista.getJtxtnombre().getText().trim().isEmpty()) {
                    vista.getAviso3().setVisible(true);

                    if (vista.getJtxtapellidos().getText().trim().isEmpty()) {
                        vista.getAviso4().setVisible(true);

                        if (vista.getJtxttelefono().getText().trim().isEmpty()) {
                            vista.getAviso5().setVisible(true);

                            if (vista.getJtxtcorreo().getText().trim().isEmpty()) {
                                vista.getAviso6().setVisible(true);

                                if (vista.getJtxtdirecion().getText().trim().isEmpty()) {
                                    vista.getAviso7().setVisible(true);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        
        return false;
    }

    

}
