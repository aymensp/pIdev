/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Donneur;
import Utils.ConnexionBD;
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

/**
 *
 * @author hp
 */
public class ServiceDonneur {
    Connection c=ConnexionBD.getinstance().getcnx();
     public void ajouterdonneur(Donneur d)
    {
       
        try 
        {
            Statement st=c.createStatement();
            String req="insert into donneur values("+d.getId()+","+d.getCin()+",'"+d.getNom()+"','"+d.getPrenom()+"','"+d.getMail()+"','"+d.getDon()+"','"+d.getNumcarte()+"')";
            
            st.executeUpdate(req);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout un don !");
             alert.setHeaderText("Information");
             alert.setContentText("don bien ajouté ");
             
             alert.showAndWait();
        } catch (SQLException ex)
        {
            Logger.getLogger(ServiceDonneur.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ajout d'un don!");
             alert.setHeaderText("Information");
             alert.setContentText("don non ajouté!!!!!!!! ");
              alert.showAndWait();
        }
  
    }
    public void modifierDonneur (Donneur d ,String nom)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update donneur set nom= ? where cin=?");
            pt.setString(1,nom);
            pt.setInt(2,d.getCin());
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    /*public void afficherDonneur()
    {
        try {
            PreparedStatement pt =c.prepareStatement("select * from donneur");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("donneur {cin:"+rs.getInt(1)+" ,nom:"+rs.getString(2)+" ,prenom:"+rs.getString(3)+" ,mail:"+rs.getString(4)+" ,don:"+rs.getInt(5)+" ,numcarte:"+rs.getInt(6)+"");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */  
    
        public List<Donneur> afficherdonneur (){
        
        ArrayList<Donneur> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from donneur ");
            ResultSet rs= pt.executeQuery();
            while(rs.next()){
                Donneur a = new Donneur();
                a.setId(rs.getInt(1));
                a.setCin(rs.getInt(2));

                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setMail(rs.getString(5));
                a.setDon(rs.getInt(6));
                a.setNumcarte(rs.getInt(7));
               
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    /*public void supprimerDonneur(Donneur d)
    {
        try {
            PreparedStatement pt =c.prepareStatement("delete from donneur where cin=?" );
            pt.setInt(1,d.getCin());
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  */
       public void supprimerdonneur(String nom){
        String requete="DELETE FROM donneur WHERE nom =?";
       
        try {
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1,nom);
            pst.executeUpdate();
            System.out.println("donneur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public int sommesdons()
    {
        int somme=0;
        try {
            PreparedStatement pt =c.prepareStatement("select * from donneur");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
                somme=somme+rs.getInt(6);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return somme;
    } 
            public void rechercher (String nom)
    {
        try {
            String query="select * from donneur where nom='"+nom+"'";
           Statement st;
           ResultSet rst;
            st=c.createStatement();
            rst=st.executeQuery(query);
            rst.last();
            int nbrRow =rst.getRow();
            if(nbrRow!=0)
            {
                System.out.println("le nom  "+nom+"   est trouver "+nbrRow+"fois");
            }
            else
            {
                System.out.println("le nom "+nom+" n'est pas dans la liste");  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            public void afficherusertrierdons() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `donneur` ORDER BY don Desc ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getObject(i).toString() + " ");
                }
                System.out.println("\n");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
