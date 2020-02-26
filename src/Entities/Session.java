/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.Properties;

//import javax.mail.Authenticator;

/**
 *
 * @author anasc
 */
public class Session {
    private static int idUser;
 private static String nomuser;
  


    public static void start(Utilisateur  s) {
        
        idUser = s.getId();
    
        
       
    }
    
    public  static void setCurrentSessionToNull()
    {
        idUser=-1;
    }
    

    public static Utilisateur getCurrentSession() {
        Utilisateur u = new Utilisateur();
        if (idUser != -1) {
        
            return u ;
        } else {
            return u;
        }
    }

    public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    /*public static Session getInstance(Properties props, Authenticator authenticator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
