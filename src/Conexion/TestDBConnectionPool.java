/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import Conexion.ConnectionPool;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paula
 */
public class TestDBConnectionPool {

    /**
     * @param args the command line arguments
     */
    //introduce las condiciones de la conexión.
    private final String DB="proyectogh";
    private final String URL="jdbc:mysql://localhost:33060/";
    private final String USER="root";
    private final String PASS="";
    String driver = "com.mysql.jdbc.Driver"; //com.mysql.cj.jdbc.Driver
    
    Connection cx;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    public Connection test(){
        try {

            Connection c =  ConnectionPool.getInstance().getConnection();
            if(c!=null){
                System.out.println("conectado ");
                try{
                    Class.forName(driver);
                    cx = DriverManager.getConnection(URL+DB,USER,PASS);
                    //System.out.println("Se conectó a la BD "+DB);
                }catch(ClassNotFoundException |SQLException ex){
                    //System.out.println("No se conectó a la BD "+DB);
                    //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
                }
                return cx;
                //ConnectionPool.getInstance().closeConnection(c);
            }else{
                System.out.println("No conectado"); 
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cx;
    }
    public void close(){
        try{
            ConnectionPool.getInstance().closeConnection(cx);
            System.out.println("Cerrando Sesión");
        }catch(SQLException ex){
            //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}