/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;


import Services.*;
import Entities.Produit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public interface IServiceProduit {
     public void add(Produit a);

    public void delete(int id);

    public boolean update(Produit a, int id);

    public boolean updateLikes(int id);
 public boolean updateViews(int id);
  

    public List<Produit> getall();

    public Produit getProduitById(int id);
  public ResultSet afficherProduit();
    /*

    public List<Produit> trierParPrixASC();

    public List<Produit> trierParPrixDESC();

   */



    public List<Produit> GetMostLikes();
    //public List<Produit> GetMostViewd();


    public List<Integer> Stat();

    //public List<Produit> StatByMyProduits();
    
    
 /*
    public void AjouterProduit(Produit p);
    public void SupprimerProduit(int id);
    public List<Produit> readAll() throws SQLException;
       public void ChangerEtatProduitToNonDisponible(int id);
     */
      
}
