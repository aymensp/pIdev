/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Annonces;
import Services.ServicesAnnonces;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Malek Guemri
 */
public class Afficher_AnnonceController implements Initializable {

    @FXML
    private TableColumn<Annonces, String> coloneAdresse;
    @FXML
    private TableColumn<Annonces, String> coloneType;
    @FXML
    private TableColumn<Annonces, String> coloneDesc;
    @FXML
    private TableColumn<Annonces, String> coloneModifier;
    @FXML
    private TableColumn<Annonces, String> coloneSupprimer;
    @FXML
    private TableView<Annonces> tableAnnonce;
    @FXML
    private TextField txAdresse;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Button btnValider;
     @FXML
    private Button retour;
    @FXML
    private TextField cmboType;
    @FXML
    private TableColumn<Annonces, Integer> coloneID;
    @FXML
    private TableColumn<Annonces, Date> colonedate;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicesAnnonces sa = new Services.ServicesAnnonces();
        ObservableList<Annonces> list = FXCollections.observableArrayList();
        try {
            list = sa.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txAdresse.setVisible(false);
        txtDesc.setVisible(false);
        cmboType.setVisible(false);
        btnValider.setVisible(false);
        coloneAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        coloneType.setCellValueFactory(new PropertyValueFactory<>("type"));
        coloneDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coloneID.setCellValueFactory(new PropertyValueFactory<>("IDannonce"));
        colonedate.setCellValueFactory(new PropertyValueFactory<>("datePub"));

        Callback<TableColumn<Annonces, String>, TableCell<Annonces, String>> cellFactory = (param) -> {
            final TableCell<Annonces, String> cell = new TableCell<Annonces, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button btnEdit = new Button("Modifier");
                        btnEdit.setOnAction(event -> {
                            txAdresse.setVisible(true);
                            txtDesc.setVisible(true);
                            cmboType.setVisible(true);
                            btnValider.setVisible(true);
                            setCellValue();
                            
                          
                            });
                        setGraphic(btnEdit);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        
        Callback<TableColumn<Annonces, String>, TableCell<Annonces, String>> cellFactory2 = (param) -> {
            final TableCell<Annonces, String> cell = new TableCell<Annonces, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        
                        final Button btnDelete = new Button("Supprimer");
                        btnDelete.setOnAction(event -> {
                            Annonces a2 = getTableView().getItems().get(getIndex());
                            String id = Integer.toString(a2.getIDannonce());
                            sa.supprimerAnnonce(a2.getIDannonce());
                            ObservableList<Annonces> listUpdate = FXCollections.observableArrayList();
                            try {
                                listUpdate = sa.readAll();
                            } catch (SQLException ex) {
                                Logger.getLogger(Afficher_AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            tableAnnonce.setItems(listUpdate);
                        });
                        setGraphic(btnDelete);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        coloneModifier.setCellFactory(cellFactory);
        coloneSupprimer.setCellFactory(cellFactory2);
        tableAnnonce.setItems(list);
    }    

    @FXML
    private void modifierEvent(MouseEvent event) {
        
        ServicesAnnonces sa = new Services.ServicesAnnonces();
       Annonces e= tableAnnonce.getItems().get(tableAnnonce.getSelectionModel().getSelectedIndex());
        
        sa.modifierAnnonce(e.getIDannonce(), txAdresse.getText(), cmboType.getText(), txtDesc.getText());
        ObservableList<Annonces> listUpdate = FXCollections.observableArrayList();
        try {
            listUpdate = sa.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableAnnonce.setItems(listUpdate);
        txAdresse.setVisible(false);
        txtDesc.setVisible(false);
        cmboType.setVisible(false);
        btnValider.setVisible(false);
    }
public void setCellValue ()
  {
        
      tableAnnonce.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Annonces e= tableAnnonce.getItems().get(tableAnnonce.getSelectionModel().getSelectedIndex());
        txAdresse.setText(e.getAdresse());
        txtDesc.setText(e.getDescription());
        cmboType.setText(e.getType());
              
          }
          
          
      });
  
  }
  public void retour(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainadminscreen.fxml"));
            Parent root= loader.load();
            MainAdminScreenController rc= loader.getController();
            retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }

}
