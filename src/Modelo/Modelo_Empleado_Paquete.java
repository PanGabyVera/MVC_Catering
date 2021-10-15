
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


public class Modelo_Empleado_Paquete extends Empleado_paquete{
    Modelo_ConexionBD con=new Modelo_ConexionBD();

    public Modelo_Empleado_Paquete(){
        
    }
    public Modelo_Empleado_Paquete(String cod_paq, String cod_emp, String nombre) {
        super(cod_paq,cod_emp,nombre);
    }
    
    
    public List<Empleado_paquete> listaEmpleado_Paquete(String aguja){
    
        try {
            String sql="select * from emp_paq join empleado ON (emp_paq.cod_empl=empleado.id) join cargo ON (empleado.cargo=cargo.cod_cargo) WHERE ";
            sql+=" UPPER(cod_emp) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(cod_paq) like UPPER('%"+aguja+"%')";
            ResultSet rs=con.Consulta(sql);
            List<Empleado_paquete> lp= new ArrayList<Empleado_paquete>();
            while(rs.next()){
                Empleado_paquete emp_paq= new Empleado_paquete();
                emp_paq.setCod_paq(rs.getString("cod_paq"));
                emp_paq.setCod_emp(rs.getString("cod_empl"));
                emp_paq.setNombre(rs.getString("nom_car"));
                lp.add(emp_paq);
            }
          rs.close();
          return lp;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Empleado_Paquete.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public boolean grabarEmpleado_paq(){
        
        String sql;
        sql="INSERT INTO emp_paq(cod_paq,cod_empl) ";
        sql+=" VALUES ('"+getCod_paq()+"','"+getCod_emp()+"')";
        return con.accion(sql);
    }
    
    public boolean EliminarEmple_paq(){
        
        String sql;
        sql="DELETE FROM emp_paq where cod_paq='"+getCod_paq()+"' and cod_empl='"+getCod_emp()+"'";
        return con.accion(sql);   
    }
    public boolean EliminarEmple_paquete(){
        
        String sql;
        sql="DELETE FROM emp_paq where cod_paq='"+getCod_paq()+"'";
        return con.accion(sql);   
    }
    
}
