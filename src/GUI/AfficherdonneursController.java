/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Donneur;
import Services.ServiceDonneur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherdonneursController implements Initializable {
    @FXML
    private ImageView somme; 
    @FXML
    private ImageView supp; 
    @FXML
    private ImageView re; 
    @FXML
    private TextArea sommedesdons; 
    @FXML
    private Button retour;  
   
       @FXML
    private TableColumn<Donneur, Integer> cin;
       @FXML
    private TableColumn<Donneur, String> nom;
       @FXML
    private TableColumn<Donneur, String> prenom;
       @FXML
    private TableColumn<Donneur, Integer> don;
       @FXML
    private TableColumn<Donneur, Integer> numcarte;
       @FXML
    private TableColumn<Donneur, String> mail;
       @FXML
    private TableView tabledonneur;
      @FXML 
              private Button supprimer;

       
       
ObservableList<Donneur> donneurlist ;
       
       
       
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
 
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        don.setCellValueFactory(new PropertyValueFactory<>("don"));
       numcarte.setCellValueFactory(new PropertyValueFactory<>("numcarte"));
        
          
         //addButtonUpdateToTable();
        
               // refugies a=new refugies();

        ServiceDonneur ac =new ServiceDonneur();
        donneurlist=FXCollections.observableArrayList(ac.afficherdonneur());
      
        tabledonneur.setItems(donneurlist);
        sommedesdons.setText(String.valueOf(ac.sommesdons()+"DT"));

        
      
    }    
   
    private void testAff(ActionEvent event) {
        
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        don.setCellValueFactory(new PropertyValueFactory<>("don"));
       numcarte.setCellValueFactory(new PropertyValueFactory<>("numcarte"));
        
        
        
        
       
        //refugies a=new refugies();
        ServiceDonneur ac =new ServiceDonneur();
        
  //   refugieslist=FXCollections.observableArrayList(ac.(tfAuteurRechercher.getText()));

donneurlist=FXCollections.observableArrayList(ac.afficherdonneur());        
        tabledonneur.setItems(donneurlist);
                sommedesdons.setText(String.valueOf(ac.sommesdons()+"DT"));

       
        
        
         
        

    

   
}
    @FXML
    public void validersupp(ActionEvent event) 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer ce Donneur ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceDonneur srv=new ServiceDonneur();
                            
                            tabledonneur.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Donneur v;
              v = (Donneur) tabledonneur.getItems().get(tabledonneur.getSelectionModel().getSelectedIndex());
                                
                     srv.supprimerdonneur(v.getNom());           
    }
                           
          });
                          
                                    }
          
                    

testAff(event);  }
            @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainadminscreen.fxml"));
            Parent root= loader.load();
            MainAdminScreenController rc= loader.getController();            
            retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }

    @FXML
    private void testAff(KeyEvent event) {
    }
}
