/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Annonces;
import Entities.Sponsors;
import Services.ServicesAnnonces;
import Services.ServicesSponsors;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Malek Guemri
 */
public class Afficher_SponsorsController implements Initializable {

    @FXML
    private TableView<Sponsors> sponsorsTable;
    @FXML
    private TableColumn<Sponsors, String> Nom;
    @FXML
    private TableColumn<Sponsors, String> Adresse;
    @FXML
    private TableColumn<Sponsors, String> Type;
    @FXML
    private TableColumn<Sponsors, String> NumeroTel;
    @FXML
    private TableColumn<Sponsors, String> Modifier;
    @FXML
    private TableColumn<Sponsors, String> Supprimer;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtNumTel;
    @FXML
    private ChoiceBox<String> comboType;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServicesSponsors ss = new Services.ServicesSponsors();
        ObservableList<String> type = FXCollections.observableArrayList();
        type.addAll("type1","type2");
        comboType.getItems().addAll(type);
        ObservableList<Sponsors> list = FXCollections.observableArrayList();
        try {
            list = ss.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtAdresse.setVisible(false);
        txtNom.setVisible(false);
        comboType.setVisible(false);
        btnValider.setVisible(false);
        txtNumTel.setVisible(false);
        
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        //.setCellValueFactory(new PropertyValueFactory<>("IDannonce"));
        NumeroTel.setCellValueFactory(new PropertyValueFactory<>("numtel"));

        Callback<TableColumn<Sponsors, String>, TableCell<Sponsors, String>> cellFactory = (param) -> {
            final TableCell<Sponsors, String> cell = new TableCell<Sponsors, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button btnEdit = new Button("Modifier");
                        btnEdit.setOnAction(event -> {
                            
                            txtAdresse.setVisible(true);
                            txtNom.setVisible(true);
                            comboType.setVisible(true);
                            btnValider.setVisible(true);
                            txtNumTel.setVisible(true);
                            setCellValue();
                            
                            });
                        setGraphic(btnEdit);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        
        Callback<TableColumn<Sponsors, String>, TableCell<Sponsors, String>> cellFactory2 = (param) -> {
            final TableCell<Sponsors, String> cell = new TableCell<Sponsors, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        
                        final Button btnDelete = new Button("Supprimer");
                        btnDelete.setOnAction(event -> {
                            Sponsors s2 = getTableView().getItems().get(getIndex());
                            String id = Integer.toString(s2.getIDsponsors());
                            ss.supprimerSponsors(s2);
                            ObservableList<Sponsors> listUpdate = FXCollections.observableArrayList();
                            try {
                                listUpdate = ss.readAll();
                            } catch (SQLException ex) {
                                Logger.getLogger(Afficher_AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            sponsorsTable.setItems(listUpdate);
                            
                        });

                        setGraphic(btnDelete);
                        setText(null);
                    }
                }

            };
            return cell;
        };
        
        

        Modifier.setCellFactory(cellFactory);
        Supprimer.setCellFactory(cellFactory2);
        sponsorsTable.setItems(list);
        
    }    
    
    @FXML
    private void modifierEvent(MouseEvent event) {
        ServicesSponsors ss = new Services.ServicesSponsors();
                
        Sponsors s = sponsorsTable.getItems().get(sponsorsTable.getSelectionModel().getSelectedIndex());
        ss.modifierSponsors(s.getIDsponsors(),txtNom.getText(), txtAdresse.getText(), comboType.getValue(),txtNumTel.getText());
        ObservableList<Sponsors> listUpdate = FXCollections.observableArrayList();
        try {
            listUpdate = ss.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sponsorsTable.setItems(listUpdate);
        txtAdresse.setVisible(false);
        txtNom.setVisible(false);
        comboType.setVisible(false);
        txtNumTel.setVisible(false);
        btnValider.setVisible(false);
        
        
    }
    public void setCellValue ()
  {
        
      sponsorsTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              
          
        Sponsors e= sponsorsTable.getItems().get(sponsorsTable.getSelectionModel().getSelectedIndex());
        txtAdresse.setText(e.getAdresse());
        txtNom.setText(e.getNom());
        txtNumTel.setText(e.getNumtel());
        comboType.setValue(e.getType());
              
          }
          
          
      });
  }
}
    

