
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


public class Modelo_Menu_Paquete extends Menu_paquete{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Menu_Paquete(){
        
    }
    public Modelo_Menu_Paquete(String cod_paq, String cod_men, int cantidad, String nombre) {
        super(cod_paq,cod_men,cantidad,nombre);
    }
    
    
    public List<Menu_paquete> listaMenu_Paquete(String aguja){
    
        try {
            String sql="select * from menu_paque join menu ON (menu_paque.cod_men=menu.cod_menu) WHERE ";
            sql+=" UPPER(cod_men) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(cod_paq) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Menu_paquete> lp= new ArrayList<Menu_paquete>();
            while(rs.next()){
                Menu_paquete menu_paq= new Menu_paquete();
                menu_paq.setCod_paq(rs.getString("cod_paq"));
                menu_paq.setCod_menu(rs.getString("cod_men"));
                menu_paq.setCantidad(rs.getInt("cantidad")); 
                menu_paq.setNombre(rs.getString("nom_menu"));
                lp.add(menu_paq);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Menu_Paquete.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public boolean grabarMenu_paq(){
        
        String sql;
        sql="INSERT INTO menu_paque(cod_paq,cod_men,cantidad) ";
        sql+=" VALUES ('"+getCod_paq()+"','"+getCod_menu()+"','"+getCantidad()+"')";
        return con.accion(sql);
    }
    
    public boolean EliminarMenu_paq(){
        
        String sql;
        sql="DELETE FROM menu_paque where cod_paq='"+getCod_paq()+"' and cod_men='"+getCod_menu()+"'";
        return con.accion(sql);
         
    }
    public boolean EditarMenu_paq() {
        String sql;
        sql = "UPDATE menu_paque set cantidad='" + getCantidad() +"'";
        sql += " WHERE cod_paq='"+getCod_paq()+"' and cod_men='"+getCod_menu()+"'";
        return con.accion(sql);
    }
}
