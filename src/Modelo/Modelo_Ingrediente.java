
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


public class Modelo_Ingrediente extends Ingredientes{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Ingrediente(){
        
    }
    public Modelo_Ingrediente(String cod_ingrediente, String nombres) {
        super(cod_ingrediente, nombres);
    }
    
    
    public List<Ingredientes> listaIngrediente(String aguja){
    
        try {
            String sql="select * from ingredientes WHERE ";
            sql+=" UPPER(nombre) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Ingredientes> lp= new ArrayList<Ingredientes>();
            while(rs.next()){
                Ingredientes ingre= new Ingredientes();
                ingre.setCod_ingrediente(rs.getString("cod_ingrediente"));
                ingre.setNombre(rs.getString("nombre"));
         
                lp.add(ingre);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Ingrediente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public boolean grabar(){
        
        String sql;
        sql="INSERT INTO ingredeintes(cod_ingrediente,nombres) ";
        sql+=" VALUES ('"+getCod_ingrediente()+"','"+getNombre()+"')";
        return con.accion(sql);
    }
    
    public boolean Eliminar(){
        
        String sql;
        sql="DELETE FROM ingredientes where cod_ingrediente='"+getCod_ingrediente()+"'";
        return con.accion(sql);
         
    }
     public boolean Editar() {
        String sql;
        sql = "UPDATE ingrediente set nombre='" + getNombre() +"'";
        sql += " WHERE cod_ingrediente='" + getCod_ingrediente() + "'";
        return con.accion(sql);
    }
}
