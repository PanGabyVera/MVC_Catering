
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


public class Modelo_Menu extends Menu{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Menu(){
        
    }
    public Modelo_Menu(String cod_menu, String nombre, int precio, String tipo) {
        super(cod_menu,nombre,precio,tipo);
    }
    
    
    public List<Menu> listaMenu(String aguja){
    
        try {
            String sql="select * from Menu WHERE ";
            sql+=" UPPER(nom_menu) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(tipo_menu) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Menu> lp= new ArrayList<Menu>();
            while(rs.next()){
                Menu menu= new Menu();
                menu.setCod_menu(rs.getString("cod_menu"));
                menu.setNombre(rs.getString("nom_menu"));
                menu.setPrecio(rs.getInt("precio_menu"));
                menu.setTipo(rs.getString("tipo_menu"));
                
                lp.add(menu);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Menu.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public boolean grabarMen(){
        
        String sql;
        sql="INSERT INTO menu(cod_menu,nom_menu,precio_menu,tipo_menu) ";
        sql+=" VALUES ('"+getCod_menu()+"','"+getNombre()+"','"+getPrecio()+"','"+getTipo()+"')";
        return con.accion(sql);
    }
    
    public boolean EliminarMe(){
        
        String sql;
        sql="DELETE FROM menu where cod_menu='"+getCod_menu()+"'";
        return con.accion(sql);
         
    }
     public boolean EditarMe() {
        String sql;
        sql = "UPDATE menu set nom_menu='"+ getNombre()+"',precio_menu='" + getPrecio() +"'";
        sql += " WHERE cod_menu='" + getCod_menu() + "'";
        return con.accion(sql);
    }
}
