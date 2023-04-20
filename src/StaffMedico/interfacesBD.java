/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaffMedico;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julian
 */

public interface interfacesBD extends Remote{
    public DefaultTableModel Especialistas(String buscar) throws RemoteException;
    public void metodoListarEspecialistas() throws RemoteException;
}
