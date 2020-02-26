/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Session;
import Entities.Volontaire;
import static GUI.EventController.i;
import Services.ServiceVolontaire;
import Services.Service_generer_Qr_code;
import Services.Service_mail;
import Services.UserService;
import Utils.JavaMail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ParticipVolController implements Initializable {
@FXML 
private Button retour ;
@FXML
private TextField nom;
@FXML
private TextField prenom;
@FXML
private TextField cin;
@FXML
private TextField mail;
@FXML
private TextField tel;
@FXML
private TextField nomEv;
@FXML 
private Button confirmer ;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Event ev=EventController.e;
       
       nomEv.setText(ev.getNomEvnet());
        nomEv.setEditable(false);
       nom.setText(Session.getCurrentSession().getNom());
        nom.setEditable(false);
       prenom.setText(Session.getCurrentSession().getPrenom());
        prenom.setEditable(false);
       mail.setText(Session.getCurrentSession().getEmail());
        mail.setEditable(false);
       tel.setText(Session.getCurrentSession().getNumero());
       tel.setEditable(false);
      //  System.out.println(ev.getNomEvnet());  
    }  
 public void insertVol (ActionEvent event)
    {
        
        
        //int cinn = Integer.parseInt(cin.getText());
        String nomm=Session.getCurrentSession().getNom();
        // int id=Session.getCurrentSession().getId();
                        
        String prenomm=Session.getCurrentSession().getPrenom();
        String maill=Session.getCurrentSession().getEmail();
         int tell=Integer.parseInt(Session.getCurrentSession().getNumero());
      
       
        Event ev= EventController.e;
        System.out.println(maill);
         System.out.println(ev.getNomEvnet());
      System.out.println(nomm);

        Volontaire v= new Volontaire();
        //v.setId_vol(id);
       // v.setCin(cinn);
        v.setNom(nomm);
        v.setPrenom(prenomm);
        v.setMail(maill);
        v.setTel(tell);
        v.setNom_event(ev.getNomEvnet());
        v.setEtat(1);
        ServiceVolontaire srv = new ServiceVolontaire();
        
     
        srv.ajoutervolontaire(v,ev);
        Service_generer_Qr_code Q= new Service_generer_Qr_code();
                                Q.create(nomm,prenom.getText());
                                    Service_mail M = new Service_mail();
                                 M.send_mail(maill,nomm);
        
         //JavaMail.sendMail(r.getMail());
    }
     public void directFront(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FrontEvent.fxml"));
            Parent root= loader.load();
            FrontEventController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
}
