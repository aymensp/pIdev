/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Commande;
import Entities.PanierProduit;
import Entities.Produit;
import IServices.IServiceLigneCommande;
import IServices.IServiceProduit;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public class ServiceProduit implements IServiceProduit {
    IServiceLigneCommande ll;
        Connection cn = ConnexionBD.getinstance().getcnx();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<Produit> annonces = new ArrayList<Produit>();
    ArrayList<Produit> retour = new ArrayList<Produit>();
    ArrayList<Integer> Stat = new ArrayList<Integer>();
    

   
        @Override
    public void add(Produit a) {
        
        String req = "INSERT INTO produits ( nomProd,nomRef, img, prix, dispo, description, likes,views) VALUES (?,?,?,?,?,?,?,?)";
        try {
            pt = cn.prepareStatement(req);
          pt.setString(1, a.getNomProd());
            pt.setString(2, a.getNomRef());
                        pt.setString(3, a.getImg());
           pt.setDouble(4, a.getPrix());
      pt.setString(5, a.getDispo());
        pt.setString(6, a.getDescription());
        
            pt.setInt(7, a.getLikes());
           pt.setInt(8, a.getViews());
            pt.executeUpdate();
            System.out.println("ajout etablie");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

   
        @Override
    public void delete(int id) {
        try {
            String req = "delete from produits where idProd=?";
            pt = cn.prepareStatement(req);
            pt.setInt(1, id);
            pt.executeUpdate();
            System.out.println("Categorie  supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
        @Override
    public boolean update(Produit a, int id) {
        try {
            String req = "update produits SET nomProd= ? , nomRef=?, img =?, prix= ?, dispo =?, description= ? WHERE idProd=" + id;
            pt = cn.prepareStatement(req);
            pt.setString(1, a.getNomProd());
            pt.setString(2, a.getNomRef());
            pt.setString(3, a.getImg());
           pt.setDouble(4, a.getPrix());
      
            pt.setString(5, a.getDispo());
            pt.setString(6, a.getDescription());

       
            
            if (pt.executeUpdate() > 0) {
                System.out.println("update avce sucée");
                return true;
            }

            System.out.println("Categorie  modifiée !!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
        @Override
    public List<Produit> getall() {
        
             ll  =  new ServiceLigneCommande();
        String req = "SELECT a.*  from produits a ";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
                Produit a = new Produit();
                a.setIdProd(rs.getInt("a.idProd"));
               
               // a.setUser_id(rs.getInt("a.user_id"));
                a.setNomProd(rs.getString("a.nomProd"));
                a.setNomRef(rs.getString("a.nomRef"));
                a.setImg(rs.getString("a.img"));
                a.setPrix(rs.getDouble("a.prix"));
                a.setDispo(rs.getString("a.dispo"));
                              a.setDescription(rs.getString("a.description"));

//                a.setPhoto_updated_at(rs.getDate(12));
                a.setLikes(rs.getInt("a.likes"));
                a.setViews(rs.getInt("a.views"));
               // a.setLib(rs.getString("c.libelle"));
              //  a.setNomPrenom(rs.getString("nomUser"));
                if(ll.VerifProduit(a.getIdProd())==0)
                {
                 annonces.add(a);
                }
               
            }
            System.out.println("affichage etablie");
            return annonces;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    @Override
    public ResultSet afficherProduit() {
        ArrayList<Produit> psr = new ArrayList<>();
                  try {
              String req3 =
                      "select * from produit ";
              ResultSet res =   pt.executeQuery(req3);
              
              
             return res;
          } catch (SQLException ex) {
              Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return null;
    }
    
        @Override
    public Produit getProduitById(int id) {

        retour = (ArrayList<Produit>) getall();

        return retour.stream().filter(e -> e.getIdProd()== id).collect(Collectors.toList()).get(0);
    }

    /*
        @Override
    public List<Produit> trierParDate() {
        retour.removeAll(retour);
        retour = (ArrayList<Produit>) getall();
        return retour.stream().sorted((a, b) -> b.getDate_creation().compareTo(a.getDate_creation())).collect(Collectors.toList());
    }*/

 
        
    public List<Produit> trierParPrixASC() {
        retour.removeAll(retour);
        retour = (ArrayList<Produit>) getall();
       // return retour.stream().sorted((c,d) -> c.getPrix().compareTo(d.getPrix())).collect(Collectors.toList());
       return retour;
    }

    
    public List<Produit> trierParPrixDESC() {
        retour.removeAll(retour);
        retour = (ArrayList<Produit>) getall();
        
  //return retour.stream().sorted(( a, b) -> a.getPrix().compareTo(b.getPrix())).collect(Collectors.toList());
          // return retour.stream().sorted((a, b) -> b.getPrix().compareTo(a.getPrix())).collect(Collectors.toList());
return retour;
    }

   /*
        @Override
    public List<Produit> GetByUser() {
        retour.removeAll(retour);
        retour = (ArrayList<Produit>) getall();
        return retour.stream().filter(e -> e.getUser_id() == Session.getCurrentSession()).collect(Collectors.toList());
    }
*/
  
    
        @Override
    public boolean updateLikes(int id) {
        try {
            String requete = "UPDATE produits SET likes= likes+1 WHERE idProd=" + id;

            pt = cn.prepareStatement(requete);
            if (pt.executeUpdate() > 0) {
                System.out.println("update avce sucée");
                return true;
            }
            System.out.println("Categorie  modifiée !!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /*
  @Override
    public Produit getProduitById(int id) {

        retour = (ArrayList<Annonce>) getall();

        return retour.stream().filter(e -> e.getId() == id).collect(Collectors.toList()).get(0);
    }*/
    @Override
     public boolean updateViews(int id)
    {
        try {
            String requete = "UPDATE produits SET views= views+1 WHERE idProd=" + id;

            pt = cn.prepareStatement(requete);
            if (pt.executeUpdate() > 0) {
                System.out.println("update avce sucée");
                return true;
            }
            System.out.println("Categorie  modifiée !!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
        @Override
    public List<Produit> GetMostLikes() {
        retour.removeAll(retour);
        retour = (ArrayList<Produit>) getall();
        return retour.stream().filter(e -> e.getLikes() >= 5).collect(Collectors.toList());
    }


     
    public List<Produit> GetMostViwed() {
        retour.removeAll(retour);
        retour = (ArrayList<Produit>) getall();
        return retour.stream().filter(e -> e.getViews() >= 5).collect(Collectors.toList());
    }

        @Override
    public List<Integer> Stat() {
        String req = "Select year(date_creation),month(date_creation),count(*) from annonce group by year(date_creation),month(date_creation)";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {

                Stat.add(rs.getInt(3));
            }

            return Stat;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

   /*
        @Override
    public List<Produit> StatByCat() {

        String req = "SELECT c.libelle,COUNT(p.id) FROM annonce p, categorie_annonce c WHERE p.categorie_id = c.id GROUP BY categorie_id";
        try {
            ArrayList<Produit> aa = new ArrayList<Produit>();
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {

                Produit a = new Produit();
                a.setNomCat(rs.getString(1));
                a.setNb_cat(rs.getInt(2));
                aa.add(a);
            }

            return aa;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
*/
    /*
        
    public List<Produit> StatByMyProduits() {

        String req = "SELECT a.id from produits a   where  likes >=5  group by categorie_id";
        try {
            ArrayList<Produit> aa = new ArrayList<Produit>();
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {

                Produit a = new Produit();
                a.setNomCat(rs.getString(1));
                a.setNb_cat(rs.getInt(2));
                aa.add(a);
            }

            return aa;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   
   /* Connection cnx = DataBase.getInstance().getCnx();
    Statement st;
    PreparedStatement pt;
    ResultSet rs;
     
    @Override
     public void SupprimerProduit(int idProd) {
        String req = "delete from produits where idProd =?";
        try {
            pt = cnx.prepareStatement(req);
            pt.setInt(1, idProd);
            pt.executeUpdate();
          System.out.println("Suppression terminé avec succes ");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("not ok");
        }
    }


    @Override
     public void AjouterProduit( Produit p) {
       String requete=" INSERT INTO produits(idProd ,nomProd ,nomRef ,img ,prix  ) VALUES(?,?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setInt(1, p.getIdProd());
              pt.setString(2, p.getNomProd());
            pt.setString(3, p.getNomRef());
          
            pt.setString(4, p.getImg());
            pt.setDouble(5, p.getPrix());
          // pt.setString(5, p.getDispo());
            pt.executeUpdate();
            System.out.println("Produit Ajouté");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());  
        }
    }
   /*  List<Personne> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from personne");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
     arr.add(p);
     }
    return arr;*/
   
   
/*
    @Override
         public List<Produit> readAll() throws SQLException {
    List<Produit> arr=new ArrayList<>();
    st=cnx.createStatement();
    ResultSet rs=st.executeQuery("select * from produits ");
     while (rs.next()) {                
               int idProd=rs.getInt("idProd");
               String nomProd=rs.getString("nomProd");
               String nomRef=rs.getString("nomRef");
               String img=rs.getString("img");
               double prix=rs.getDouble("prix");
               String dispo=rs.getString("dispo");
               
              Produit p =new Produit(idProd ,nomProd ,nomRef ,img , prix ,dispo);
              
     arr.add(p);
     }
    return arr;
    }
       
       
       
 
    @Override
         public void ChangerEtatProduitToNonDisponible(int id) {
       try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE produits SET dispo = ? where idProd = ? ");

            ps.setString(1,"non Disponible");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }
    
    
    
    
    
    
    
    
    
    
    */
    
}
