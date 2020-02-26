/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Annonces;
import Entities.Sponsors;
import Services.ServicesAnnonces;
import Services.ServicesSponsors;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Malek Guemri
 */
public class Ajouter_SponsorController implements Initializable {

    @FXML
    private Label lbNom;
    @FXML
    private Label lbAdresse;
    @FXML
    private Label lbType;
    @FXML
    private Label lbNumTel;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtNumTel;
    @FXML
    private Button btnAjouter;
    @FXML
    private ChoiceBox<String> comboType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> type = FXCollections.observableArrayList();
        type.addAll("Entreprise","Association");
        comboType.getItems().addAll(type);
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        ServicesSponsors ss = new ServicesSponsors();
        Sponsors s = new Sponsors(1,txtNom.getText(), txtAdresse.getText(), comboType.getValue(),txtNumTel.getText());
        ss.ajouterSponsors(s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/afficher_Sponsors.fxml"));
        try {
            Parent root = loader.load();
            Afficher_SponsorsController c = loader.getController();
            btnAjouter.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Ajouter_SponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
