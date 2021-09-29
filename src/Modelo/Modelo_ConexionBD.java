/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pandora
 */
public class Modelo_ConexionBD {
    String cadenaConexion="jdbc:postgresql://localhost:5432/Catering";
    String usuario="postgres";
    String contraseña="1234";
    
    Connection con;

    public Modelo_ConexionBD () {
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modelo_ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con= DriverManager.getConnection(cadenaConexion, usuario, contraseña);
            System.out.println("conexion exitosa");
        } catch (Exception ex) {
            Logger.getLogger(Modelo_ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet Consulta(String sqlc){//para la conexion
        try {
            Statement st=con.createStatement();
            ResultSet rb=st.executeQuery(sqlc);
            return rb;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }    
}
