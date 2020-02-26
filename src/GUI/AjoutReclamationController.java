/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Session;
import Entities.reclamation;
import Services.servicereclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */

public class AjoutReclamationController implements Initializable {

    @FXML
    private Button idajouter;
    @FXML
    private TextField mail;
    @FXML
   
    private TextField redaction;
    
    @FXML
    private Button retour;
     @FXML
    private ChoiceBox<String> comboType;
    @FXML
    private TextField type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ObservableList<String> type = FXCollections.observableArrayList();

        type.addAll("Urgent","Normale");
        comboType.getItems().addAll(type);
    }    

    @FXML
    private void AjouterReclamtionAction(ActionEvent event) {
         String Mail=Session.getCurrentSession().getEmail();
         
        String Combo;
         Combo = comboType.getValue();

        String Description=redaction.getText();
     //iddates.getValue();
        
       // String cb=String.valueOf(cbitems.getItems());
       
       
       // Date.valueOf( dateformat.format(iddates.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000));
       
       servicereclamation srv1 = new servicereclamation();
        reclamation c;
        c = new reclamation (0,Mail,Combo,Description,0);
        srv1.ajouterReclamation(c);
    }
    
    
       
    @FXML
    public void directAccueilRec(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FrontReclamations.fxml"));
            Parent root= loader.load();
            FrontReclamationController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @FXML
    private void directAccueil(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainuserscreen.fxml"));
            Parent root= loader.load();
            MainuserscreenController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    
}
    
    

