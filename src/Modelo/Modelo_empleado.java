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
public class Modelo_empleado extends empleado{
     Modelo_ConexionBD conexion = new Modelo_ConexionBD();

    public Modelo_empleado() {
    }

    public Modelo_empleado(String cargo, String cod, String nom_car, String suel, String ci, String nombres, String apellidos, String telefono, String correo, String direccion) {
        super(cargo, cod, nom_car, suel, ci, nombres, apellidos, telefono, correo, direccion);
    }

    
    public List<empleado> ListaEmpleados(String aguja) {
        String sql = "select * from empleado where";
        sql += " UPPER(id) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(nom_empleado) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(cargo) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(ape_empleado) like UPPER('%" + aguja + "%')";
        ResultSet result = conexion.Consulta(sql);
        List<empleado> lista = new ArrayList<>();
        try {
            while (result.next()) {
                empleado e = new empleado();
                e.setCod(result.getString("cod_emp"));
                e.setCi(result.getString("id"));
                e.setNombres(result.getString("nom_empleado"));
                e.setApellidos(result.getString("ape_empleado"));
                e.setTelefono(result.getString("telefono"));
                e.setCorreo(result.getString("correo"));
                e.setDireccion(result.getString("direccion"));
                e.setCargo(result.getString("cargo"));
                lista.add(e);
            }
            result.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_empleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Crear() {
       String sql;
        sql = "INSERT INTO empleado(id,nom_empleado,ape_empleado,telefono,correo,direccion,cargo,cod_emp) ";
        sql += " VALUES ('" + getCi() + "','" + getNombres() + "','"
                + getApellidos() + "','" + getTelefono()+ "','"
                + getCorreo() + "','" + getDireccion() + "','"+ getCargo()+"','" + getCod() +"')";
      return conexion.accion(sql);
    }
    
    public boolean Eliminar(String id) {
        String sql;
        sql = "DELETE FROM empleado where id='" + id + "'";
        return conexion.accion(sql);

    }
    
    public boolean Editar(String id) {
        String sql = "UPDATE public.empleado "
                + "SET id='" + getCi() + "', nom_empleado='" + getNombres()+ "',ape_empleado='" + getApellidos()
                + "', telefono='" + getTelefono() + "', correo='" + getCorreo() + "', direccion='" + getDireccion() 
                + "', cargo='" + getCargo()+ "', cod_emp='" + getCod()+"'"
                + " WHERE id = '" + id + "'";
        return conexion.accion(sql);
    }
    
    
    
    
    
    
    
    
    
    
    
}
