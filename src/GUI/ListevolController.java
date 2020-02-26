/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Volontaire;
import Services.ServiceEvent;
import Services.ServiceVolontaire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListevolController implements Initializable {
@FXML 
private TableView tab;
@FXML 
private TableColumn <Volontaire,Integer> cin;
@FXML
private TableColumn <Volontaire,String> nom;
@FXML
private TableColumn <Volontaire,String> prenom;
@FXML
private TableColumn <Volontaire,String> mail;
@FXML
private TableColumn <Volontaire,Integer> tel;
@FXML
private TableColumn <Volontaire,String> nomev;
@FXML
private TableColumn <Volontaire,String> presence;
ObservableList<Volontaire> dataVolontaire ;
@FXML
private Button retour;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cin.setCellValueFactory(new PropertyValueFactory<>("Cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        tel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        nomev.setCellValueFactory(new PropertyValueFactory<>("Nom_event"));
        presence.setCellValueFactory(new PropertyValueFactory<>("Presence"));
        ServiceVolontaire srv =new ServiceVolontaire();
       String eya=AjoutereventController.recupevent.getNomEvnet();
       
       
        dataVolontaire =FXCollections.observableArrayList(srv.afficherEvent(eya));

     
        tab.setItems(dataVolontaire);
    } 
    public void aff(ActionEvent event)
    {
        cin.setCellValueFactory(new PropertyValueFactory<>("Cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        tel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        nomev.setCellValueFactory(new PropertyValueFactory<>("Nom_event"));
        presence.setCellValueFactory(new PropertyValueFactory<>("Presence"));
        ServiceVolontaire srv =new ServiceVolontaire();
       String eya=AjoutereventController.recupevent.getNomEvnet();
       
       
        dataVolontaire =FXCollections.observableArrayList(srv.afficherEvent(eya));

     
        tab.setItems(dataVolontaire);
    }
     public void directEvents(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouterevent.fxml"));
            Parent root= loader.load();
            AjoutereventController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    
}
