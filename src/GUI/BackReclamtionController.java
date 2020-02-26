/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Session;
import Entities.reclamation;
import static GUI.Ajouter_AnnonceController.ACCOUNT_SID;
import static GUI.Ajouter_AnnonceController.AUTH_TOKEN;
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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Mohamed
 */


public class BackReclamtionController  implements Initializable {
     @FXML
    private TextField rechercher;
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
public static final String ACCOUNT_SID
            = "AC6e8c8282242929e88dd5c3a24214f157";
    public static final String AUTH_TOKEN
            = "a3f8de1a4a2c809a0db1b95304a8f544";
    
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
    
    
    //methode supprimer utilisateur:
        public void deleteUtilisateur() throws SQLException
    {
     ObservableList<reclamation> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer  cette reclamation ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
     if (action.get() == ButtonType.OK)
     {
     for(reclamation gg:SelectedRows){
       allpeople.remove(gg);
       u.supprimerReclamation(gg.getId());
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
          public void Change_to_Traite() throws SQLException
    {
     ObservableList<reclamation> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment changer l'etat de cette reclamation ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
     if (action.get() == ButtonType.OK)
     {
     for(reclamation gg:SelectedRows){
      // allpeople.remove(gg);
       u.modifierEtat(gg.getId(),1);
     }
  
    }
     
      String num=Session.getCurrentSession().getNumero();
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message
                        .creator(new PhoneNumber("+216" + num), // to
                                new PhoneNumber("+15099564484"), // from
                                "Mr/Mme: " + Session.getCurrentSession().getNom() + " Tunfugees vous informe que votre reclamation  a été traité"
                                + "Merci , Tunfugees est toujours la pour vous servir")
                        .create();

                System.out.println(message.getSid());
    }
          
          
            public void directAccueil(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainadminscreen.fxml"));
            Parent root= loader.load();
            MainAdminScreenController rc= loader.getController();
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
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment changer l'etat de cette reclamation ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
     if (action.get() == ButtonType.OK)
     {
     for(reclamation gg:SelectedRows){
      // allpeople.remove(gg);
       u.modifierEtat(gg.getId(),0);
     }
     }
    }      
       public void Refrech() 
                            {

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
       
       //chercher une reclamation par son mail 
       
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
       
}


