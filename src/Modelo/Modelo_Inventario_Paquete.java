
package Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.postgresql.util.Base64;


public class Modelo_Inventario_Paquete extends Inventario_paquete{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Inventario_Paquete(){
        
    }
    public Modelo_Inventario_Paquete(String cod_paq, String cod_inv, int cantidad, String nombre) {
        super(cod_paq,cod_inv,cantidad,nombre);
    }
    
    
    public List<Inventario_paquete> listaInventario_Paquete(String aguja){
    
        try {
            String sql="select * from inv_paque join inventario USING(cod_inv) WHERE ";
            sql+=" UPPER(cod_paq) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(cod_inv) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Inventario_paquete> lp= new ArrayList<Inventario_paquete>();
            while(rs.next()){
                Inventario_paquete invent_paq= new Inventario_paquete();
                invent_paq.setCod_paq(rs.getString("cod_paq"));
                invent_paq.setCod_inv(rs.getString("cod_inv"));
                invent_paq.setCantidad(rs.getInt("cantidad")); 
                invent_paq.setNombre(rs.getString("nom_inv"));
                lp.add(invent_paq);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Inventario_Paquete.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Inventario_paquete> listaInventario_PaqueteBus(String aguja){
    
        try {
            String sql="select * from inv_paque join inventario USING(cod_inv) WHERE ";
            sql+=" UPPER(cod_paq) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Inventario_paquete> lp= new ArrayList<Inventario_paquete>();
            while(rs.next()){
                Inventario_paquete invent_paq= new Inventario_paquete();
                invent_paq.setCod_paq(rs.getString("cod_paq"));
                invent_paq.setCod_inv(rs.getString("cod_inv"));
                invent_paq.setCantidad(rs.getInt("cantidad")); 
                invent_paq.setNombre(rs.getString("nom_inv"));
                lp.add(invent_paq);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Inventario_Paquete.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean grabarInv_paq(){
        
        String sql;
        sql="INSERT INTO inv_paque(cod_paq,cod_inv,cantidad) ";
        sql+=" VALUES ('"+getCod_paq()+"','"+getCod_inv()+"','"+getCantidad()+"')";
        return con.accion(sql);
    }
    
    public boolean EliminarInv_paq(){
        
        String sql;
        sql="DELETE FROM inv_paque where cod_paq='"+getCod_paq()+"' and cod_inv='"+getCod_inv()+"'";
        return con.accion(sql);   
    }
    public boolean EliminarInv_paquete(){
        
        String sql;
        sql="DELETE FROM inv_paque where cod_paq='"+getCod_paq()+"'";
        return con.accion(sql);   
    }
    public boolean EditarInv_paq() {
        String sql;
        sql = "UPDATE inv_paque set cantidad='" + getCantidad() +"'";
        sql += " WHERE cod_paq='"+getCod_paq()+"' and cod_inv='"+getCod_inv()+"'";
        return con.accion(sql);
    }
}
