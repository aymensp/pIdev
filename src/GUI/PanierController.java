/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Commande;
import Entities.LigneCommande;
import Entities.PanierProduit;
import Entities.Session;
import IServices.IServiceCommande;
import Services.ServiceCommande;
import IServices.IServiceLigneCommande;
import IServices.IServicePanierProduit;
import IServices.IServiceProduit;
import Services.ServiceCommande;
import Services.ServiceLigneCommande;
import Services.ServicePanierProduit;
import Services.ServiceProduit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class PanierController implements Initializable {

    
    @FXML
    private TableView<PanierProduit> tableview_panier;

    @FXML
    private TableColumn<PanierProduit,ImageView> photo;
@FXML
    private Button ajouter_commande;
    @FXML
    private TableColumn<PanierProduit, String> titre;

    @FXML
    private TableColumn<PanierProduit, String> description;

    @FXML
    private TableColumn<PanierProduit, Double> prix;
    
     @FXML
    private Button supprimer;
     
     @FXML
    private Label nb_article;
     
    private IServicePanierProduit panierService;
    private IServiceProduit annonceService;
    private IServiceCommande commandeService;
     private IServiceLigneCommande lignecommandeService;
    @FXML
    private Label prix_total;
    
     public static int x=0;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button vider;
    @FXML
    private ImageView affichage_panier;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        panierService = new ServicePanierProduit();
        tableview_panier.setEditable(false);
        tableview_panier.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       photo.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
        //photo.setCellValueFactory(c -> new SimpleObjectProperty<>(new Image("C:\\ecosystemjava\\src\\res\\ajoutpanier.png")));
        titre.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        description.setCellValueFactory(new PropertyValueFactory<>("nomRef"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableview_panier.setItems(panierService.getall());
        double nbr_total=0;
        
       ObservableList<PanierProduit> selectedRows = tableview_panier.getItems();
                ArrayList<PanierProduit> rows = new ArrayList<>(selectedRows);
               for(int i = 0 ; i < rows.size(); i++)
               {
               nbr_total=nbr_total+(rows.get(i).getPrix());
               }
   prix_total.setText(Double.toString(nbr_total)+"DT");
   
     panierService = new ServicePanierProduit();
        int nbr=panierService.LonguerPanier();
        nb_article.setText("("+nbr+")");
            
        
       
        
    } 
     @FXML
    void Supprimer_AnnonceP(ActionEvent event) {
        double nbr_total=0;
        panierService = new ServicePanierProduit();
if (tableview_panier.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne  sélectionnée ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de Suppression");
            alert.setHeaderText("Voulez vous supprimer les annonces suivantes de votre Panier ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<PanierProduit> selectedRows = tableview_panier.getSelectionModel().getSelectedItems();
                ArrayList<PanierProduit> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                    panierService.SupprimerDuPanier(row.getId());
                    tableview_panier.getItems().remove(row);
                });

            } else {

            }

        }
  panierService = new ServicePanierProduit();
        int nbr=panierService.LonguerPanier();
        nb_article.setText("("+nbr+")");
        
        ObservableList<PanierProduit> selectedRows = tableview_panier.getItems();
                ArrayList<PanierProduit> rows = new ArrayList<>(selectedRows);
               for(int i = 0 ; i < rows.size(); i++)
               {
               nbr_total=nbr_total+(rows.get(i).getPrix());
               }
   prix_total.setText(Double.toString(nbr_total)+"DT");
    }
    
     @FXML
    void ViderPanier(ActionEvent event) {
        double nbr_total=0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vider Panier");
            alert.setHeaderText("Voulez vous videz votre panier? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ObservableList<PanierProduit> selectedRows = tableview_panier.getItems();
                ArrayList<PanierProduit> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                    panierService.SupprimerDuPanier(row.getId());
                    tableview_panier.getItems().remove(row);
                });
              panierService = new ServicePanierProduit();
        int nbr=panierService.LonguerPanier();
        nb_article.setText("("+nbr+")");    
        
               for(int i = 0 ; i < rows.size(); i++)
               {
               nbr_total=nbr_total+(rows.get(i).getPrix());
               }
   prix_total.setText(Double.toString(nbr_total)+"DT");
        
                
    }
    
    
    
    }
    
     @FXML
    void ajout_cmd(ActionEvent event) {
Commande c =new Commande();

Date d=Date.valueOf(LocalDate.now());
c.setDate_emission(d);
c.setEtat_commande("En cours");
double nbr_total=0;
ObservableList<PanierProduit> selectedRows = tableview_panier.getItems();
                ArrayList<PanierProduit> rows = new ArrayList<>(selectedRows);
               for(int i = 0 ; i < rows.size(); i++)
               {
               nbr_total=nbr_total+(rows.get(i).getPrix());
               }
 c.setPrixTotal(nbr_total);
c.setId_user(Session.getCurrentSession().getId());
commandeService = new ServiceCommande();
annonceService = new ServiceProduit();
lignecommandeService = new ServiceLigneCommande();
commandeService.AjouterCommande(c);
         
 Commande c2=new Commande();
 c2=commandeService.RecupererCommande(c);
 int id_c=c2.getId();
         System.out.println(id_c);
                rows.forEach((row) -> {
                    LigneCommande l =new LigneCommande();
                  
                   
                    l.setIdProd(Integer.parseInt(row.getIdProd()));
                    l.setId_commande(id_c);
                    l.setId_user(Session.getCurrentSession().getId());
                    l.setPrix_Produit(row.getPrix());
                    lignecommandeService.AjouterLigneCommande(l);  
                     System.out.println("Ligne Ajouté");
                   // historique.add(row);
                     panierService.SupprimerDuPanier(row.getId());
                  //  annonceService.delete(Integer.parseInt(row.getId_annonce()));
                      System.out.println("Produit supprimé du panier");
                });
                
 System.out.println("Commande ajouté");
 
 Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation de Commande!");
            alert.setHeaderText(null);
            alert.setContentText("Votre Commande est Confirmé Avec un Prix Total de :"+nbr_total+" DT ");
            alert.showAndWait();
  try {
               FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/Commande.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
           
            
                stage.setTitle("Edition d'une announce de réparation");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                
                
                
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
    
    
    
}
 