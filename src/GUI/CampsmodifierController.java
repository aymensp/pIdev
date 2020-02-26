/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Camps;
import Services.ServiceCamp;
import doryan.windowsnotificationapi.fr.Notification;

import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AMINE
 */
public class CampsmodifierController implements Initializable {

    @FXML
    private TableView<Camps> idtable;
    @FXML
    private TableColumn<Camps, String> clidcamp;
    @FXML
    private TableColumn<Camps, String> clnomcamp;
    @FXML
    private TableColumn<Camps, String> clnbrmax;
    @FXML
    private TableColumn<Camps, String> clAdresse;
    @FXML
    private TextField txtIdcamp;
    @FXML
    private TextField txtnomCamp;
    @FXML
    private TextField txtnbrmax;
    @FXML
    private Text nomc;
    @FXML
    private Text nbrmax;
    @FXML
    private Text adresse;
    @FXML
    private TextField txtadresse;
    @FXML
    private Text idc;
    @FXML
    private Button btn;
    @FXML
    private Button supprimer;
    @FXML
    private Button vider;
    @FXML
    private Button retour;
    @FXML
    private Button btnAjouter;
    
    
    ObservableList<Camps> ob1 ; 
    Stage dialogStage = new Stage();
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       clidcamp.setCellValueFactory(new PropertyValueFactory<>("idCamp"));
       clnomcamp.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        clnbrmax.setCellValueFactory(new PropertyValueFactory<>("nbrmax"));
    
        clAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ServiceCamp srv =new ServiceCamp();
        ob1 =FXCollections.observableArrayList(srv.getCamp());
        idtable.setItems(ob1); 
    
    
   
       setCellValue();
    }    
    public void afficherAllCamp(ActionEvent event) throws SQLException, IOException, Exception{
        clidcamp.setCellValueFactory(new PropertyValueFactory<>("idCamp"));
        clnomcamp.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        clnbrmax.setCellValueFactory(new PropertyValueFactory<>("nbrmax"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ServiceCamp srv =new ServiceCamp();
        ob1 =FXCollections.observableArrayList(srv.getCamp());
        idtable.setItems(ob1); 
    }
    @FXML
    public void insertdata (ActionEvent event) throws IOException, Exception
    {
       try {
           String  idCamp=txtIdcamp.getText() ;
           String nbrmax =txtnbrmax.getText();
           String nomCamp=txtnomCamp.getText();
           
           String adresseC =txtadresse.getText();
           
           
           Camps evt = new Camps();
           evt.setIdCamp(idCamp);
           evt.setNbrmax(nbrmax);
           evt.setNomCamp(nomCamp);
           
           evt.setAdresse(adresseC);
         
           ServiceCamp srv =new ServiceCamp();
           srv.ajouterCamps(evt);
           afficherAllCamp(event);
       } catch (MalformedURLException ex) {
           Logger.getLogger(CampsmodifierController.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }
    
    @FXML
    private void validerModif(ActionEvent event) throws IOException, Exception {
        
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier cet article ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceCamp srv=new ServiceCamp();
                            
                            
                               
                                
                                        
                             
              srv.updateCamp(txtIdcamp.getText(),txtnomCamp.getText(), txtnbrmax.getText(),txtadresse.getText());
                                
          }afficherAllCamp(event);
          
                    
       
    }

    @FXML
    private void validersupp(ActionEvent event)  throws SQLException, IOException, Exception{
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer ce camp ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceCamp srv=new ServiceCamp();
                            
                            
                                srv.SupprimerCamp(txtnomCamp.getText());
                               
                                
                                        
                             
                                
          }afficherAllCamp(event);
          
    }

    @FXML
    private void vider(ActionEvent event) {
   
   
        txtIdcamp.clear();
        txtnomCamp.clear();
        txtnbrmax.clear();
        txtadresse.clear();
        
        

    }

    @FXML
    private void directAccueil(ActionEvent event) {
    }
    
 public void setCellValue ()
  {
        
      idtable.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Camps e= idtable.getItems().get(idtable.getSelectionModel().getSelectedIndex());
              txtIdcamp.setText(e.getIdCamp());
              txtnomCamp.setText(e.getNomCamp());
              txtnbrmax.setText(e.getNbrmax());
              txtadresse.setText(e.getAdresse());
              
          }
          
          
      });
  
  }
}
