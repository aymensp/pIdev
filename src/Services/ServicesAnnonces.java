/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Annonces;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public class ServicesAnnonces {
    
    Connection c = ConnexionBD.getinstance().getcnx();
     Statement ste;
     
     public void ajouterAnnonce(Annonces a)
    {
        try {
            Statement st=c.createStatement();
            String req="insert into annonce values(null,'"+a.getAdresse()+"','"+a.getType()+"','"+a.getDescription()+"',CURRENT_TIMESTAMP)";
            
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void modifierAnnonce(int id, String Adresse, String type, String Description){
       
        try {
            
            PreparedStatement pst = c.prepareStatement("update annonce set adresse=?, type=?, description=? where idannonce=?");
            pst.setString(1,Adresse);
            pst.setString(2,type);
            pst.setString(3,Description);
            pst.setInt(4,id);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void afficherAnnonce(){
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from annonce");

        ResultSet rs = pt.executeQuery();

        while (rs.next()){
            System.out.println("ID : "+rs.getInt(1)+" , Adresse :"+rs.getString(2)+" , Type :"+rs.getString(3)+
                    " ,Description:"+rs.getString(4)+" , Date de Publication :"+rs.getDate(5));
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerAnnonce(int ID){

        try {
        PreparedStatement pt = c.prepareStatement("delete from annonce where IDannonce =?");
        pt.setInt(1,ID);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void rechercherAnnonce(int id){
        
        PreparedStatement ps ;
         try {
             ps= c.prepareStatement("select * from Annonce where idannonce =?");
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                System.out.println("ID : "+rs.getInt(1)+" , Adresse :"+rs.getString(2)+" , Type :"+rs.getString(3)+ 
                        " , Description:"+rs.getString(4)+" Date de publication"+rs.getDate(5));
                }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public void triAnnonceParID_ASC(){
        PreparedStatement ps ;
         try {
             ps= c.prepareStatement("select * from Annonce order by IDannonce ASC ");
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                System.out.println("ID : "+rs.getInt(1)+" , Adresse :"+rs.getString(2)+" , Type :"+rs.getString(3)+ 
                        " ,Description:"+rs.getString(4)+" Date de publication :"+rs.getDate(5));
                }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void triAnnonceParID_DESC(){
        PreparedStatement ps ;
         try {
             ps= c.prepareStatement("select * from Annonce order by IDannonce DESC ");
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                System.out.println("ID : "+rs.getInt(1)+" , Adresse :"+rs.getString(2)+" , Type :"+rs.getString(3)+ 
                        " ,Description:"+rs.getString(4)+" Date de publication :"+rs.getDate(5));
                }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public ObservableList<Annonces> readAll() throws SQLException {
    ObservableList<Annonces> arr=FXCollections.observableArrayList();;
    ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from annonce");
     while (rs.next()) {                
               int id = rs.getInt(1);
               String adresse=rs.getString(2);
               String type=rs.getString(3);
               String Description=rs.getString(4);
               Timestamp datepub = rs.getTimestamp(5);
               Annonces p=new Annonces(id,adresse, type, Description,datepub);
     arr.add(p);
     }
    return arr;
    }
    
}
