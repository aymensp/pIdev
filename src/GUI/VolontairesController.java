/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Volontaire;
import Services.ServiceEvent;


import Services.ServiceVolontaire;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VolontairesController implements Initializable {
@FXML 
 private Button retour;

@FXML 
private TableView tab;

@FXML
private TableColumn <Volontaire,String> nom;
@FXML
private TableColumn <Volontaire,String> prenom;
@FXML
private TableColumn <Volontaire,String> mail;
@FXML
private TableColumn <Volontaire,Integer> tel;
@FXML
private TableColumn <Volontaire,String> nomev;
@FXML
private TableColumn <Volontaire,String> presence;
@FXML
private TextField recherche ;

@FXML
        private TextField nomm;
@FXML
        private TextField nomevent;
@FXML 
        ComboBox<String> combo;
    ObservableList<String> list=FXCollections.observableArrayList("Présent","Absent");

    /**
     * Initializes the controller class.
     */
     
     ObservableList<Volontaire> dataVolontaire ;
    @FXML
    private Button supprimer;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        tel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        nomev.setCellValueFactory(new PropertyValueFactory<>("Nom_event"));
        presence.setCellValueFactory(new PropertyValueFactory<>("Presence"));
        ServiceVolontaire srv1 =new ServiceVolontaire();
       
        dataVolontaire =FXCollections.observableArrayList(srv1.afficherVolontaire());

       
        
       
        tab.setItems(dataVolontaire);
        setCellValue();
        combo.setItems(list);
        
      
    }    
    public void testAff(ActionEvent event)
     {
       
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        tel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        nomev.setCellValueFactory(new PropertyValueFactory<>("Nom_event"));
        presence.setCellValueFactory(new PropertyValueFactory<>("Presence"));
        ServiceVolontaire srv1 =new ServiceVolontaire();
       
        dataVolontaire =FXCollections.observableArrayList(srv1.afficherVolontaire());

       
        
       
        tab.setItems(dataVolontaire);
        
 
    }
    @FXML
     public void directAccueil(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainadminscreen.fxml"));
            Parent root= loader.load();
            MainAdminScreenController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
      public void recher(ActionEvent event) {
         
         
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        nomev.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        presence.setCellValueFactory(new PropertyValueFactory<>("presence"));
        ServiceVolontaire srv =new ServiceVolontaire();
        
        
        dataVolontaire=FXCollections.observableArrayList(srv.rechercherVolontaire(recherche.getText()));
        
        tab.setItems(dataVolontaire);
  

   
}
public void setCellValue ()
  { 
      tab.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Volontaire v;
              v = (Volontaire) tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
              nomm.setText(v.getMail());
              nomm.setEditable(false);
              nomevent.setText(v.getNom_event());
              nomevent.setEditable(false);
              combo.setValue((v.getPresence()));          
          }
          
          
      });
  
  }
public int getid()
  {
       ServiceVolontaire srv =new ServiceVolontaire();
        int x  = srv.getId(nomm.getText());   
        return x;

  }
    @FXML 
    public void validerModif(ActionEvent event) 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier ce volontaire ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceVolontaire srv=new ServiceVolontaire();
                            
                            System.out.println(getid());
                                
                               
                                
                                        
                             
              srv.modifierVolontaire(combo.getValue(),getid());
                                
          }testAff(event);
          
                    }
    @FXML 
    public void validersupp(ActionEvent event) 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer cet article ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceVolontaire srv=new ServiceVolontaire();
                            
                            
                                srv.supprimervolontaire(nomm.getText(),nomevent.getText());
                               
                                
                                        
                             
                                
          }testAff(event);
          
                    }
  
  


}
