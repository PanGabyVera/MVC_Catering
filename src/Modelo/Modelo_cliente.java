package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pandora
 */
public class Modelo_cliente extends cliente {

    Modelo_ConexionBD conexion = new Modelo_ConexionBD();

    //Constructores
    public Modelo_cliente() {
    }

    public Modelo_cliente(String ci, String nombres, String apellidos) {
        super(ci, nombres, apellidos);
    }

    public List<cliente> ListaClientes(String aguja) {
        String sql = "select * from cliente where";
        sql += " UPPER(id) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(nom_cli) like UPPER('%" + aguja + "%') OR";
        sql += " UPPER(ape_cli) like UPPER('%" + aguja + "%')";
        ResultSet result = conexion.Consulta(sql);
        List<cliente> lista = new ArrayList<>();
        try {
            while (result.next()) {
                cliente cli = new cliente();
                cli.setCi(result.getString("id"));
                cli.setNombres(result.getString("nom_cli"));
                cli.setApellidos(result.getString("ape_cli"));
                cli.setTelefono(result.getString("telefono"));
                cli.setCorreo(result.getString("correo"));
                cli.setDireccion(result.getString("direccion"));
                lista.add(cli);
            }
            result.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_cliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Crear() {
       String sql;
        sql = "INSERT INTO cliente(id,nom_cli,ape_cli,telefono,correo,direccion) ";
        sql += " VALUES ('" + getCi() + "','" + getNombres() + "','"
                + getApellidos() + "','" + getTelefono()+ "','"
                + getCorreo() + "','" + getDireccion() +"')";
      return conexion.accion(sql);
    }
    
    public boolean Eliminar(String id) {
        String sql;
        sql = "DELETE FROM cliente where id='" + id + "'";
        return conexion.accion(sql);

    }
    
    public boolean Editar(String id) {
        String sql = "UPDATE public.cliente "
                + "SET id='" + getCi() + "', nom_cli='" + getNombres()+ "',ape_cli='" + getApellidos()
                + "', telefono='" + getTelefono() + "', correo='" + getCorreo() + "', direccion='" + getDireccion()+"'"
                + " WHERE id = '" + id + "'";
        return conexion.accion(sql);
    }
    
}
