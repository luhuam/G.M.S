/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaffMedico;

import Conexion.TestDBConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Julian
 */

public class Buscar extends UnicastRemoteObject implements interfacesBD{
    TestDBConnectionPool cn = new TestDBConnectionPool();
    Connection cnE;
    DefaultTableModel modeloE;
    Statement ste;
    ResultSet rse;
    int idE;
    
    public Buscar() throws RemoteException{}
    
    @Override
    public DefaultTableModel Especialistas(String buscar){
        String [] nombreColumna = {"id_dni_personal", "apellido_paterno", "apellido_materno", "nombres", "nombre_especialidad", "fecha_nacimiento", "turno", "CONSULTAS"};
        Object [] especialistasSearch = new Object[8];
        DefaultTableModel modelo = new DefaultTableModel(null, nombreColumna);
        
        String sql ="SELECT medico.id_dni_personal, personal.apellido_paterno, personal.apellido_materno, personal.nombres, especialidad.nombre_especialidad, personal.fecha_nacimiento, turno.descripcion, COUNT(citas.dni_medico) AS 'CONSULTAS' FROM medico\n" +
                    "INNER JOIN personal ON personal.id_dni = medico.id_dni_personal\n" +
                    "INNER JOIN especialidad ON medico.codigo_especialidad = especialidad.codigo_especialidad\n" +
                    "INNER JOIN turno ON personal.turno = turno.codigo_turno\n" +
                    "INNER JOIN citas ON medico.id_dni_personal = citas.dni_medico AND citas.dni_medico = personal.id_dni\n" +
                    "WHERE id_dni_personal LIKE '%"+buscar+"%' OR apellido_paterno LIKE '%"+buscar+"%' OR apellido_materno LIKE '%"+buscar+"%' OR nombres LIKE '%"+buscar+"%' OR nombre_especialidad LIKE '%"+buscar+"%' OR fecha_nacimiento LIKE '%"+buscar+"%' OR descripcion LIKE '%"+buscar+"%'\n" +
                    "GROUP BY id_dni_personal";
        
        try{
            cnE = cn.test();
            ste = cnE.createStatement();
            rse = ste.executeQuery(sql);
            
            while(rse.next()){
                especialistasSearch[0] = rse.getInt("id_dni_personal");
                especialistasSearch[1] = rse.getString("apellido_paterno");
                especialistasSearch[2] = rse.getString("apellido_materno");
                especialistasSearch[3] = rse.getString("nombres");
                especialistasSearch[4] = rse.getString("nombre_especialidad");
                especialistasSearch[5] = rse.getString("fecha_nacimiento");
                especialistasSearch[6] = rse.getString("descripcion");
                especialistasSearch[7] = rse.getInt("CONSULTAS");
                
                modelo.addRow(especialistasSearch);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return modelo;
    }
    
    @Override
    public void metodoListarEspecialistas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
    }
    
}
