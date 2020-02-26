/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author user
 */
public class ConnexionBD{
    private static String url="jdbc:mysql://localhost:3306/tunfugees";
    private static String usr="root";
    private static String pwd="";
    private static Connection cnx;
    
    private static ConnexionBD mycon;
    public Connection getcnx(){return cnx;}
     
    private ConnexionBD ()
    {
        try {
        cnx=DriverManager.getConnection(url,usr,pwd);
        System.out.println("Connection etablie");
    }
        catch(SQLException ex)
    {
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null,ex);
        System.out.println("erreeuuuur");
    }
    }
    public static ConnexionBD getinstance()
    {
        if (mycon==null)
        {
            mycon=new ConnexionBD();
        }
        return mycon;
    }
}
