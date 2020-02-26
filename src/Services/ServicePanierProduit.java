/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.PanierProduit;
import Entities.Produit;
import IServices.IServicePanierProduit;
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
public class ServicePanierProduit implements IServicePanierProduit {
    
       Connection cnx= ConnexionBD.getinstance().getcnx();
Statement st;
    PreparedStatement pt;
    ResultSet rs;

    
  
       @Override
    public void AjouterAuPanier(Produit a) {
    
String requete=" INSERT INTO panier_produit(idProd,nomProd,nomRef,prix,img) VALUES(?,?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setInt(1, a.getIdProd());
            pt.setString(2, a.getNomProd());
            pt.setString(3, a.getNomRef());
            pt.setDouble(4, a.getPrix());
            pt.setString(5, a.getImg());            
            pt.executeUpdate();
      
            System.out.println("Produit ajouté au Panier ");
        } catch (SQLException ex) {
            
            
           System.out.println(ex.getMessage());  
        }
    }
    

    
       @Override
    public void SupprimerDuPanier(int id) {
        String req = "delete from panier_produit where idProd =?";
        try {
            pt = cnx.prepareStatement(req);
            pt.setInt(1, id);
            pt.executeUpdate();
          System.out.println("Suppression terminé avec succes ");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("not ok");
        }
    }

   

 
    
       @Override
    public Produit RecupererProduit(int id) {
        
        String req = "SELECT * FROM `produits` WHERE `idProd`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Produit a = new Produit();
            while(rs.next())
            {
               a.setIdProd(rs.getInt(1));
               a.setNomProd(rs.getString(2));
               a.setNomRef(rs.getString(3));
                     a.setImg(rs.getString(4));
                       a.setPrix(rs.getDouble(5));
               a.setDispo(rs.getString(6));
         
               a.setLikes(rs.getInt(8));             
             
               
               
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
       @Override
    public int LonguerPanier() {
        int count=0;
     String req = "select count(*) from `panier_produit`";
        try {
            PreparedStatement pstm = cnx.prepareStatement(req);
 ResultSet res = pstm.executeQuery();
 while (res.next()) {
            count = res.getInt(1);
        }
            return count;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
      
       
    }

 
       @Override
    public int existProduit(int id) {
        String req = "SELECT * FROM `panier_produit` WHERE `idProd`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            ResultSet res = pt.executeQuery();
           if ( res.absolute (1)) 
           { 
               
System.out.print (" L'objet ResultSet n'est pas vide "); 
return 1;
}
           else { 
System.out.print (" L'objet ResultSet est vide " ) ; 
return 0;
           } 
              
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

  
       @Override
    public ObservableList<PanierProduit> getall() {
    ObservableList<PanierProduit> annoncesp = FXCollections.observableArrayList();
       String req = "SELECT * FROM `panier_produit` ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next())
            {   
               PanierProduit a = new PanierProduit();
               a.setId(rs.getInt(1));
               a.setIdProd(rs.getString(2));
    
               a.setNomProd(rs.getString(3));
              
               a.setPrix(rs.getDouble(5));
              
               a.setPhoto(rs.getString(6));
               
               annoncesp.add(a);   
            }
             System.out.println("recup ok ");
            return annoncesp;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
