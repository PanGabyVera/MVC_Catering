
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pandora
 */
public class Modelo_inventario extends inventario{

     Modelo_ConexionBD conexion = new Modelo_ConexionBD();
     
    public Modelo_inventario() {
    }
    
    public Modelo_inventario(String cod_inventario, String nombre, double precio) {
        super(cod_inventario, nombre, precio);
    }
    
 
  public List<inventario> Listainventario(String aguja) {
        String sql = "select * from inventario where";
        sql += " UPPER(cod_inv) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(nom_inv) like UPPER('%" + aguja + "%') ";
        ResultSet result = conexion.Consulta(sql);
        List<inventario> lista = new ArrayList<>();
        try {
            while (result.next()) {
                inventario inv=new inventario();
                inv.setCod_inventario(result.getString("cod_inv"));
                inv.setNombre(result.getString("nom_inv"));
                inv.setPrecio(result.getDouble("precio_inv"));
                lista.add(inv);
            }
            result.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

  public boolean Crear() {
       String sql;
        sql = "INSERT INTO inventario(cod_inv,nom_inv,precio_inv) ";
        sql += " VALUES ('" + getCod_inventario() + "','" + getNombre() + "','" + getPrecio() +"')";
         
      return conexion.accion(sql);
    }
  
public boolean Eliminar(String id) {
        String sql;
        sql = "DELETE FROM inventario where cod_inv='" + id + "'";
        return conexion.accion(sql);

    }
    
    public boolean Editar(String id) {
        String sql = "UPDATE public.inventario "
                + "SET cod_inv='" + getCod_inventario() + "', nom_inv='" + getNombre()+ "',precio_inv='" + getPrecio()+"'"
                 + " WHERE cod_inv = '" + id + "'";
        return conexion.accion(sql);
    }

} 
