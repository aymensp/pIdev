/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Event;
import Entities.Volontaire;
import Utils.ConnexionBD;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
//import utils.JavaMail;

/**
 *
 * @author user
 */
public class ServiceVolontaire  
{
    Connection c=ConnexionBD.getinstance().getcnx();
    public int count(Event e)
    {
        try {
            PreparedStatement pt= c.prepareStatement("select COUNT(*) AS total from volontaire where nom_event= ?");
            pt.setString(1, e.getNomEvnet());
            ResultSet rs= pt.executeQuery();
            while(rs.next())
            {
                rs.getInt("total");
                
               return rs.getInt("total");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    return 0;
    }
    public void ajoutervolontaire(Volontaire v,Event e)
    { 
       if (count(e)<e.getNbrMax())
       {try 
        {
   
            Statement st=c.createStatement();
            String req="insert into volontaire values("+v.getId_vol()+","+v.getCin()+",'"+v.getNom()+"','"+v.getPrenom()+"','"+v.getMail()+"',"+v.getTel()+",'"+e.getNomEvnet()+"',"+v.getPresence()+","+v.getEtat()+")";   
            st.executeUpdate(req);
          // JavaMail.sendMail(v.getMail());
           Notification.sendNotification("Merci pour votre participation !!", "Volontaire ajouté ",TrayIcon.MessageType.INFO);
             
Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
             alert.setHeaderText("PARTICIPATION");
             alert.setContentText("Merci pour votre participation !!");
             
             alert.showAndWait();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (Exception ex) {
               Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
           }}
       else {
           System.out.println("matnjamch");
       Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("");
             alert.setHeaderText("Evenement Complet");
             alert.setContentText("Vous ne pouvez pas participer a cet événement parce qu'il est complet ");
             
             alert.showAndWait();
       }
       }
 
    
        
   /* public void modifiervolontaire (int id,String presence)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update volontaire set presence= ? where cin=?");
            pt.setString(1,presence);
            pt.setInt(2,id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
    public void supprimervolontaire(String nom,String nomev)
    {
        try {
            PreparedStatement pt =c.prepareStatement("delete from volontaire where mail=? and nom_event=?" );
            pt.setString(1,nom);
            pt.setString(2,nomev);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   public List<Volontaire> afficherVolontaire(){
       
        
        ArrayList<Volontaire> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from volontaire");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next()){
                Volontaire a = new Volontaire();
                a.setCin(rs.getInt(2));  
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setMail(rs.getString(5));
                a.setTel(rs.getInt(6));
                a.setNom_event(rs.getString(7));
                a.setPresence(rs.getString(8));
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public List<Volontaire> rechercherVolontaire (String auteur){
        
        String requete="select * FROM volontaire where (nom LIKE ? )";
      
        String ch="%"+auteur+"%";
        ArrayList<Volontaire> myList = new ArrayList();
        try {
            
             PreparedStatement pst = c.prepareStatement(requete);
             pst.setString(1,ch);
              
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Volontaire a = new Volontaire();
                a.setCin(rs.getInt(2));  
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setMail(rs.getString(5));
                a.setTel(rs.getInt(6));
                a.setNom_event(rs.getString(7));
                a.setPresence(rs.getString(8));
           
                myList.add(a);
                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
     public void modifierVolontaire (String presence,int id)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update volontaire set presence=? where id_vol=?");
            
 
            pt.setString(1,presence);
            pt.setInt(2,id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int getId (String nom )
    { 
                int k=0;
        try{ 
        
        
        PreparedStatement pst=c.prepareStatement("select id_vol from volontaire where nom= ?  ");
        pst.setString(1, nom);
         ResultSet rs=pst.executeQuery();
       
        while(rs.next()){
              k = rs.getInt(1);
        }    
     } catch (SQLException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    
    return k;
    
    }
    public List<Volontaire> afficherEvent(String nom){
       
        
        ArrayList<Volontaire> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from volontaire where nom_event = ?");
            pt.setString(1,nom);
            ResultSet rs= pt.executeQuery();
            
            while(rs.next()){
                Volontaire a = new Volontaire();
                a.setCin(rs.getInt(2));  
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setMail(rs.getString(5));
                a.setTel(rs.getInt(6));
                a.setNom_event(rs.getString(7));
                a.setPresence(rs.getString(8));
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public boolean RechercherVol(String nomEv , String mail)
{
        try {
            String requete="select * from volontaire where mail=? and etat=? and nom_event=? ";
            PreparedStatement pst=c.prepareStatement(requete);
            pst.setString(1, mail);
            pst.setInt(2, 1);
            pst.setString(3, nomEv);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
