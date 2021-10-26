/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Modelo_cargo  extends cargo{
    
    Modelo_ConexionBD conexion = new Modelo_ConexionBD();

    public Modelo_cargo() {
    }
    
    public List<cargo> Listacargo(String aguja) {
        String sql = "select * from cargo where";
        sql += " UPPER(cod_cargo) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(nom_car) like UPPER('%" + aguja + "%') ";
        ResultSet result = conexion.Consulta(sql);
        List<cargo> lista = new ArrayList<>();
        try {
            while (result.next()) {
                cargo c=new cargo();
                c.setCod_car(result.getString("cod_cargo"));
                c.setNom(result.getString("nom_car"));
                c.setSueldo(result.getInt("sueldo"));
                lista.add(c);
            }
            result.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_cargo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

  public boolean Crear() {
       String sql;
        sql = "INSERT INTO cargo(cod_cargo,nom_car,sueldo) ";
        sql += " VALUES ('" + getCod_car() + "','" + getNom() + "','" + getSueldo() +"')";
         
      return conexion.accion(sql);
    }
  
public boolean Eliminar(String id) {
        String sql;
        sql = "DELETE FROM cargo where cod_cargo='" + id + "'";
        return conexion.accion(sql);

    }
    
    public boolean Editar(String id) {
        String sql = "UPDATE public.cargo "
                + "SET cod_cargo='" + getCod_car() + "', nom_car='" + getNom()+ "',sueldo='" + getSueldo()+"'"
                 + " WHERE cod_cargo = '" + id + "'";
        return conexion.accion(sql);
    }
    
    
    
    
    
}
