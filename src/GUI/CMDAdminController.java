/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.Session;
import IServices.ISendEmailService;
import IServices.IServiceCommande;
import IServices.IServiceLigneCommande;
import IServices.IServicePanierProduit;
import Services.ServiceCommande;
import Services.ServiceLigneCommande;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class CMDAdminController implements Initializable {
    
    @FXML
    private BarChart<String,Integer> bar_chart;
    
    @FXML
    private BarChart<String,Integer> stat_liv;
    
 @FXML
    private Tab cond;

    @FXML
    private TableView<Commande> table_commande;

    @FXML
    private TableColumn<Commande,Date> date;

    @FXML
    private TableColumn<Commande, String> etat;

    @FXML
    private TableColumn<Commande,Double> prix_total;

    @FXML
    private TableColumn<Commande, Integer> id_user;
    
    @FXML
    private TextField motdepasse;

    @FXML
    private TextField confirmer_mdp;
    
     @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField pseudo;
@FXML
    private TextField zone;

@FXML
    private Button btnPhotoUser;
    

    @FXML
    private Button ajouter;
    
   
   @FXML
    private Button annuler_cmd;
   
   @FXML
    private Button annuler_livraison;
   
   
     private String absolutePathPhotoUser;
     @FXML
    private Text txtPhotoUser;
    @FXML
    private Button noter1;

    @FXML
    private Button noter2;

    @FXML
    private Button noter3;

    @FXML
    private Button noter4;

    @FXML
    private Button noter5;
    @FXML
    private Button meilleur_livreur;
    
     
    
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
        table_commande.setEditable(false);
        table_commande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        date.setCellValueFactory(new PropertyValueFactory<>("date_emission"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         prix_total.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
         id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         
        int id=Session.getCurrentSession().getId();
        table_commande.setItems(commandeService.getall());
       
       
       
        
        XYChart.Series<String,Integer> series=new XYChart.Series<>();
        
 int jan=commandeService.CommandeJanvier();
  int fev=commandeService.CommandeFevrier();
   int mar=commandeService.CommandeMars();
    int avr=commandeService.CommandeAvril();
     int mai=commandeService.CommandeMai();
      int juin=commandeService.CommandeJuin();
       int jui=commandeService.CommandeJuillet();
        int aou=commandeService.CommandeAout();
         int sep=commandeService.CommandeSeptembre();
          int oct=commandeService.CommandeOctobre();
           int nov=commandeService.CommandeNovembre();
            int dec=commandeService.CommandeDecembre();
  
 System.out.println("nb jan :"+jan);
 System.out.println("nb jan :"+jan);
        series.getData().add(new XYChart.Data<>("Janvier",jan));
        series.getData().add(new XYChart.Data<>("Février",fev));
        series.getData().add(new XYChart.Data<>("Mars",mar));
        series.getData().add(new XYChart.Data<>("Avril",avr));
        series.getData().add(new XYChart.Data<>("Mai",mai));
        series.getData().add(new XYChart.Data<>("Juin",juin));
        series.getData().add(new XYChart.Data<>("Juillet",jui));
        series.getData().add(new XYChart.Data<>("Août",aou));
        series.getData().add(new XYChart.Data<>("Spetembre",sep));
        series.getData().add(new XYChart.Data<>("Octobre",oct));
        series.getData().add(new XYChart.Data<>("Nomvembre",nov));
        series.getData().add(new XYChart.Data<>("Décembre",dec));
                          bar_chart.getData().add(series);
       bar_chart.setTitle("Nombres de Commandes Par rapport au mois de l'année 2020 ");
       
    
   
     
             
     
        
      
    }    
  
    @FXML
    void Annuler_Commande(ActionEvent event) {
commandeService = new ServiceCommande();
lignecommandeService = new ServiceLigneCommande();
if (table_commande.getSelectionModel().getSelectedIndex() == -1)
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

                ObservableList<Commande> selectedRows = table_commande.getSelectionModel().getSelectedItems();
                ArrayList<Commande> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   lignecommandeService.AnnulerLigneCommande(row.getId()); 
                    commandeService.AnnulerCommande(row.getId());
                    table_commande.getItems().remove(row);
                });

            }
            
            else {

            }

        }
    }
    
    
  

  
   
    
}
