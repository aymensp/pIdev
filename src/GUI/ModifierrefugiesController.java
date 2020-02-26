/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.Servicerefugies;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierrefugiesController implements Initializable {

    @FXML
    private ImageView re;
    @FXML
    private ImageView va;
    @FXML
    private TextField modifnom;
    @FXML
    private TextField modifprenom;
    @FXML
    private TextField modifage;
    @FXML
    private TextField modifpays;
    @FXML
    private Button retour; 
        @FXML
    private Button valider;

    public TextField getModifnom() {
        return modifnom;
    }

    public void setModifnom(TextField modifnom) {
        this.modifnom = modifnom;
    }

    public TextField getModifprenom() {
        return modifprenom;
    }

    public void setModifprenom(TextField modifprenom) {
        this.modifprenom = modifprenom;
    }

    public TextField getModifage() {
        return modifage;
    }

    public void setModifage(TextField modifage) {
        this.modifage = modifage;
    }

    public TextField getModifpays() {
        return modifpays;
    }

    public void setModifpays(TextField modifpays) {
        this.modifpays = modifpays;
    }

    public Button getRetour() {
        return retour;
    }

    public void setRetour(Button retour) {
        this.retour = retour;
    }

    public Button getValider() {
        return valider;
    }

    public void setValider(Button valider) {
        this.valider = valider;
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        modifnom.setText(AfficherrefugiesController.Recup.getNom());
        modifprenom.setText(AfficherrefugiesController.Recup.getPrenom()); 
        modifage.setText(String.valueOf(AfficherrefugiesController.Recup.getAge()));
        modifpays.setText(AfficherrefugiesController.Recup.getPays());
        
    }   
    @FXML
    private void validerModifrefugies(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier cet refugies ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if (action.get() == ButtonType.OK)
                            {Servicerefugies ac=new Servicerefugies();
        
      
        ac.modifierrefugies(AfficherrefugiesController.Recup.getIdRef(),modifnom.getText(), modifprenom.getText(), Integer.parseInt(modifage.getText()), modifpays.getText()); // insertion dans la base de données
          try {
 
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherrefugiesController.fxml"));
                                Parent root = loader.load();
                               AfficherrefugiesController rc = loader.getController();
                               modifnom.getScene().setRoot(root);
                               
             
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }
         
                            }
                            
        
        /*articleRecup=getTableView().getItems().get(getIndex());
                            
                          
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier l'article de l'ID " + articleRecup.getIdentifiant() + " et de Titre <" + articleRecup.getTitre() + "> ?");
                            Optional<ButtonType> action = alert.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                ArticleCrud ac = new ArticleCrud();
                                Article aUP=new Article();
                                 try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifArticle.fxml"));
                                Parent root = loader.load();
                               ModifArticleController rc = loader.getController();
                               btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }

                            }

        */
        
        
       
    }

    @FXML
    private void retourModif(ActionEvent event) {
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

    
    

