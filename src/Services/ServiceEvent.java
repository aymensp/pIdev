/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author user
 */
public class ServiceEvent  
{
    Connection c=ConnexionBD.getinstance().getcnx();
    public void ajouterevent(Event e) throws MalformedURLException
    {
       
        try 
        {   
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
            Statement st=c.createStatement();
            String req="insert into event values("+e.getId_event()+",'"+e.getNomEvnet()+"','"+e.getAdresse()+"','"+e.getDate()+"','"+e.getDescription()+"',"+e.getNbrMax()+",'"+e.getImage()+"')";
            
             st.executeUpdate(req);
             try {
               Notification.sendNotification("Un évenement", " Ajout d'un Evenement",TrayIcon.MessageType.INFO);
           } catch (AWTException ex) {
               Logger.getLogger(ServiceVolontaire.class.getName()).log(Level.SEVERE, null, ex);
           }
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout evenement!");
             alert.setHeaderText("Information");
             alert.setContentText("Evenement bien ajouté ");
             
             alert.showAndWait();
        } catch (SQLException ex)
        {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ajout evenement!");
             alert.setHeaderText("Information");
             alert.setContentText("Evenement non ajouté!!!!!!!! ");
              alert.showAndWait();
        }
  
    }
        
    public void modifierevent (String nom ,String adresse, String date, String description , int nbrMax,int id)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update event set nomEvent=? ,adresse=?, date=?, description=?, nbrMax=? where id_event=?");
            pt.setString(1,nom);
            pt.setString(2,adresse);
            pt.setString(3,date);
            pt.setString(4,description);
 
            pt.setInt(5,nbrMax);
            pt.setInt(6,id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int getId (String nom )
    { 
                int k=0;
        try{ 
        
        
        PreparedStatement pst=c.prepareStatement("select id_event from event where nomEvent= ?  ");
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
  /*  public void afficherevent()
    {
        try {
            PreparedStatement pt =c.prepareStatement("select * from event");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("Event : \n Nom de l'event:"+rs.getString(2)+"\n ,l'adresse:"+rs.getString(3)+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public void supprimerevent(String nom)
    {
        try {
            PreparedStatement pt =c.prepareStatement("delete from event where nomEvent=?" );
            pt.setString(1,nom);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Event RechercherEvent(int id) {
        Event e=new Event();
        try{ 
        
        String requete="select * from event where id_event="+id+" ";
        PreparedStatement pst=c.prepareStatement(requete);
         ResultSet rs=pst.executeQuery();
       
        while(rs.next()){
            System.out.println("evenement existe \n");
            e=new Event(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
          System.out.println(e.toString());
        }    
     } catch (SQLException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    
    return e;
    }
    public void afficherusertrier() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `event` ORDER BY nbrMax Desc ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getObject(i).toString() + " ");
                }
                System.out.println("\n");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
      public List<Event> afficherEvents(){
       
        
        ArrayList<Event> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from event");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next()){
                Event a = new Event();
                a.setNomEvnet(rs.getString(2));  
                a.setAdresse(rs.getString(3));
                a.setDate(rs.getDate(4).toString());
                a.setDescription(rs.getString(5));
                
                a.setNbrMax(rs.getInt(6));
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
      public List<Event> rechercherEvent (String auteur){
        
        String requete="select * FROM event where (nomEvent LIKE ? )";
      
        String ch="%"+auteur+"%";
        ArrayList<Event> myList = new ArrayList();
        try {
            
             PreparedStatement pst = c.prepareStatement(requete);
             pst.setString(1,ch);
              
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Event a = new Event();
                a.setNomEvnet(rs.getString(2)); 
                a.setAdresse(rs.getString(3));
                a.setDate(rs.getString(4));
                a.setDescription(rs.getString(5));
              
                a.setNbrMax(rs.getInt(6));
           
                myList.add(a);
                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
      
    public List<Event> afficherEvents2(){
       
        
        ArrayList<Event> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from event");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next()){
                Event a = new Event();
                a.setNomEvnet(rs.getString(2));  
                a.setAdresse(rs.getString(3));
                a.setDate(rs.getDate(4).toString());
                a.setDescription(rs.getString(5));
                
                a.setNbrMax(rs.getInt(6));
                a.setImage(rs.getString(7));
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
            return myList;
}
}
