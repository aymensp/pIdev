/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Sponsors;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public class ServicesSponsors {
    Connection c = ConnexionBD.getinstance().getcnx();
    Statement ste;
    
    public void ajouterSponsors(Sponsors s)
    {
        try {
            Statement st=c.createStatement();
            String req="insert into sponsors(nom,adresse,type,numtel) values('"
                +s.getNom()+"','"+s.getAdresse()+"','"+s.getType()+"','"+s.getNumtel()+"')";
            
            st.executeUpdate(req);
        } catch (SQLException ex) {

            Logger.getLogger(ServicesSponsors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void modifierSponsors(int id, String Nom, String Adresse, String Type, String numTel){
       
        try {
            
            PreparedStatement pst = c.prepareStatement("update sponsors set nom=?, adresse=? ,type=? ,numtel=? where IDsponsors=?");
            pst.setString(1,Nom);
            pst.setString(2,Adresse);
            pst.setString(3, Type);
            pst.setString(4, numTel);
            pst.setInt(5,id);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
     
     public void afficherSponsors(){
        PreparedStatement ps;
        try {
            ps = c.prepareStatement("select * from sponsors");

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.println("Sponsor ID : "+rs.getInt(1)+" , Name :"+rs.getString(2)+" , Adresse :"
                    +rs.getString(3)+" ,Type :"+rs.getString(4)+" ,Phone Number");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerSponsors(Sponsors s){

        try {
        PreparedStatement pt = c.prepareStatement("delete from sponsors where IDsponsors =?");
        pt.setInt(1,s.getIDsponsors());
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void rechercherSponsors(int id){
        
        PreparedStatement ps ;
         try {
             ps= c.prepareStatement("select * from Sponsors where IDsponsor =?");
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                System.out.println("Sponsor ID : "+rs.getInt(1)+" , Name :"+rs.getString(2)+" , Adresse :"
                    +rs.getString(3)+" ,Type :"+rs.getString(4)+" ,Phone Number");
                }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public void triSponsorParID_ASC(){
        PreparedStatement ps ;
         try {
             ps= c.prepareStatement("select * from Annonce order by IDsponsor ASC ");
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                System.out.println("Sponsor ID : "+rs.getInt(1)+" , Name :"+rs.getString(2)+" , Adresse :"
                    +rs.getString(3)+" ,Type :"+rs.getString(4)+" ,Phone Number");
                }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void triSponsorsParID_DESC(){
        PreparedStatement ps ;
         try {
             ps= c.prepareStatement("select * from sponsors order by IDsponsors DESC ");
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                System.out.println("Sponsor ID : "+rs.getInt(1)+" , Name :"+rs.getString(2)+" , Adresse :"
                    +rs.getString(3)+" ,Type :"+rs.getString(4)+" ,Phone Number");
                }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAnnonces.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public ObservableList<Sponsors> readAll() throws SQLException {
    ObservableList<Sponsors> arr=FXCollections.observableArrayList();;
    ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from Sponsors");
     while (rs.next()) {                
               int id = rs.getInt(1);
               String nom=rs.getString(2);
               String adresse=rs.getString(3);
               String type =rs.getString(4);
               String numtel = rs.getString(5);
               Sponsors s=new Sponsors(id,nom, adresse, type,numtel);
     arr.add(s);
     }
    return arr;
    }
    
}
