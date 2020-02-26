/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.PanierProduit;
import Entities.Session;
import IServices.ISendEmailService;
import IServices.IServiceCommande;
import IServices.IServiceLigneCommande;
import IServices.IServicePanierProduit;
import Services.ServiceCommande;
import Services.ServiceLigneCommande;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;
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
import javafx.stage.Stage;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class CommandeController implements Initializable {
@FXML
    private TableView<Commande> tableview_commande;
    @FXML
    private TableColumn<Commande,Date> date_emission;

    @FXML
    private TableColumn<Commande,String> etat;

    @FXML
    private Label nom_prenom_u;
    @FXML
    private Button afficher_details;
 
    @FXML
    private TableColumn<Commande,Double> prixtotal;
   @FXML
    private Button annuler_commande;
   

   public ObservableList<PanierProduit>  tab;
      @FXML
    private Button payer;
      public static int date_plus=3;

private IServicePanierProduit panierService;
    private IServiceCommande commandeService;
     private IServiceLigneCommande lignecommandeService;
    
       private ISendEmailService sendEmailService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         commandeService = new ServiceCommande();
        tableview_commande.setEditable(false);
        tableview_commande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        date_emission.setCellValueFactory(new PropertyValueFactory<>("date_emission"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         prixtotal.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        int id=Session.getCurrentSession().getId();
        
        Commande c = new Commande();
        c=commandeService.RecupererCommandeClient2(id);
        
        int id2=c.getId_user();
        String n=Session.getCurrentSession().getNom();
       // String n2=commandeService.RecupererNP_Utilisateur(id2);
        System.out.println("name :"+n);
        nom_prenom_u.setText(n);
        
        tableview_commande.setItems(commandeService.RecupererCommandeClient(id));
    }    
    
    @FXML
    void Annuler_Commande(ActionEvent event) {
commandeService = new ServiceCommande();
lignecommandeService = new ServiceLigneCommande();
if (tableview_commande.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne  sélectionnée ");
            alert.showAndWait();
        } 
else 
{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de Suppression");
            alert.setHeaderText("Voulez vous supprimer les commandes suivantes  ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<Commande> selectedRows = tableview_commande.getSelectionModel().getSelectedItems();
                ArrayList<Commande> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   lignecommandeService.AnnulerLigneCommande(row.getId()); 
                    commandeService.AnnulerCommande(row.getId());
                    tableview_commande.getItems().remove(row);
                });

            }
            
            else {

            }

        }
    }
    
    @FXML
    void AfficherDetails(ActionEvent event) {
   
      System.out.println("Détails commande");
      
      if (tableview_commande.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionnée ! ");

            alert.showAndWait();
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("LigneCommande.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              LigneCommandeController controller = fxmlLoader.getController();
                controller.initData(tableview_commande.getSelectionModel().getSelectedItem());
            
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
    

    
    
    @FXML
    void Payer(ActionEvent event) {
System.out.println("Détails commande");
      
      if (tableview_commande.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionnée ! ");

            alert.showAndWait();
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Payer.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              PayerController controller = fxmlLoader.getController();
                controller.initData(tableview_commande.getSelectionModel().getSelectedItem());
            
                stage.setTitle("Payement");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to open payement window .", e);
            }
        }
    }
    
}
