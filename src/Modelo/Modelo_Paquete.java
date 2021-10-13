
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


public class Modelo_Paquete extends Paquete{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Paquete(){
        
    }
    public Modelo_Paquete(String cod_paquete, String nombre, String tipo,int precio) {
        super(cod_paquete,nombre,tipo,precio);
    }
    
    
    public List<Paquete> listaPaquete(String aguja){
    
        try {
            String sql="select * from paquete WHERE ";
            sql+=" UPPER(nom_paq) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(tipo_paq) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Paquete> lp= new ArrayList<Paquete>();
            while(rs.next()){
                Paquete paquete= new Paquete();
                paquete.setCod_paquete(rs.getString("cod_paq"));
                paquete.setNombre(rs.getString("nom_paq"));
                paquete.setPrecio(rs.getInt("precio_paq"));
                paquete.setTipo(rs.getString("tipo_paq"));
                
                lp.add(paquete);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Paquete.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public boolean grabarPaq(){
        
        String sql;
        sql="INSERT INTO paquete(cod_paq,nom_paq,precio_paq,tipo_paq) ";
        sql+=" VALUES ('"+getCod_paquete()+"','"+getNombre()+"','"+getPrecio()+"','"+getTipo()+"')";
        return con.accion(sql);
    }
    
    public boolean EliminarPaq(){
        
        String sql;
        sql="DELETE FROM paquete where cod_paq='"+getCod_paquete()+"'";
        return con.accion(sql);
         
    }
     public boolean EditarPaq() {
        String sql;
        sql = "UPDATE paquete set nom_paq='"+ getNombre()+"',precio_paq='" + getPrecio() +"'";
        sql += " WHERE cod_paq='" + getCod_paquete() + "'";
        return con.accion(sql);
    }
}
