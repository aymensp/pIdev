/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Services.*;
import Entities.PanierProduit;
import Entities.Produit;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public interface IServicePanierProduit {
      public Produit RecupererProduit(int id);
    public int existProduit(int id);
    public void AjouterAuPanier(Produit a);
    public void SupprimerDuPanier(int id);
    
    public int LonguerPanier();
   public ObservableList<PanierProduit> getall();
   
}
