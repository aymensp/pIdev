/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import doryan.windowsnotificationapi.fr.Notification;
import Entities.Camps;
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
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import Utils.ConnexionBD;
/**
 *
 * @author AMINE
 */
public class ServiceCamp {
 Connection c=ConnexionBD.getinstance().getcnx();
   

    
   public void  AjouterCamp(Camps u) throws SQLException {
        PreparedStatement pt;
        String sql = "INSERT INTO camps(idCamp, nbrmax, nbrrefug, "
                + "nomCamp,adresse) VALUES(?,?,?,?,?)";
                 
        System.out.println(sql);
        try {
            Connection cn = ConnexionBD.getinstance().getcnx();
            pt = cn.prepareStatement(sql);
            pt.setString(1, u.getIdCamp());
            pt.setString(2, u.getNbrmax());
            pt.setString(3, u.getNomCamp());
            pt.setString(4, u.getAdresse());
            
            
            System.out.println("succée");
        } catch (SQLException e) {

    }
   }
    
    public void  SupprimerCamp(String nomCamp) {

 
        try {
            PreparedStatement pt =c.prepareStatement("delete from camps where nomCamp=?" );
            pt.setString(1,nomCamp);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try {
            String request = "DELETE FROM `camps` WHERE idCamp ='" + idCamp + "'";
            pt = cn.prepareStatement(request);
            int res = pt.executeUpdate();
            System.err.println(res);

            //cn.close();
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        /*try {
            String request = "DELETE FROM `camps` WHERE idCamp ='" + idCamp + "'";
            pt = cn.prepareStatement(request);
            int res = pt.executeUpdate();
            System.err.println(res);

            //cn.close();
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
*/
        

    }
public void updateCamp (String idCamp,String nomCamp,String nbrMax,String adresse)
{     //   Connection cn = ConnectionBase.getInstance().getCnx();
 //   ResultSet rs;
   // PreparedStatement pst;
    //String req="UPDATE camps SET idCamp=?, nomCamp=?,nbrmax=?,adresse=? WHERE idCamp =?" ; 
        try { 
            PreparedStatement ste= c.prepareStatement("UPDATE camps SET idCamp=?, nomCamp=?,nbrmax=?,adresse=? WHERE idCamp =?");
           // PreparedStatement ste = cn.prepareStatement(req) ;
            ste.setString(1,idCamp);
            ste.setString(2,nomCamp);
            ste.setString(3,nbrMax);
            ste.setString(4,adresse);
            ste.setString(5,idCamp);
             ste.execute();
            TrayNotification tray = new TrayNotification("succès", "modifier", SUCCESS);
            tray.showAndWait();
        } catch (SQLException ex) {
             Logger.getLogger(ServiceCamp.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
   
public  List<Camps> getCamp() {
            ArrayList<Camps> myList = new ArrayList();
            
        try {
            PreparedStatement pt =c.prepareStatement("select * from camps");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next()){
                Camps a = new Camps();
                a.setIdCamp(rs.getString(1));
                  a.setNbrmax(rs.getString(2));
                a.setNomCamp(rs.getString(3));  
                
              
                a.setAdresse(rs.getString(4));
                
          
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;

}
    
        /*List<Camps> list = new ArrayList<Camps>();
        Connection cnx = ConnectionBase.getInstance().getCnx();
    ResultSet rs;
    PreparedStatement pt;
        try {
            String sql = "select * from camps ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Camps camp = new Camps();

               camp.setNomCamp(resultSet.getString("nomCamp"));
               camp.setNbrmax(resultSet.getString("nbrmax"));
               camp.setAdresse(resultSet.getString("adresse"));
               camp.setIdCamp(resultSet.getString("idCamp"));
               list.add(camp);
               camp.toString();
            }
//            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }*/

 public void ajouterCamps(Camps e) throws MalformedURLException
    {
       
        try 
        {   
            
            Statement st=c.createStatement();
            String req="insert into camps values('"+e.getIdCamp()+"','"+e.getNbrmax()+"','"+e.getNomCamp()+"','"+e.getAdresse()+"')";
            
             st.executeUpdate(req);
             try {
               Notification.sendNotification("Un Camp", " Ajout d'un Camp",TrayIcon.MessageType.INFO);
           } catch (AWTException ex) {
               Logger.getLogger(ServiceCamp.class.getName()).log(Level.SEVERE, null, ex);
           }
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout Camp!");
             alert.setHeaderText("Information");
             alert.setContentText("Camp bien ajouté ");
             
             alert.showAndWait();
             TrayNotification tray = new TrayNotification("succès", "ajouter", SUCCESS );
            tray.showAndWait();
        } catch (SQLException ex)
        {
            Logger.getLogger(ServiceCamp.class.getName()).log(Level.SEVERE, null, ex);
           Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ajout Camp!");
             alert.setHeaderText("Information");
             alert.setContentText("Camp non ajouté!!!!!!!! ");
              alert.showAndWait();
              
        }
  
}


    
}
