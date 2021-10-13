package Controlador;

import Modelo.Modelo_pedido;
import Modelo.pedido;
import Vista.Vista_Pedido;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Controlador_pedido {

    private Modelo_pedido modelo;
    private Vista_Pedido vista;

    public Controlador_pedido(Modelo_pedido modelo, Vista_Pedido vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setTitle("CRUD PEDIDO");
        vista.setVisible(true);
        Cargarlistados("");
        vista.getAvi1().setVisible(false);
        vista.getAvi2().setVisible(false);
        vista.getAvi3().setVisible(false);
        vista.getAvi4().setVisible(false);
        vista.getAvi5().setVisible(false);
        vista.getAvi6().setVisible(false);
        //combos
        modelo.llenar_comboCli(vista.getCBcliente());
        modelo.llenar_comboPaq(vista.getCBpaquete());

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
        vista.getBtnel().addActionListener(l -> EliminarPE());
        vista.getBtnca().addActionListener(l -> vista.getDlgped().setVisible(false));
        vista.getjBttsalir().addActionListener(l -> vista.setVisible(false));

        //reporte y busqueda
        // vista.getBtnim().addActionListener(l->);
        vista.getTxtbu().addKeyListener(kl);
    }

    private void btcrear_editar(int origen) {
        if (origen == 1) {
            vista.getBtnac().addActionListener(l -> GuardarPE());
        } else if (origen == 2) {
            vista.getBtnac().addActionListener(l -> EditarPE());

        }
    }

    public void MostarVentana(int origen) {
        if (origen == 1) {
            btcrear_editar(origen);
            vista.getDlgped().setTitle("Crear Pedido");
            vista.getDlgped().setLocationRelativeTo(vista);
            vista.getDlgped().setSize(580, 410);
            vista.getDlgped().setVisible(true);
            Limpiar();

        } else if (origen == 2) {
            btcrear_editar(origen);
            vista.getDlgped().setTitle("Editar Pedido");
            vista.getDlgped().setLocationRelativeTo(vista);
            vista.getDlgped().setSize(580, 410);
            vista.getDlgped().setVisible(true);
            pasarDatos();
        } else {
            vista.getDlgped().setVisible(false);
        }

    }

    public void pasarDatos() {
        int cont = vista.getTblped().getSelectedRow();
        if (cont != -1) {
            //llenamos datos en la ventana emergente
            Modelo_pedido mc = new Modelo_pedido();
            mc.setCod_pedido(vista.getTblped().getValueAt(cont, 0).toString());
            vista.getTxtcoped().setText(vista.getTblped().getValueAt(cont, 0).toString().toUpperCase());

            vista.getCBcliente().setSelectedItem(vista.getTblped().getValueAt(cont, 1).toString());
            vista.getCBpaquete().setSelectedItem(vista.getTblped().getValueAt(cont, 2).toString());

            String fecha = vista.getTblped().getValueAt(cont, 4).toString();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date date1 = new java.util.Date();
                date1 = df.parse(fecha);
                java.sql.Date date2 = new java.sql.Date(date1.getTime());

                vista.getJdcfech().setDate(date2);

            } catch (ParseException ex) {
                Logger.getLogger(Controlador_pedido.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxthor().setText(vista.getTblped().getValueAt(cont, 3).toString());
            vista.getTxtdir().setText(vista.getTblped().getValueAt(cont, 5).toString().toUpperCase());

        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }
    }

    private void Cargarlistados(String aguja) {

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();

        DefaultTableModel tblModel; //Estructura JTbable
        tblModel = (DefaultTableModel) vista.getTblped().getModel();
        tblModel.setNumRows(0);
        List<pedido> lista = modelo.ListaPedido(aguja);
        int colum = tblModel.getColumnCount();//extraemos el# de colum
        Holder<Integer> a = new Holder<>(0);//contador
        //ORDENAR LOS DATOS DE LA TABLA
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        vista.getTblped().setRowSorter(sorter);
        List<SortKey> sotk = new ArrayList<>();

        lista.stream().forEach(per -> {

            //mostramos fecha
            Date fecha = (Date) per.getFecha_enterega();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            String fch = formatoDelTexto.format(fecha);

            tblModel.addRow(new Object[colum]);//para crear colum
            //ubcacion en la tabla
            vista.getTblped().setValueAt(per.getCod_pedido(), a.value, 0);
            vista.getTblped().setValueAt(per.getCod_cliente(), a.value, 1);
            vista.getTblped().setValueAt(per.getCod_paquete(), a.value, 2);
            vista.getTblped().setValueAt(per.getHora_entrega(), a.value, 3);
            vista.getTblped().setValueAt(fch, a.value, 4);
            vista.getTblped().setValueAt(per.getDireccion_entre(), a.value, 5);

            a.value++;

        });

    }

    private void GuardarPE() {

        //para guardar la fecha en la base
        Instant instant = vista.getJdcfech().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());
        ///// 

        modelo.setCod_pedido(vista.getTxtcoped().getText());
        modelo.setCod_cliente(vista.getCBcliente().getSelectedItem().toString());
        modelo.setCod_paquete(vista.getCBpaquete().getSelectedItem().toString());

        //hora
        String s = vista.getTxthor().getText();
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long ms = 0;
        try {
            ms = sdf.parse(s).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Controlador_pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        Time t = new Time(ms);


        modelo.setHora_entrega(t);
        modelo.setFecha_enterega(fecha);
        modelo.setDireccion_entre(vista.getTxtcoped().getText());

        int resultado = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de Guardar?", "Si", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resultado == JOptionPane.YES_NO_OPTION) {
            if (modelo.Crear()) {
                JOptionPane.showMessageDialog(vista, "Se guardo correctamente..");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error al guardar");
            }
        }

    }

    public void EliminarPE() {
        DefaultTableModel tblPersonas = (DefaultTableModel) vista.getTblped().getModel();
        int fila = vista.getTblped().getSelectedRow();
        if (fila != -1) {
            String idPersona = tblPersonas.getValueAt(fila, 0).toString();

            Modelo_pedido pe = new Modelo_pedido();
            if (pe.Eliminar(idPersona)) {
                JOptionPane.showMessageDialog(vista, "Se elimino correctamente el registro");
            } else {
                JOptionPane.showMessageDialog(vista, "Huvo un error el elimiar el registro");
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila");
        }

    }

    public void EditarPE() {

        int cont = vista.getTblped().getSelectedRow();
        String id = vista.getTblped().getValueAt(cont, 0).toString();
        //fecha
        Instant instant = vista.getJdcfech().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());

        //hora
        String s = vista.getTxthor().getText();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long ms = 0;
        try {
            ms = sdf.parse(s).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Controlador_pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        Time t = new Time(ms);

        Modelo_pedido per = new Modelo_pedido();
        per.setCod_pedido(id);
        per.setCod_cliente(vista.getCBcliente().getSelectedItem().toString());
        per.setCod_paquete(vista.getCBpaquete().getSelectedItem().toString());
        per.setHora_entrega(t);
        per.setFecha_enterega(fecha);
        per.setDireccion_entre(vista.getTxtdir().getText());

        if (per.Editar(vista.getTxtcoped().getText())) {
            JOptionPane.showMessageDialog(vista, "Se modifico correctamente los datos");
            vista.getDlgped().setVisible(false);
            Cargarlistados("");
        } else {
            JOptionPane.showMessageDialog(vista, "Huvo un error al editar los datos");
        }

    }

    public void Limpiar() {
        vista.getTxtcoped().setText("");
        vista.getTxthor().setText("");
        vista.getTxtdir().setText("");
        vista.getJdcfech().setCalendar(null);
        vista.getCBcliente().setSelectedIndex(0);
        vista.getCBcliente().setSelectedIndex(0);
    }

}
