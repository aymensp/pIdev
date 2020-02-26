/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Services.*;
import Entities.Commande;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author aymen
 */
public interface IServiceCommande {
   
    
   
     
    public void AjouterCommande(Commande c);
     public ObservableList<Commande> getall();
    public void AnnulerCommande(int id);
    public List<Commande> readAll() throws SQLException;
       public void ChangerEtatCommandeToPaye(int id);
      public void trieParDateASC();
       public Commande RecupererCommande( Commande c );
       public Commande RecupererCommandeClient2(int id);
        public ObservableList<Commande> RecupererCommandeClient(int id);
        public String RecupererNP_Utilisateur(int id);
     
       public int CommandeJanvier();
       public int CommandeFevrier();
       public int CommandeMars();
       public int CommandeAvril();
       public int CommandeMai();
       public int CommandeJuin();
       public int CommandeJuillet();
       public int CommandeAout();
       public int CommandeSeptembre();
       public int CommandeOctobre();
       public int CommandeNovembre();
       public int CommandeDecembre();
       
}
