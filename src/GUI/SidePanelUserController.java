/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Services.UserService;
import com.jfoenix.controls.JFXButton;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class SidePanelUserController implements Initializable {
  //  private ILivraisonService livraisonService;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton dec;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

    @FXML
    private JFXButton b78;
        
    @FXML
    private JFXButton b99;
    @FXML
    private JFXButton b781;
    @FXML
    private Label user;

    private ChangeCallback callback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
        
        String nomUser=Session.getCurrentSession().getNom();
       
   
        user.setText("Utilisateur : "+ nomUser);
      //  System.out.println(UserService.());
        int id_u=Session.getCurrentSession().getId();
        System.out.println("id : "+id_u);
        
        /*
        if(livraisonService.RoleLivreur(id_u)==0)
        {
         b99.setDisable(false);
         
            //b99.setVisible(false);
        }else
        {
        b99.setDisable(true);
        
           b99.setVisible(true);
        }
        */
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
            case "Nos Evenements":
                callback.update("/GUI/FrontEvent.fxml"); //event
                break;
             case "Tunfugees Store":
                callback.update("/GUI/AllProduit.fxml"); //produit
                break;
            case "Faire Annonce":
                callback.update("/GUI/ajouter_Annonce.fxml"); //annonce
                break;
            case "Reclamation":
                callback.update("/GUI/AjoutReclamation.fxml"); //reclamer
                break;
             case "Faire un Don":
                callback.update("/GUI/Dons.fxml"); // faire un don
                break;
                case "Commandes":
                callback.update("/GUI/Commande.fxml");
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
