/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.refugies;
import Services.Servicerefugies;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherrefugiesController implements Initializable {
    @FXML
    private Hyperlink printpdf;
    @FXML 
    private ImageView aj;
    @FXML 
    private ImageView re;
    @FXML 
    private ImageView rech;
    @FXML 
    private TextField recherche;
    @FXML
    private Button retour;
    @FXML
    private Button btAjArticl;
 
  @FXML
    private TableColumn<refugies, String> nom;
  @FXML
    private TableColumn<refugies, String> prenom;
  @FXML
    private TableColumn<refugies, String> pays;
  @FXML
    private TableColumn<refugies, Integer> age;
  @FXML
    private TableView table;
  ObservableList<refugies> refugieslist ;
  static refugies Recup ;
  @FXML
  private Button ajouter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
          delete();
         addButtonUpdateToTable();
        
                refugies a=new refugies();

        Servicerefugies ac =new Servicerefugies();
        refugieslist=FXCollections.observableArrayList(ac.afficherrefugies());
      
        
        table.setItems(refugieslist);
        
       
       
    }   
    
    
    private void addButtonUpdateToTable() {
        TableColumn<refugies, Void> colBtn = new TableColumn("Modifier refugies");

        Callback<TableColumn<refugies, Void>, TableCell<refugies, Void>> cellFactory = new Callback<TableColumn<refugies, Void>, TableCell<refugies, Void>>() {
            @Override
            public TableCell<refugies, Void> call(final TableColumn<refugies, Void> param) {
                final TableCell<refugies, Void> cell = new TableCell<refugies, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Recup=getTableView().getItems().get(getIndex());
                                 try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierrefugies.fxml"));
                                Parent root = loader.load();
                              ModifierrefugiesController rc = loader.getController();
                               btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        

        table.getColumns().add(colBtn);

    }
    
    @FXML
    private void retourajouter(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouterRefugies.fxml"));
            Parent root= loader.load();
            AjouterRefugiesController rc= loader.getController();
            
            ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }
       private void delete() {
        TableColumn<refugies, Void> colBtn = new TableColumn("Supprimer refugies");

        Callback<TableColumn<refugies, Void>, TableCell<refugies, Void>> cellFactory = new Callback<TableColumn<refugies, Void>, TableCell<refugies, Void>>() {
            @Override
            public TableCell<refugies, Void> call(final TableColumn<refugies, Void> param) {
                final TableCell<refugies, Void> cell = new TableCell<refugies, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                           
                            refugies art = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppression");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez vraiment supprimer le refugié de l'IdRef " + art.getIdRef() +"> ?");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK) {
                                Servicerefugies ac = new Servicerefugies();
                                ac.supprimerrefugies(art.getIdRef()); //supprimer T3amlet

                            }

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherrefugies.fxml"));
                                Parent root = loader.load();
                                AfficherrefugiesController rc = loader.getController();
                               btAjArticl.getScene().setRoot(root);
                                
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }

                        });
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        

        table.getColumns().add(colBtn);
       

    }
       
       
       
    @FXML
    private void testAff(KeyEvent event) {
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
       
        
        
        
        
        
       
        refugies a=new refugies();
        Servicerefugies ac =new Servicerefugies();
        
  //   refugieslist=FXCollections.observableArrayList(ac.(tfAuteurRechercher.getText()));

    refugieslist=FXCollections.observableArrayList(ac.afficherrefugies());
        
        table.setItems(refugieslist);
       
        
        
         
        

    

   
}
      @FXML
    private void redirectionAjArticle(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouterrefugies.fxml"));
            Parent root= loader.load();
            AjouterRefugiesController rc= loader.getController();
            
            btAjArticl.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
            @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainadminscreen.fxml"));
            Parent root= loader.load();
            MainAdminScreenController rc= loader.getController();            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }
    @FXML
    public void recher(ActionEvent event) {
         
 
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
      
           Servicerefugies ac =new Servicerefugies();       
        
      //  dataEvent=FXCollections.observableArrayList(srv.rechercherEvent(recherche.getText()));
         refugieslist=FXCollections.observableArrayList(ac.rechercherEvent(recherche.getText()));
 table.setItems(refugieslist);  

   
}
    public void pdf (ActionEvent event) throws FileNotFoundException
    {   Servicerefugies srv = new Servicerefugies();
        srv.pdf();
    }
}
