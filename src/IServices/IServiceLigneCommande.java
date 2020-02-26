/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Services.*;
import Entities.LigneCommande;
import Entities.PanierProduit;
import Entities.Produit;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public interface IServiceLigneCommande {
       public ObservableList<PanierProduit> RecupererLignesCommande2(int id,ObservableList<PanierProduit> A);
    public Produit RecupA(int id);
    public ObservableList<LigneCommande> RecupererLignesCommande(int id);
    public int VerifProduit(int id);
    //public void AddToHistrique(AnnoncePanier a);
   // public ObservableList<AnnoncePanier> getHistorique();
    public void AjouterLigneCommande(LigneCommande c);
    public void AnnulerLigneCommande(int id);
    public ObservableList<LigneCommande> getall();
    
}
