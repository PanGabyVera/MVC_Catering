/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Pandora
 */
public class Modelo_pedido extends pedido{
    
    Modelo_ConexionBD conexion = new Modelo_ConexionBD();

    public Modelo_pedido() {
    }

    public Modelo_pedido(String cod_pedido, String cod_cliente, String cod_paquete, Time hora_entrega, java.sql.Date Fecha_enterega, String direccion_entre) {
        super(cod_pedido, cod_cliente, cod_paquete, hora_entrega, Fecha_enterega, direccion_entre);
    }

    
    
     public List<pedido> ListaPedido(String aguja) {
        String sql = "select * from pedido where";
        sql += " UPPER(cod_pedido) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(cod_cliente) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(cod_paquete) like UPPER('%" + aguja + "%') ";
        ResultSet result = conexion.Consulta(sql);
        List<pedido> lista = new ArrayList<>();
        try {
            while (result.next()) {
                pedido p=new pedido();
                p.setCod_pedido(result.getString("cod_pedido"));
                p.setCod_cliente(result.getString("cod_cliente"));
                p.setCod_paquete(result.getString("cod_paquete"));
                p.setFecha_enterega(result.getDate("fecha_entre"));
                p.setHora_entrega(result.getTime("hora_entre"));
                p.setDireccion_entre(result.getString("direccion_pe"));
                lista.add(p);
            }
            result.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_pedido.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

  public boolean Crear() {
       String sql;
        sql = "INSERT INTO pedido(cod_pedido,cod_cliente,cod_paquete,fecha_entre,hora_entre,direccion_pe) ";
        sql += " VALUES ('" + getCod_pedido() + "','" + getCod_cliente() + "','" + getCod_paquete()
                +"',to_date('" + getFecha_enterega() + "','yyyy-MM-dd') ,'" +getHora_entrega()+"','"+getDireccion_entre()+"','" +"')";
         
      return conexion.accion(sql);
    }
  
public boolean Eliminar(String id) {
        String sql;
        sql = "DELETE FROM pedido where cod_pedido='" + id + "'";
        return conexion.accion(sql);

    }
    
    public boolean Editar(String id) {
        String sql = "UPDATE public.pedido "
                + "SET cod_pedido='" + getCod_pedido() + "', cod_cliente='" + getCod_cliente()
                + "',cod_paquete='" + getCod_paquete()+"',fecha_entre= to_date('"+getFecha_enterega()
                +"','yyyy-MM-dd') ,hora_entre='"+getHora_entrega() +"',direccion_pe='" + getDireccion_entre()+"'"
                + " WHERE cod_pedido = '" + id + "'";
        return conexion.accion(sql);
    }
    public void llenar_comboCli(JComboBox combo){
    
        String sql="SELECT id FROM cliente ORDER BY id ASC";
        
        java.sql.Connection con=null; 
            con=conexion.getCon();
        try {
            //cliente
            PreparedStatement ps= con.prepareStatement(sql);//preparar sentencia
            ResultSet result=ps.executeQuery();//ejecuta y guarda la sentencia
            
            while (result.next()) {        
                combo.addItem(result.getString("id"));//se llena
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
        
            if (con!=null) {
                System.err.println("Se subieron los clientes correctamente");
            } else {
                System.err.println("Huvo un error al subir los datos");
            }
        }
        
        
    }
    
    public void llenar_comboPaq(JComboBox combo){
    
        String sql="SELECT cod_paq FROM paquete ORDER BY cod_paq ASC";
        
        java.sql.Connection con=null; 
            con=conexion.getCon();
        try {
            //cliente
            PreparedStatement ps= con.prepareStatement(sql);//preparar sentencia
            ResultSet result=ps.executeQuery();//ejecuta y guarda la sentencia
            
            while (result.next()) {        
                combo.addItem(result.getString("cod_paq"));//se llena
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
        
            if (con!=null) {
                System.err.println("Se subieron los paquetes correctamente");
            } else {
                System.err.println("Huvo un error al subir los datos");
            }
        }
    }
    
}
