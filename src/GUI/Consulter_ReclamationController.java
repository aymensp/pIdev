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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import Entities.reclamation;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.servicereclamation;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/*
 * FXML Controller class
 *
 * @author Mohamed
 */
/*
public class Consulter_ReclamationController implements Initializable {

    @FXML
    private TableView<reclamation> tableview;
   
   ObservableList<reclamation> obl = FXCollections.observableArrayList();
    @FXML
    private  TableColumn<reclamation,Integer> ColId;
    @FXML
    private TableColumn<reclamation,String> ColMail;
    @FXML 
    private TableColumn<reclamation,String> ColType;
    
    @FXML 
    private TableColumn<reclamation,String> ColDescription;
    
    @FXML 
    private TableColumn<reclamation,Integer> ColEtat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    public void afficherAllReclamation(){
        ArrayList<reclamation> le;
        le = (ArrayList<reclamation>) servicereclamation.getAllReclamation();

        le.forEach((e) -> {
            obl.add(e);
        });  
        
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        tableview.setItems(obl);
        tableview.setEditable(true);
    }
    
    
    
}

/*



public class Consulter_ReclamationController implements Initializable {
    
 private Connection cnx; 

  @FXML
    private TableView<reclamation> tableview;
   
   ObservableList<reclamation> obl = FXCollections.observableArrayList();
    @FXML
    private  TableColumn<reclamation,Integer> ColId;
    @FXML
    private TableColumn<reclamation,String> ColMail;
    @FXML 
    private TableColumn<reclamation,String> ColType;
    
    @FXML 
    private TableColumn<reclamation,String> ColDescription;
    
    @FXML 
    private TableColumn<reclamation,Integer> ColEtat;
    
                
  servicereclamation u = new servicereclamation();        
ObservableList listuu = FXCollections.observableArrayList();

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
       // récuperer les données a partir de  la base 
          ObservableList<reclamation> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(reclamation bb: u.getReclamation())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(Consulter_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
    //mettre les données dans la table view:  
       
         ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        // TODO
        tableview.setItems(listu);
    }    
}
   */




public class Consulter_ReclamationController implements Initializable {

    @FXML
    private TableView<reclamation> tableview;
   
    @FXML
    private  TableColumn<reclamation,Integer> ColId;
    @FXML
    private TableColumn<reclamation,String> ColMail;
    @FXML 
    private TableColumn<reclamation,String> ColType;
    
    @FXML 
    private TableColumn<reclamation,String> ColDescription;
    
    @FXML 
    private TableColumn<reclamation,Integer> ColEtat;
    
     servicereclamation u = new servicereclamation();        
   ObservableList listuu = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             ObservableList<reclamation> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(reclamation bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(Consulter_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
         ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        // TODO
        tableview.setItems(listu);
    }    
    
}
