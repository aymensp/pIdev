/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class SidePanelController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;
    
    @FXML
    private JFXButton b3111;
      
    @FXML
    private Label user;

    private ChangeCallback callback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  String nomUser=UserService.getTtUtilisateur().stream().filter(e -> e.getId() == Session.getCurrentSession()).findFirst().get().getUsername();
       
      //  user.setText("Utilisateur : "+ nomUser);
      
        
        String nomUser=Session.getCurrentSession().getNom();
       
   
        user.setText("Utilisateur : "+ nomUser);
      //  System.out.println(UserService.());
        int id_u=Session.getCurrentSession().getId();
        System.out.println("id : "+id_u);


    }

    public void setCallback(ChangeCallback callback) {
        this.callback = callback;
    }

    @FXML
    // a mettre les SRC
    private void navigate(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getText()) {
            case "Acceuil":
                callback.update("");
                break;
            case "Gestion Des Refugies":
                callback.update("/GUI/afficherrefugies.fxml");
                break;
             case "Gestion Des Donneurs":
                callback.update("/GUI/afficherdonneurs.fxml");
                break;
             case "Gestion Des Evénements":
                callback.update("/GUI/ajouterevent.fxml");
                break;
                 case "Gestion Des Sponsors":
                callback.update("/GUI/ajouter_Sponsor.fxml");
                break;
             case "Gestion Des Volantaires":
                callback.update("/GUI/Volontaires.fxml");
                break;
             case "Gestion Des Camps":
                callback.update("/GUI/Campsmodifier.fxml");
                break;
                case "Gestion De Stock":
                callback.update("/GUI/ModifierStock.fxml");
                break;
                
             case "Gestion Des Utilisateurs":
                callback.update("/GUI/utilisateur.fxml");
                break;
             case "Gestion Des Reclamations":
                callback.update("/GUI/BackReclamtion.fxml");
                break;
             case "Gestion Des Commandes":
                callback.update("/GUI/CMDAdmin.fxml");
                break;
                 case "Gestion Des Produits":
                callback.update("/GUI/ProduitAdmin.fxml");
                break;
         
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
     @FXML
    // a mettre les SRC
    private void deco(ActionEvent event) {
                try
        {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/GUI/Login.fxml"));
              Stage s = (Stage) b2.getScene().getWindow();
            s.close();
            

            
            try {
                Loader.load();
            } catch (IOException e) {
                System.out.println(e);
            }
            LoginController c = Loader.getController();
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            event.consume();
            Session.setCurrentSessionToNull();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
        
    
    } 
}
