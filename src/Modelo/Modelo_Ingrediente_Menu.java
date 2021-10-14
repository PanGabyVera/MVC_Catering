
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


public class Modelo_Ingrediente_Menu extends Ingrediente_menu{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Ingrediente_Menu(){
        
    }
    public Modelo_Ingrediente_Menu(String cod_menu, String cod_ing, int cantidad, String nombre) {
        super(cod_menu,cod_ing,cantidad,nombre);
    }
    
    
    public List<Ingrediente_menu> listaIngrediente_Menu(String aguja){
    
        try {
            String sql="select * from ing_menu join ingredientes ON (ing_menu.cod_ing=ingredientes.cod_ingrediente) WHERE ";
            sql+=" UPPER(cod_men) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(cod_ing) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Ingrediente_menu> lp= new ArrayList<Ingrediente_menu>();
            while(rs.next()){
                Ingrediente_menu ingre_menu= new Ingrediente_menu();
                ingre_menu.setCod_menu(rs.getString("cod_men"));
                ingre_menu.setCod_ing(rs.getString("cod_ing"));
                ingre_menu.setCantidad(rs.getInt("cantidad")); 
                ingre_menu.setNombre(rs.getString("nom_ingre"));
                lp.add(ingre_menu);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Ingrediente_Menu.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public boolean grabarIng_menu(){
        
        String sql;
        sql="INSERT INTO ing_menu(cod_men,cod_ing,cantidad) ";
        sql+=" VALUES ('"+getCod_menu()+"','"+getCod_ing()+"','"+getCantidad()+"')";
        return con.accion(sql);
    }
    
    public boolean EliminarIng_menu(){
        
        String sql;
        sql="DELETE FROM ing_menu where cod_men='"+getCod_menu()+"' and cod_ing='"+getCod_ing()+"'";
        return con.accion(sql);
         
    }
    public boolean EliminarIngre_menu(){
        
        String sql;
        sql="DELETE FROM ing_menu where cod_men='"+getCod_menu()+"'";
        return con.accion(sql);
         
    }
    public boolean EditarIng_menu() {
        String sql;
        sql = "UPDATE ing_menu set cantidad='" + getCantidad() +"'";
        sql += " WHERE cod_men='"+getCod_menu()+"' and cod_ing='"+getCod_ing()+"'";
        return con.accion(sql);
    }
}
