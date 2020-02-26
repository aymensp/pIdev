/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Donneur;
import Entities.Session;
import Services.ServiceDonneur;
import Utils.ControlleSaisie;
import Utils.JavaMail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
//import utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DonsController implements Initializable {

    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField mail;
    @FXML
    private TextField don;
    @FXML
    private PasswordField numcarte;
     @FXML
    private Button ajouter;
     @FXML
    private Button retour;
     @FXML
    private ImageView re ;
     @FXML
    private ImageView aj ;

    public TextField getCin() {
        return cin;
    }

    public void setCin(TextField cin) {
        this.cin = cin;
    }

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

    public TextField getMail() {
        return mail;
    }

    public void setMail(TextField mail) {
        this.mail = mail;
    }

    public TextField getDon() {
        return don;
    }

    public void setDon(TextField don) {
        this.don = don;
    }


    public Button getAjouter() {
        return ajouter;
    }

    public void setAjouter(Button ajouter) {
        this.ajouter = ajouter;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nom.setText(Session.getCurrentSession().getNom());
         nom.setEditable(false);
       prenom.setText(Session.getCurrentSession().getPrenom());
       prenom.setEditable(false);
       mail.setText(Session.getCurrentSession().getEmail());
       mail.setEditable(false);
    }  
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();                
                if (txt_TextField.getText().length() >= max_Lengh) {                    
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){ 
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume(); 
                    }
                }else{
                    e.consume();
                }
            }
        };
    }      
    public void insertDonneur(ActionEvent event)
    { 
        if ( !(ControlleSaisie.estVide(cin, "numero de carte d'identite")) 
           
    
            && !(ControlleSaisie.estVide(numcarte, "numero de carte "))
            && !(ControlleSaisie.estVide(don, " dons"))
            )
        {
        
        try {
            Donneur r = new Donneur();
            cin.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8)); 
           // cin.addEventFilter(KeyEvent.KEY_TYPED ,numeric);
            r.setCin(Integer.parseInt(cin.getText()));
       
 r.setNom(Session.getCurrentSession().getNom());
       r.setPrenom(Session.getCurrentSession().getPrenom());
            r.setMail(Session.getCurrentSession().getEmail());
            r.setDon(Integer.parseInt(don.getText()));
            r.setNumcarte(Integer.parseInt(numcarte.getText()));
            
            ServiceDonneur sr=new ServiceDonneur();
             
            sr.ajouterdonneur(r);
            JavaMail.sendMail(r.getMail());
          
            
           try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainuserscreen.fxml"));
            Parent root= loader.load();
            MainuserscreenController rc= loader.getController();            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        } catch (Exception ex) {
            Logger.getLogger(DonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }}
                   @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainuserscreen.fxml"));
            Parent root= loader.load();
            MainuserscreenController rc= loader.getController();            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
