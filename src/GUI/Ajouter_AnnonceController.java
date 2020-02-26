/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Annonces;
import Entities.Session;
import Services.ServicesAnnonces;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malek Guemri
 */
public class Ajouter_AnnonceController implements Initializable {

    @FXML
    private TextField txAddresse;
    @FXML
    private TextArea txDesc;
    @FXML
    private Button btnEnvoyer;
     @FXML
    private Button retour;
    @FXML
    private TextField txType;
  public static final String ACCOUNT_SID
            = "AC6e8c8282242929e88dd5c3a24214f157";
    public static final String AUTH_TOKEN
            = "a3f8de1a4a2c809a0db1b95304a8f544";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerEvent(MouseEvent event) {
        ServicesAnnonces sa = new ServicesAnnonces();
        Annonces a = new Annonces(txAddresse.getText(), txType.getText(), txDesc.getText());
        sa.ajouterAnnonce(a);
       
        String num=Session.getCurrentSession().getNumero();
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message
                        .creator(new PhoneNumber("+216" + num), // to
                                new PhoneNumber("+15099564484"), // from
                                "Mr/Mme: " + Session.getCurrentSession().getNom() + " Tunfugees vous informe que votre annonce  a été envoyé avec succes"
                                + "Merci , Tunfugees est toujours la pour vous servir")
                        .create();

                System.out.println(message.getSid());
              

        
            
            
        
    }
     
    
}
