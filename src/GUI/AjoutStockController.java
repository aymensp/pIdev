/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.stock;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.servicestock;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */

public class AjoutStockController implements Initializable {

    @FXML
    private Button idajouter;
    @FXML
    private TextField type;
    @FXML
    private TextField quantite;
    @FXML
    private TextField nomproduit;
    
    @FXML
    private Button retour;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterStockAction(ActionEvent event) {
        String Type=type.getText();
       // String Quantite=quantite.getText();
               
 
       int Quantite = Integer.parseInt(quantite.getText());
       String nom=nomproduit.getText();
            
   //  iddates.getValue();
        
       // String cb=String.valueOf(cbitems.getItems());
       
       
       // Date.valueOf( dateformat.format(iddates.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000));
       
       servicestock srv1 = new servicestock();
        stock c;
        c = new stock (0,Type,Quantite,nom);
        srv1.ajouterstock(c);
    }
    
    public void directAccueil(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("GStock.fxml"));
            Parent root= loader.load();
            GStockController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    
    
}