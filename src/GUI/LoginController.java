/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Session;
import Entities.Utilisateur;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author AMINE
 */


public class LoginController implements Initializable {

    @FXML
    private AnchorPane Username;
    @FXML
    private TextField txt_user_name;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button inscrire;
    @FXML
    private ImageView imageview;
    
 Stage dialogStage = new Stage();
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Image image=new Image("/Image/logo.png");
        imageview.setImage(image);  
        
    }

    @FXML
    void connexionUtilisateur(ActionEvent event) throws SQLException, IOException, Exception {
        UserService us = new UserService();
        Utilisateur u = new Utilisateur();
        u.setEmail(txt_user_name.getText());
        u.setPassword(txt_password.getText());
        u.setEnabled(u.isEnabled());
        
        if (us.login(u) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("user inccorrect");
            alert.setContentText("please check your informations!");
            alert.showAndWait();
        } else  {
            Session.start(u);
            System.out.println(Session.getCurrentSession().getId());
        
            System.out.println(Session.getCurrentSession().getPassword());
            System.out.println(Session.getCurrentSession().getEmail());
               System.out.println(Session.getCurrentSession().getNom());
            System.out.println(Session.getCurrentSession().getNumero());
            System.out.println(Session.getCurrentSession().getRole());
           
            
         
            

            if (us.login(u) != null ) {
                
                        
                System.out.println("5deeeem");
                if (us.login(u).getRole().contains("Admin")) {
                    Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/mainadminscreen.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();
                   System.out.println("ADMIN CONNECTE");
                } else {
                    Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/mainuserscreen.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Passage User");
                    dialogStage.show(); 
                }

            }

        }}
    
        @FXML
    void goToInscription(ActionEvent event) throws SQLException, IOException, Exception {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/inscription.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();   
    
    
    
    

}
}
