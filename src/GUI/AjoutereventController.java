/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Services.ServiceEvent;
import Utils.ControlleSaisie;

import java.io.File;



import javafx.scene.control.TextArea;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
    

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutereventController implements Initializable {
   @FXML
     private TextField nom;
    @FXML
     private TextField adresse;
    @FXML
     private DatePicker date;
    @FXML
        private TextArea description;
    @FXML
        private Button ajouter;
    @FXML
        private Button vider;
     @FXML
        private Button retour;
    @FXML 
    private TableView <Event> table ;
    @FXML
    private TableColumn <Event,String> no;
    @FXML
    private TableColumn <Event,String> ad;
    @FXML
    private TableColumn <Event,String> da;
    @FXML
    private TableColumn <Event,String> dec;
  
     @FXML
    private TableColumn <Event,Integer> nbr; 
    @FXML 
    private TextField recherche;
    @FXML 
    private TextField nbrr;
    @FXML 
private Button btn;
    @FXML 
private Button supprimer;
    static Event recupevent;
      private Button Front;
   @FXML 
           private ImageView imgv;
   public String pathh;
   
    
     /**
     * Initializes the controller class.
     * 
     * @return 
     */
   
     ObservableList<Event> dataEvent ; 
    @FXML
    private Text nomm;
    @FXML
    private Text adressee;
    @FXML
    private Text datee;
    @FXML
    private Text descriptionn;
      @Override
       
    public void initialize(URL url, ResourceBundle rb) {
     
 no.setCellValueFactory(new PropertyValueFactory<>("NomEvnet"));
        ad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        da.setCellValueFactory(new PropertyValueFactory<>("date"));
        dec.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbrMax"));
        ServiceEvent srv =new ServiceEvent();
       
        dataEvent =FXCollections.observableArrayList(srv.afficherEvents());
        
        table.setItems(dataEvent);  
        setCellValue();
        addButtonListeToTable();
        
       
    }    
 
    @FXML
    public void insertdata (ActionEvent event)
    { if  ( !(ControlleSaisie.date(date, "Date")) 
            && !(ControlleSaisie.estVide(adresse, "adresse "))
            && !(ControlleSaisie.estVide(nom, "adresse "))
            && !(ControlleSaisie.estVideTextArea(description, "description "))
            && !(ControlleSaisie.estVide(nbrr, "nombre maximal "))
            //&& !(ControlleSaisie.date2(date))
            && ! (date.getValue().isBefore(LocalDate.now())
            ) 
            
            )
            
    
        
        
       try {
           String dateBirt = date.getValue().toString();
           String adresseE=adresse.getText();
           String nomE =nom.getText();
           String descr =description.getText();
           int nbrm=Integer.parseInt(nbrr.getText());
           
           
           
           Event evt = new Event();
           evt.setNomEvnet(nomE);
           evt.setAdresse(adresseE);
           evt.setDate(dateBirt);
           evt.setDescription(descr);
           evt.setNbrMax(nbrm);
           evt.setImage(pathh);
         
           ServiceEvent srv =new ServiceEvent();
           srv.ajouterevent(evt);
           testAff(event);
       } catch (MalformedURLException ex) {
           Logger.getLogger(AjoutereventController.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }
    
    @FXML
    public void vider ()
    {
        nom.clear();
        date.setValue(null);
        description.clear();
        adresse.clear();
        nbrr.clear();
        
    }
     public void testAff(ActionEvent event)
     {
        no.setCellValueFactory(new PropertyValueFactory<>("NomEvnet"));
        ad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        da.setCellValueFactory(new PropertyValueFactory<>("date"));
        dec.setCellValueFactory(new PropertyValueFactory<>("description"));
      
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbrMax"));
        ServiceEvent srv =new ServiceEvent();
       
        dataEvent =FXCollections.observableArrayList(srv.afficherEvents());
        
        table.setItems(dataEvent); 
 
    }
    @FXML
     public void recher(ActionEvent event) {
         
        no.setCellValueFactory(new PropertyValueFactory<>("NomEvnet"));
        ad.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        da.setCellValueFactory(new PropertyValueFactory<>("date"));
        dec.setCellValueFactory(new PropertyValueFactory<>("description"));
      
        nbr.setCellValueFactory(new PropertyValueFactory<>("nbrMax"));
        ServiceEvent srv =new ServiceEvent();
       
        
        dataEvent=FXCollections.observableArrayList(srv.rechercherEvent(recherche.getText()));
        
        table.setItems(dataEvent);
  

   
}
    @FXML
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

  public void setCellValue ()
  {
        
      table.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Event e= table.getItems().get(table.getSelectionModel().getSelectedIndex());
              nom.setText(e.getNomEvnet());
              adresse.setText(e.getAdresse());
              description.setText(e.getDescription());
              nbrr.setText(Integer.toString(e.getNbrMax()));
              
          }
          
          
      });
  
  }
  public int getid()
  {
       ServiceEvent srv =new ServiceEvent();
        int x  = srv.getId(nom.getText());   
        return x;

  }
    @FXML
  public void validerModif(ActionEvent event) 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier cet article ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceEvent srv=new ServiceEvent();
                            
                            
                                System.out.println(getid());
                               
                                
                                        
                             
              srv.modifierevent(nom.getText(), adresse.getText(), date.getValue().toString(),description.getText(),Integer.parseInt(nbrr.getText()),getid());
                                
          }testAff(event);
          
                    }
    @FXML
 public void validersupp(ActionEvent event) 
  {
       
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer cet article ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {ServiceEvent srv=new ServiceEvent();
                            
                            
                                srv.supprimerevent(nom.getText());
                               
                                
                                        
                             
                                
          }testAff(event);
          
                    }
  
  private void addButtonListeToTable() {
        TableColumn<Event, Void> colBtn = new TableColumn("liste Vol");

        Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellFactory = new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
            @Override
            public TableCell<Event, Void> call(final TableColumn<Event, Void> param) {
                final TableCell<Event, Void> cell = new TableCell<Event, Void>() {

                    private final Button btn = new Button("Details");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            recupevent=getTableView().getItems().get(getIndex());
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("listevol.fxml"));
                                Parent root = loader.load();
                                ListevolController rc = loader.getController();
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
    public void directFront(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FrontEvent.fxml"));
            Parent root= loader.load();
            FrontEventController rc= loader.getController();
            Front.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    @FXML
    public void importer(ActionEvent event) {
        //file = fileChooser.showOpenDialog();
        //FileChooser fc = new FileChooser();
        
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        //File f =fc.showOpenDialog(null);
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
               //
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
              new FileChooser.ExtensionFilter("PNG", "*.png"));
           String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        
        path = path.replace('\\','/');
        pathh=path;
        System.out.println(path);
        Image img = new Image("file:///"+path);
        
        imgv.setImage(img);
        
        //Image img = new Image(f.getAbsolutePath());
        //if (f != null){
        //   l.setText("selected file" + f.getAbsolutePath());
           //imgv.setImage(img);
        //}
        
        
   }
    
    
   
  

  
}

         
                      
 

 

