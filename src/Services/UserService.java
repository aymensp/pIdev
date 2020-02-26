/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Utilisateur;
import java.awt.Desktop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.ConnexionBD;
import org.mindrot.jbcrypt.BCrypt;
import tray.notification.NotificationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 *
 * @author anasc
 */
public class UserService {

    Connection cn = ConnexionBD.getinstance().getcnx();
    ResultSet rs;
    PreparedStatement pst;

    public static int Inscription(Utilisateur u) {
        int workload = 13;
        int status = 0;
        PreparedStatement pt;
        String sql = "INSERT INTO user(id,nom,prenom, email, "
                + "password,age,numero,photo,sexe,role) VALUES(?,?,?,?,?,?,?,?,?,?)";
                 
        System.out.println(sql);
        try {
            Connection cn = ConnexionBD.getinstance().getcnx();
            pt = cn.prepareStatement(sql);
            pt.setInt(1, u.getId());
            pt.setString(2, u.getNom());
            pt.setString(3, u.getPrenom());
            pt.setString(4, u.getEmail());
            pt.setString(5, u.getPassword());
            pt.setString(6, u.getAge());
            pt.setString(7, u.getNumero());
            pt.setString(8, u.getPhoto());
            pt.setString(9, u.getSexe());
            pt.setString(10, u.getRole());
            status = pt.executeUpdate();
            System.out.println("succée");
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return status;
    }

    public int getId (String nom )
    {  int k = 0;
        try {
       
            
            
            
            PreparedStatement pst=cn.prepareStatement("select id from user where nom= ?  ");
            pst.setString(1, nom);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next()){
                k = rs.getInt(1);
            }
            
            
            return k;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
   return k;
    }


    public List<Utilisateur> getTUtilisateur() {
        ArrayList<Utilisateur> myList = new ArrayList();
     
        
        try {
           
            PreparedStatement pt =cn.prepareStatement("select * from user ");
            ResultSet rs= pt.executeQuery();
            
           
            while (rs.next()) {
                 Utilisateur a = new Utilisateur();
                a.setNom(rs.getString(2)); 
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAge(rs.getString(6));
              
                a.setNumero(rs.getString(7));
                a.setSexe(rs.getString(8));
               
                 myList.add(a);
            }
//            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return myList;
    }
    
    
    public Utilisateur login(Utilisateur u) {

        try {
            Connection cn = ConnexionBD.getinstance().getcnx();
            String loginQry = "SELECT * FROM user WHERE email = ? && password= ? && etat = '"+u.isEnabled()+"' ";
            PreparedStatement ste = cn.prepareStatement(loginQry);
            ste.setString(1, u.getEmail());
            ste.setString(2, u.getPassword());
           
            
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                u.setRole(rs.getString(9));
                u.setPassword(rs.getString(5));
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setAge(rs.getString(6));
                u.setNumero(rs.getString(7));
                u.setEnabled(rs.getInt(11));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getIdRep(String nom) {

        try {
            Connection cn = ConnexionBD.getinstance().getcnx();
            String loginQry = "SELECT * FROM user WHERE nom = ? ";
            PreparedStatement ste = cn.prepareStatement(loginQry);
            ste.setString(1, nom);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getLastId() {
        int id = 0;
        String sqlGetLastId = "SELECT MAX(id) FROM user";
        try {
            Connection cnLastId = ConnexionBD.getinstance().getcnx();
            Statement st;
            st = cnLastId.createStatement();
            id = st.executeUpdate(sqlGetLastId);
            cnLastId.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
     
     public Utilisateur findById(int id_user)throws SQLException
    {
        Utilisateur u = new Utilisateur();
        String requete="select * from user where id ='"+id_user+"';";
       PreparedStatement pst =cn.prepareStatement(requete);
       rs =pst.executeQuery(requete); 
        while(rs.next())
        {
            u=new Utilisateur(rs.getInt(1), rs.getString(3));
        }
       return u;   
    }
     public List<Utilisateur> rechercher (String auteur) throws SQLException {
        
         
         String req="select * FROM user where (nom LIKE ? )";
        
            
            
            String ch="%"+auteur+"%";
            ArrayList<Utilisateur> myList = new ArrayList();
       try{     
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1,ch);
            
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Utilisateur a = new Utilisateur();
                a.setNom(rs.getString(2)); 
                a.setPrenom(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAge(rs.getString(6));
                a.setNumero(rs.getString(7));
                a.setSexe(rs.getString(8));
                myList.add(a);
               
            }
             }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
     
     public static List<Utilisateur> getAllUsers() {
        List<Utilisateur> list = new ArrayList();
        Connection cnx = ConnexionBD.getinstance().getcnx();
   
        try {
            String sql = "select * from user ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();

                utilisateur.setNom(resultSet.getString("nom"));
                utilisateur.setPrenom(resultSet.getString("prenom"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setAge(resultSet.getString("age"));
                utilisateur.setNumero(resultSet.getString("numero"));
                utilisateur.setRole(resultSet.getString("role"));
                list.add(utilisateur);
                utilisateur.toString();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
     }
     
     
     public static boolean testMotDePasse(String motDePasseGUI, String email) {
        boolean password_verified = false;
        
        Connection cn = ConnexionBD.getinstance().getcnx();
        PreparedStatement pt;
        String req ="select * from user where email = ?";
        try {
            pt = cn.prepareStatement(req);
            pt.setString(1, email);
            ResultSet rs = pt .executeQuery();
                
            if (motDePasseGUI == rs.getString(5)){
                password_verified = true;
                }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (password_verified);
        }

        // en remplaçant 2y par 2a le cryptage on obtient le cryptage par defaut pour que la methode checkpw puisse comparer
        //password_verified = BCrypt.checkpw(motDePasseGUI, motDePasseBD);
        
     public static void supprimer(String nom) {

       Connection cnx = ConnexionBD.getinstance().getcnx();
        PreparedStatement pt;

        String req = "delete from user where nom =?";
        try {
            pt = cnx.prepareStatement(req);
            pt.setString(1, nom);
            pt.executeUpdate();
          System.out.println("Suppression terminé avec succes ");
            TrayNotification tray = new TrayNotification("succès", "supprimer", SUCCESS );
            tray.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("not ok");
        }

    }
     public void edit(String nom, String prenom ,String email,String age, String numero, String role,int id){

        Connection cnx = ConnexionBD.getinstance().getcnx();
      //  ResultSet rs;
       //PreparedStatement pst ;
       // String req = "UPDATE user set nom = ? ,prenom=?,age = ?, email=?, numero = ?, Role = ? WHERE id=?";

        try {
           
PreparedStatement pst = cnx.prepareStatement("UPDATE user set nom = ? ,prenom= ?, email = ?, age=?, numero = ?, Role = ? WHERE id=?");

            //pst = cnx.prepareStatement(req);
            pst.setString(1,nom);
            pst.setString(2,prenom);
            pst.setString(3,email); 
            pst.setString(4,age);
            pst.setString(5,numero);
            pst.setString(6,role);
            pst.setInt(7,id);
            
            
            pst.execute();
             TrayNotification tray = new TrayNotification("succès", "modifier", SUCCESS);
            tray.showAndWait();
        } catch (SQLException se) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, se);

        }

    }
       public void supprimerUser(String nom) throws SQLException
    {
        Connection cn = ConnexionBD.getinstance().getcnx();
    ResultSet rs;
    PreparedStatement pst;
          pst = cn.prepareStatement("delete * from user where (nom = ?)" );
       
         try {
          
            pst.setString(1,nom);
            pst.executeUpdate();
             TrayNotification tray = new TrayNotification("succès", "supprimer", SUCCESS );
            tray.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public List<Utilisateur> rechercherUtilisateur (String auteur){
        
        String requete="select * FROM user where (nom LIKE ? )";
      
        String ch="%"+auteur+"%";
        ArrayList<Utilisateur> myList = new ArrayList();
        try {
            
             Connection cn = ConnexionBD.getinstance().getcnx();
        PreparedStatement ps;
             pst.setString(1,ch);
              
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Utilisateur a = new Utilisateur();
                a.setNom(rs.getString(2)); 
                a.setPrenom(rs.getString(3));
                a.setAge(rs.getString(4));
                a.setEmail(rs.getString(5));
                a.setNumero(rs.getString(6));
                a.setRole(rs.getString(7));
              
                
           
                myList.add(a);
                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
/*
public int getId (String nom )
    { 
                int k=0;
        try{ 
        
        
        PreparedStatement pst=cn.prepareStatement("select id from user where nom= ?  ");
        pst.setString(1, nom);
         ResultSet rs=pst.executeQuery();
       
        while(rs.next()){
              k = rs.getInt(1);
        }    
     } catch (SQLException ex) {
            Logger.getLogger(ConnectionBase.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    
    return k;
    
    }*/
public void Bloquer(Utilisateur user) throws SQLException
    {
        Connection cn = ConnexionBD.getinstance().getcnx();
    ResultSet rs;
    PreparedStatement pst;
          pst = cn.prepareStatement("UPDATE user set etat = ?  WHERE id=?" );
       
         try {
          
            pst.setInt(1,1);
            pst.setInt(2,user.getId());
            pst.executeUpdate();
             TrayNotification tray = new TrayNotification("succès", "bloquer", SUCCESS );
            tray.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}


