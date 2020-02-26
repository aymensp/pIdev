/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author aymen
 */
public class LigneCommande {
      private int id_commande;
    private int idProd;
    private int id_user;
    private double prix_Produit;
public LigneCommande(){
    
}

    public LigneCommande(int id_commande, int idProd, int id_user, double prix_Produit) {
        this.id_commande = id_commande;
        this.idProd = idProd;
        this.id_user = id_user;
        this.prix_Produit = prix_Produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

  

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public double getPrix_Produit() {
        return prix_Produit;
    }

    public void setPrix_Produit(double prix_Produit) {
        this.prix_Produit = prix_Produit;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id_commande=" + id_commande + ", idProd=" + idProd + ", id_user=" + id_user + ", prix_Produit=" + prix_Produit + '}';
    }

   

    

}
