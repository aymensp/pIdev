/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.stock;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Services.servicestock;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */


public class ModifierStockController implements Initializable {
  /*   @FXML
    private TextField rechercher;
   */ @FXML
    private TableView<stock> tableview;
   
    @FXML
    private  TableColumn<stock,Integer> ColId;
    @FXML
    private TableColumn<stock,String> ColType;
    @FXML 
    private TableColumn<stock,String> ColQuantite;
    @FXML 
    private TableColumn<stock,String> ColNom;
   
    @FXML
    private TextField Nom;
    @FXML
    private Button stock;
    
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
         Logger.getLogger(Afficher_StockController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColId.setCellValueFactory(new PropertyValueFactory<>("idStock"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("typeStock"));
        ColQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
      //ColNom.setCellValueFactory(new PropertyValueFactory<>("NomProd"));
        
        // TODO
        tableview.setItems(listu);
    }    
    @FXML
    private void Retour2(MouseEvent event) throws IOException {
        Stage primaryStage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        Scene scene = new Scene(root);
       // System.out.println("sign in"); 
   //     primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @FXML
    private void Retour3(MouseEvent event) throws IOException {
        Stage primaryStage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("AjoutStock.fxml"));
        Scene scene = new Scene(root);
       // System.out.println("sign in"); 
   //     primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    //methode supprimer utilisateur:
        public void deleteUtilisateur() throws SQLException
    {
     ObservableList<stock> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion Stock");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment Supprimer ce stock ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
     if (action.get() == ButtonType.OK)
     {
     for(stock gg:SelectedRows){
       allpeople.remove(gg);
       u.supprimerStock(gg.getIdStock());
     }
     }
  
    }   
      /*  
          public void Change_Email(TableColumn.CellEditEvent b) throws SQLException{
     reclamation reclamationselected = tableview.getSelectionModel().getSelectedItem();
     reclamationselected.setMail(b.getNewValue().toString());
     u.modifierMail(18,"fdfds");

          }
        
        */
        
        /*
          public void Change_to_Traite() throws SQLException
    {
     ObservableList<stock> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     
     for(stock gg:SelectedRows){
      // allpeople.remove(gg);
       u.modifierQte(gg.getIdStock(),555);
     }
  
    }
          */
          
                public void Change() throws SQLException
    {
     ObservableList<stock> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     int Quantite = Integer.parseInt(Nom.getText());

     
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier ce stock ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
     if (action.get() == ButtonType.OK)
     {
     for(stock gg:SelectedRows){
      // allpeople.remove(gg);
       u.modifierQte(gg.getIdStock(),Quantite);
     }
     }
    }
          
          
          
          
           
       public void Refrech() 
                            {

   ObservableList<stock> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(stock bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(Afficher_StockController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColId.setCellValueFactory(new PropertyValueFactory<>("idStock"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("typeStock"));
        ColQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
           //     ColNom.setCellValueFactory(new PropertyValueFactory<>("nomProd"));

        //ColNom.setCellValueFactory(new PropertyValueFactory<>("NomProd"));
        
        // TODO
        tableview.setItems(listu);
    }
}
       
       //chercher une reclamation par son mail 
     /*  
       public void Recherche_Stock( ) throws SQLException{
            String text = rechercher.getText();
              ObservableList<stock> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(stock bb: u.RechercherUtilisateur(text))
             listu.add(bb);
            tableview.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(Supprimer_reclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }   
 
       }
       
}

*/
