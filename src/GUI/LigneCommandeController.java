/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.ServiceProduit;
import Entities.Produit;
import Entities.LigneCommande;
import IServices.IServiceLigneCommande;
import IServices.IServicePanierProduit;
import IServices.IServiceProduit;
import Services.ServiceLigneCommande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class LigneCommandeController implements Initializable {
@FXML
    private Button fermer;
    
    @FXML
    private TableView<Produit> detail_commande;

    @FXML
    private TableColumn<Produit,ImageView> photo;

    @FXML
    private TableColumn<Produit,String> titre;

    @FXML
    private TableColumn<Produit,String> description;

    @FXML
    private TableColumn<Produit,Double> prix;

    
      private IServicePanierProduit panierService;
    private IServiceProduit annonceService;
    private IServiceLigneCommande lignecommandeService;
    
    @FXML
    void Fermer(ActionEvent event) {
Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
           lignecommandeService = new ServiceLigneCommande();
      
         
     
     
    }    
    public void initData(Commande c)
    {
       int idc=c.getId();
       lignecommandeService = new ServiceLigneCommande();
        System.out.println("id commande : ");
       System.out.println(idc);
    ObservableList<LigneCommande> l = FXCollections.observableArrayList();
    ObservableList<Produit> all_a = FXCollections.observableArrayList();
    l=lignecommandeService.RecupererLignesCommande(idc);
    for(int i=0;i<l.size();i++)
    {
        int id8=l.get(i).getIdProd();
        System.out.println("id ligne ");
        System.out.println(l.get(i).getIdProd());

        Produit a=new Produit();
       
    a=lignecommandeService.RecupA(id8);
    System.out.println("Nom du Produit a : "+a.getNomProd());
    all_a.add(a);
    }
   
    /*
     for(int i=0;i<all_a.size();i++)
    {
        System.out.println(all_a.get(i).getNomProd());
        System.out.println(all_a.get(i).getDescription());
        System.out.println(all_a.get(i).getPrix());
    }*/
       detail_commande.setEditable(false);
       detail_commande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       titre.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         photo.setCellValueFactory(p -> new SimpleObjectProperty<>(new ImageView(p.getValue().getImage())));
        detail_commande.setItems(all_a);
      
    }
    
}
