/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.refugies;
import Utils.ConnexionBD;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Alert;

/**
 *
 * @author user
 */
public class Servicerefugies 
{
    Connection c=ConnexionBD.getinstance().getcnx();
    public void ajouterrefugies (refugies p)
    {
       
        try 
        {
            Statement st=c.createStatement();
            String req="insert into refugies values("+p.getIdRef()+",'"+p.getNom()+"','"+p.getPrenom()+"','"+p.getAge()+"','"+p.getPays()+"')";
            
            st.executeUpdate(req);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout d'un refugies!");
             alert.setHeaderText("Information");
             alert.setContentText("Refugie ajouté ");
             
             alert.showAndWait();
        } catch (SQLException ex)
        {
            Logger.getLogger(Servicerefugies.class.getName()).log(Level.SEVERE, null, ex);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ajout d'un refugies!");
             alert.setHeaderText("Information");
             alert.setContentText("Refugie non ajouté ");
             
             alert.showAndWait();
        }
  
    }
        
   /* public void modifierrefugies (refugies p,String nom)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update refugies set nom= ? where idRef=?");
            pt.setString(1,nom);
            pt.setInt(2,p.getIdRef());
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Servicerefugies.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    public void modifierrefugies(int id,String nom,String prenom,int age,String pays){
         String requete="UPDATE refugies SET nom=? , prenom=? , age=? , pays=? WHERE idRef=?";
         try {
            PreparedStatement pst = c.prepareStatement(requete);
//myCNX.getCnx().prepareStatement(requete);
           
            pst.setString(1,nom);
            pst.setString(2, prenom );
            pst.setInt(3, age );
            pst.setString(4, pays );
            pst.setInt(5, id);
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    /*public void afficherrefugies ()
    {
        try {
            PreparedStatement pt =c.prepareStatement("select * from refugies ");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("refugies {idRef:"+rs.getInt(1)+" ,nom:"+rs.getString(2)+" ,prenom:"+rs.getString(3)+" ,age"+rs.getInt(4)+" ,pays"+rs.getString(5)+"");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicerefugies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
        public List<refugies> afficherrefugies (){
        
        ArrayList<refugies> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from refugies ");
            ResultSet rs= pt.executeQuery();
            while(rs.next()){
                refugies a = new refugies();
                a.setIdRef(rs.getInt(1));
                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setAge(rs.getInt(4));
                a.setPays(rs.getString(5));
               
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    /*public void supprimerrefugies (refugies p)
    {
        try {
            PreparedStatement pt =c.prepareStatement("delete from refugies  where idRef=?" );
            pt.setInt(1,p.getIdRef());
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Servicerefugies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void supprimerrefugies(int id){
        String requete="DELETE FROM refugies WHERE idRef =?";
       
        try {
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
         //   System.out.println("refugié supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public void afficherusertrierage() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `refugies` ORDER BY age Desc ");
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
      public List<refugies> rechercherEvent (String auteur){
        
        String requete="select * FROM refugies where (nom LIKE ? )";
      
        String ch="%"+auteur+"%";
        ArrayList<refugies> myList = new ArrayList();
        try {
            
             PreparedStatement pst = c.prepareStatement(requete);
             pst.setString(1,ch);
              
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                refugies a = new refugies();
                a.setIdRef(rs.getInt(1));

                a.setNom(rs.getString(2));
                a.setPrenom(rs.getString(3));
                a.setAge(rs.getInt(4));
                a.setPays(rs.getString(5));
               
                
           
                myList.add(a);
                

                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
      public void pdf() throws FileNotFoundException
    {
        try {
            String file_name ="C:\\Users\\aymen\\Desktop\\pdf\\ines.pdf";
            Document document = new Document();
            try {
                //file_name.setReadable(true,false);
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
            } catch (DocumentException ex) {
                Logger.getLogger(Servicerefugies.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            PreparedStatement pt = c.prepareStatement("select * from refugies");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) { 
                Paragraph para=new Paragraph("Refugies [ nom: " +rs.getString(2) + " prenom : " + rs.getString(3) + " age: " + rs.getInt(4)+" pays: " + rs.getString(5)+"]");
                //System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
                document.add(para);
                document.add(new Paragraph(" "));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("PDF");
             alert.setHeaderText("Information");
             alert.setContentText("Voir votre dossier pdf sur le bureau  ");
             
             alert.showAndWait();
                
            }
            document.close();
        } catch (SQLException ex) {
            Logger.getLogger(refugies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(refugies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(refugies.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
