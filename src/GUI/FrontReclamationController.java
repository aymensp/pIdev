/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.reclamation;
import Services.servicereclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.servicereclamation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */


public class FrontReclamationController  implements Initializable {
     @FXML
    private TextField nom;
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
    
    @FXML
    private Button retour;
     @FXML
    private ChoiceBox<String> comboType;
      ObservableList<String> type = FXCollections.observableArrayList();
            
    

     servicereclamation u = new servicereclamation();        
   ObservableList listuu = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
             ObservableList<reclamation> listu  = FXCollections.observableArrayList();
     try {
        type.addAll("Urgent","Normale");
        comboType.getItems().addAll(type);
    

         
         for(reclamation bb: u.readAllUser())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(Consulter_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
       //  ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        // TODO
        tableview.setItems(listu);
    }    
    
    
    //methode supprimer utilisateur:
        public void deleteUtilisateur() throws SQLException
    {
     ObservableList<reclamation> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     
     for(reclamation gg:SelectedRows){
       allpeople.remove(gg);
       u.supprimerReclamation(gg.getId());
     }
  
    }   
      /*  
          public void Change_Email(TableColumn.CellEditEvent b) throws SQLException{
     reclamation reclamationselected = tableview.getSelectionModel().getSelectedItem();
     reclamationselected.setMail(b.getNewValue().toString());
     u.modifierMail(18,"fdfds");

          }
        
        */
          public void Change_to_Traite() throws SQLException
    {
     ObservableList<reclamation> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
        String description=nom.getText();

     for(reclamation gg:SelectedRows){
      // allpeople.remove(gg);
        if (gg.getEtat()==0)
       u.modifierDescription(gg.getId(),description);
     }
  
    }
          
          
            public void directAccueil(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutReclamation.fxml"));
            Parent root= loader.load();
            AjoutReclamationController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
          
          
          
              public void Change_to_Non_Traite() throws SQLException
    {
     ObservableList<reclamation> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
                             
                   String Combo;
         Combo = comboType.getValue();

     for(reclamation gg:SelectedRows){
      // allpeople.remove(gg);
              if (gg.getEtat()==0)

       u.modifierType(gg.getId(),Combo);
     }
  
    }      
       public void Refrech() 
                            {

   ObservableList<reclamation> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(reclamation bb: u.readAllUser())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(Consulter_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
        // ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        // TODO
        tableview.setItems(listu);
    }        
       
       //chercher une reclamation par son mail 
       /*
       public void Recherche_Utilisateur( ) throws SQLException{
            String text = rechercher.getText();
              ObservableList<reclamation> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(reclamation bb: u.RechercherUtilisateur(text))
             listu.add(bb);
            tableview.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(BackReclamtionController.class.getName()).log(Level.SEVERE, null, ex);
     }   
 
       }
       
*/
}


