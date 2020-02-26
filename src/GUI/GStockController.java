/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.stock;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.servicestock;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */

public class GStockController implements Initializable {

    @FXML
    private TableView<stock> tableview;
   
    @FXML
    private  TableColumn<stock,Integer> ColId;
    @FXML 
    private TableColumn<stock,String> ColType;
    @FXML 
    private TableColumn<stock,String> ColQuantite;
         servicestock u = new servicestock();        
   ObservableList listuu = FXCollections.observableArrayList();

      
    
    
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             ObservableList<stock> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(stock bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(GStockController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
         ColId.setCellValueFactory(new PropertyValueFactory<>("idStock"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("typeStock"));
        ColQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        
        // TODO
        tableview.setItems(listu);
    }    
    
    
    
      public void Refrech() 
                            {

   ObservableList<stock> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(stock bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(GStockController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
 ColId.setCellValueFactory(new PropertyValueFactory<>("idStock"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("typeStock"));
        ColQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
                
        // TODO
        tableview.setItems(listu);
    }        
       
     
    
    //methode supprimer utilisateur:
        public void deleteUtilisateur() throws SQLException
    {
     ObservableList<stock> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     
     for(stock gg:SelectedRows){
       allpeople.remove(gg);
       u.supprimerStock(gg.getIdStock());
     }
  
    }  
    
}
