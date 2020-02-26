/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.LigneCommande;
import Entities.PanierProduit;
import Entities.Produit;
import IServices.IServiceLigneCommande;
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
public class ServiceLigneCommande implements IServiceLigneCommande {
    Connection cnx= ConnexionBD.getinstance().getcnx();
            
Statement st;
    PreparedStatement pt;
    ResultSet rs;
    
 public   ServiceLigneCommande(){
     
 }
    @Override
    public void AjouterLigneCommande(LigneCommande c) {
        String requete=" INSERT INTO ligne_commande(id_commande,idProd,id_utilisateur,prixProd) VALUES(?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setInt(1, c.getId_commande());
            pt.setInt(2, c.getIdProd());
            pt.setInt(3, c.getId_user());
            pt.setDouble(4, c.getPrix_Produit());
            pt.executeUpdate();
            System.out.println("Ligne Ajouté");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());  
        }
    }

  
    @Override
    public void AnnulerLigneCommande(int id) {
       String req = "delete from ligne_commande where id_commande =?";
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
    public ObservableList<LigneCommande> getall() {
       ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande`";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setIdProd(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_Produit(rs.getInt(5));
                   l_cmd.add(a); 
            }
            
            return l_cmd;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   
    @Override
    public ObservableList<LigneCommande> RecupererLignesCommande(int id) {
        ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande` WHERE `id_commande`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setIdProd(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_Produit(rs.getDouble(5));
                   l_cmd.add(a); 
            }
            
            return l_cmd;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    @Override
    public ObservableList<PanierProduit> RecupererLignesCommande2(int id,ObservableList<PanierProduit> A) {
        ObservableList<PanierProduit> l_cmd = FXCollections.observableArrayList();
        ObservableList<PanierProduit> tab2 = FXCollections.observableArrayList();
       ObservableList<PanierProduit> t3=A;
        String req = "SELECT * FROM `ligne_commande` WHERE `id_commande`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            PanierProduit a = new PanierProduit();
            for(int i=0;i<t3.size();i++)
            {
            while(rs.next())
            {
                if(Integer.toString(rs.getInt(3)).equals(t3.get(i).getIdProd()))
                {
                a=t3.get(i);
                 tab2.add(a); 
                }
              
                  
            }
            }
            return tab2;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

  /*
    public int VerifProduit(int id) {
       ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande` WHERE `id_annonce`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setIdProd(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_Produit(rs.getDouble(5));
                   l_cmd.add(a); 
            }
            
            if(l_cmd.size()>0)
            {
            return 1;
            }
            else
            {
            return 0;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
*/
   
    @Override
    public Produit RecupA(int id) {
         System.out.println("f west el fonction mta reuperation11");
        //ObservableList<Produit> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `produits` WHERE `idProd`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Produit a = new Produit();
            while(rs.next())
            {
                 System.out.println("f west el fonction mta reuperation");
                System.out.println(rs.getString(4));
             
              a.setIdProd(rs.getInt(1));
              
               a.setNomRef(rs.getString(3));
              
               a.setNomProd(rs.getString(2));
       
             
               a.setPrix(rs.getDouble(5));
             
               a.setDispo(rs.getString(6));
               a.setImg(rs.getString(4));
            
               a.setLikes(rs.getInt(8));
           
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}

    @Override
    public int VerifProduit(int id) {
        ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande` WHERE `idProd`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setIdProd(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_Produit(rs.getDouble(5));
                   l_cmd.add(a); 
            }
            
            if(l_cmd.size()>0)
            {
            return 1;
            }
            else
            {
            return 0;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
}

    
}