 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.Session;
import IServices.ISendEmailService;
import IServices.IServiceCommande;
import Services.SendEmailService;
import Services.ServiceCommande;
import Utils.JavaMail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
;
import com.stripe.Stripe;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javax.mail.MessagingException;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class PayerController implements Initializable {


    @FXML
    private TextField num_carte;

    @FXML
    private TextField mois_expiration;

    @FXML
    private TextField annee_expiration;

    @FXML
    private TextField cvc;

    @FXML
    private Label total;
    
    @FXML
    private Button payer_commande;
public static double total_cmd;
public static int cmd_id;
    String email;
 String carNumber;
 String cvvc;
 String expm;
 String expy;
 String total2;
 String idcu;
 
  Map <Integer,String> ListeCustomer = new HashMap<Integer,String>();
  
  int id_user= Session.getCurrentSession().getId();
  
  
  
 private IServiceCommande commandeService;
  private ISendEmailService sendEmailService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Stripe.apiKey ="sk_test_rR8VRX6Jm99hnqLV1XMHDl3a00EOJxQSqo";
         
    }   
    
     public void initData(Commande c)
    {
    Double p=c.getPrixTotal();
    cmd_id=c.getId();
    String prix_total=Double.toString(p);
    total.setText(prix_total+" DT");
    total_cmd=p;
    }
     
    
      @FXML
    void Payer_commande(ActionEvent event) throws StripeException, Exception {
      ArrayList<Integer> tab =new ArrayList<Integer>();
      int key=0;
  Stripe.apiKey ="sk_test_rR8VRX6Jm99hnqLV1XMHDl3a00EOJxQSqo";
  if ((num_carte.getText().length()==0)||(mois_expiration.getText().length()==0)||(annee_expiration.getText().length()==0)||(cvc.getText().length()==0) )
{
    System.out.println("feragh");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir les champs manquants");
            alert.showAndWait();
 
  } 
else 
{
            try
            {
            Map <String,Object> customerParameter = new HashMap<String,Object>();
            commandeService = new ServiceCommande();
     String mail=Session.getCurrentSession().getEmail();
     String np=Session.getCurrentSession().getNom()+Session.getCurrentSession().getPrenom();
                System.out.println(mail);
    customerParameter.put("email",mail);
    customerParameter.put("description","Nom & Prénom : "+np);
    Customer newCustomer =Customer.create(customerParameter);
    System.out.println("customer ajouté");
    String idcas=newCustomer.getId();
    Customer a=Customer.retrieve(idcas);
    
    Map <String,Object> cardParam = new HashMap<String,Object>();
  cardParam.put("number",num_carte.getText());
cardParam.put("exp_month",mois_expiration.getText());
cardParam.put("exp_year",annee_expiration.getText());
cardParam.put("cvc",cvc.getText());
  
 Map <String,Object> tokenParam = new HashMap<String,Object>();
 tokenParam.put("card",cardParam);
 Token token=Token.create(tokenParam);
  
  Map <String,Object> source = new HashMap<String,Object>();
 source.put("source",token.getId());
 a.getSources().create(source);
 
 Map <String,Object> chargeParam = new HashMap<String,Object>();
 double d=total_cmd;
 int prix=(int)d;
 int prix_f=prix*3;
 System.out.println(prix);
 chargeParam.put("amount",prix);
chargeParam.put("currency","eur");
chargeParam.put("customer",a.getId());
Charge.create(chargeParam);
  Gson gson=new GsonBuilder().setPrettyPrinting().create();
  tab.add(0);
            }  
            catch(StripeException e)
    {
                String m=e.getMessage();
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText(m);
            alert.showAndWait();   
               tab.add(1); 
    }
            
 for(int i=0;i<tab.size();i++)
 {
 if(tab.get(i)!=1)
 {
 key=9;
 }
 
 }
 
 if(key==9)
 {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes !");
            alert.setHeaderText(null);
            alert.setContentText("Payement Effectué avec success");
            alert.showAndWait();   
      
     commandeService = new ServiceCommande();
     String mail=Session.getCurrentSession().getEmail();
     JavaMail.sendMail(mail);
     commandeService.ChangerEtatCommandeToPaye(cmd_id);
     String message="Bonjour l'équipe ecosystem vous informe que le payement de votre commande dont le prix total est : "+total_cmd+" DT est effectué avec success ";
     
 
   try
        {
        
sendEmailService.SendE_mail(mail,message,"Payement Effectué Avec Succes");
        }
        catch (MessagingException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to send mail", e);
            }

 }
         
                          
        }  

    
    
}
    
    
    
    
    
    
    
     
}
