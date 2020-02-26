/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Utilisateur;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author AMINE
 */
public class UtilisateurController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private Button vider;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur, String> clNom;
    @FXML
    private TableColumn<Utilisateur, String> clPrenom;
    @FXML
    private TableColumn<Utilisateur, String> clAge;
    @FXML
    private TableColumn<Utilisateur, String> clEmail;
    @FXML
    private TableColumn<Utilisateur, String> clNumT;
    @FXML
    private TableColumn<Utilisateur, String> clRole;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private Text nom;
    @FXML
    private Text adressee;
    @FXML
    private Text datee;
    @FXML
    private TextField txtnumTel;
    @FXML
    private TextField txtage;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtrole;
    @FXML
    private Button btn;
    @FXML
    private Button supprimer;
       @FXML
    private Button bloc;
    @FXML
    private ImageView imgv;
   
    ObservableList<Utilisateur> dataEvent = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        clAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clNumT.setCellValueFactory(new PropertyValueFactory<>("numero"));
        clRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        UserService srv =new UserService();
       
        dataEvent =FXCollections.observableArrayList(srv.getAllUsers());
        
        table.setItems(dataEvent);  
        setCellValue();
        
        
    }    
    public void setCellValue ()
  {
        
      table.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Utilisateur e= table.getItems().get(table.getSelectionModel().getSelectedIndex());
              txtnom.setText(e.getNom());
              txtprenom.setText(e.getPrenom());
              txtage.setText(e.getAge());
              txtemail.setText(e.getEmail());
              txtnumTel.setText(e.getNumero());
              txtrole.setText(e.getRole());
              
              
          }
          
      });
              }
   public void testAff(ActionEvent event)
     {
         ArrayList<Utilisateur> le = (ArrayList<Utilisateur>) UserService.getAllUsers();

        for(Utilisateur e:le)
        {
            dataEvent.add(e);
        }  
        clNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        clAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clNumT.setCellValueFactory(new PropertyValueFactory<>("numero"));
        clRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        UserService srv =new UserService();
        dataEvent =FXCollections.observableArrayList(srv.getTUtilisateur());
        table.setItems(dataEvent); 
 
    }
    @FXML
   public void recher(ActionEvent event) {
         
       clNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        clAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clNumT.setCellValueFactory(new PropertyValueFactory<>("numero"));
        clRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
         UserService srv =new UserService();
       
        
        dataEvent=FXCollections.observableArrayList(srv.rechercherUtilisateur(recherche.getText()));
        
        table.setItems(dataEvent);
  

   
}

    @FXML
    private void directAccueil(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root= loader.load();
            LoginController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
    }
    }
    @FXML
    private void vider(ActionEvent event) {
         txtnom.clear();
        txtprenom.clear();
        txtage.clear();
        txtemail.clear();
        txtrole.clear();
        txtnumTel.clear();
    }
    /*public int getid()
  {
       UserService srv =new UserService();
        int x  = srv.getId(txtnom.getText());   
        return x;

  }
    */
    public void validerModif(ActionEvent event) 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier ce Utilisateur ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {UserService srv=new UserService();
                            
                            
                                System.out.println(getid());
                               
                                
                                        
                             
              srv.edit(txtnom.getText(), txtprenom.getText(),txtemail.getText(), txtage.getText(),txtnumTel.getText(),txtrole.getText(),getid());
                                
          }testAff(event);
          
                    }
    public int getid()
  {
       UserService srv =new UserService();
        int x  = srv.getId(txtnom.getText());   
        return x;

  }
    


    @FXML
    private void validersupp(ActionEvent event) throws SQLException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer ce Utilisateur ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {UserService srv=new UserService();
                           
                            srv.supprimer(clNom.getText());
       
        String index = table.getSelectionModel().getSelectedItem().getNom();
        //System.out.println(index);
        UserService.supprimer(index);
        testAff(event);
                                           
          }

            
    }
    
      public void validerBloc(ActionEvent event) throws SQLException 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Bloquage");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment bloquer ce Utilisateur ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {UserService srv=new UserService();
                            
                            
                                System.out.println(getid());
                               
                                
                                        
                                 Utilisateur e= table.getItems().get(table.getSelectionModel().getSelectedIndex());
              srv.Bloquer(e);
                                
          }testAff(event);
          
                    }
}
    

