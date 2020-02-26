/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Entities.refugies;
import Services.Servicerefugies;
import Utils.ControlleSaisie;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterRefugiesController implements Initializable {
    @FXML
    private ImageView re;
    @FXML
    private ImageView co;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField pays;
    @FXML
    private Button ajouter;
     @FXML
    private Button retour;
    @FXML
    private ImageView imgv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void insertRefugies(ActionEvent event)
    {
       
            if (!(ControlleSaisie.estVide(nom, "nom "))
            && !(ControlleSaisie.estVide(prenom, "prenom "))
                     && !(ControlleSaisie.estVide(age, "age "))
                      && !(ControlleSaisie.estVide(pays, "pays ")))
            {  
        refugies r = new refugies();
        r.setNom(nom.getText());
        r.setPrenom(prenom.getText());
        r.setAge(Integer.parseInt(age.getText()));
        r.setPays(pays.getText());
        Servicerefugies sr=new Servicerefugies();
        sr.ajouterrefugies(r);
        
        
        
    }}

    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getPrenom() {
        return prenom;
    }

    public void setPrenom(TextField prenom) {
        this.prenom = prenom;
    }

    public TextField getAge() {
        return age;
    }

    public void setAge(TextField age) {
        this.age = age;
    }

    public TextField getPays() {
        return pays;
    }

    public void setPays(TextField pays) {
        this.pays = pays;
    }

    public Button getAjouter() {
        return ajouter;
    }

    public void setAjouter(Button ajouter) {
        this.ajouter = ajouter;
    }
        @FXML
    private void retourafficher(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherrefugies.fxml"));
            Parent root= loader.load();
            AfficherrefugiesController rc= loader.getController();            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }
    
    
}
