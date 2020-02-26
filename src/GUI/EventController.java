/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Session;
import Services.ServiceEvent;
import Services.ServiceVolontaire;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EventController implements Initializable {

     @FXML
    private ImageView img_annoce;
    @FXML
    private Label lbl_Nom;
    @FXML
    private Label id_annonce;
    @FXML
    private Label lbl_Adresse;
    @FXML
    private VBox vbox;
    private ServiceEvent service;
    static int i;
    public int t;
    public int iduser;
    public double xx;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    @FXML
    private Label lbl_nbr;
    @FXML 
    public  Button participer;
     @FXML 
    public  Button Details;
  
    public static Event e;
    public Event e1;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_annonce.setVisible(false);
        // TODO
      //  lignecommandeService = new LigneCommandeService();
     
                
                lbl_Nom.setText(FrontEventController.obsl.get(i).getNomEvnet());
                lbl_Adresse.setText(FrontEventController.obsl.get(i).getAdresse());
               // lbl_nbr.setText(Integer.toString(FrontEventController.obsl.get(i).getNbrMax()));
                t = FrontEventController.obsl.get(i).getId_event();
                e=FrontEventController.obsl.get(i);
                e1=FrontEventController.obsl.get(i);
                //nombre.setText("On a besoin de "+FrontEventController.obsl.get(i).getNbrMax()+" volontaires pour cet événement ");
                //S=FrontEventController.obsl.get(i).getNomEvnet();
                System.out.println(FrontEventController.obsl.get(i).getImage());
                Image image = new Image("file:/"+FrontEventController.obsl.get(i).getImage());
                img_annoce.setImage(image);
                ServiceVolontaire sr= new ServiceVolontaire();
                 if(sr.RechercherVol(e1.getNomEvnet(), Session.getCurrentSession().getEmail()))
                 {
                    participer.setVisible(false);
                   
                    annuler.setVisible(true);
                 }
                 else{
                     participer.setVisible(true);     
                  annuler.setVisible(false);
                  
                     }
                participer.setOnAction((event) -> {
                    try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ParticipVol.fxml"));
                                Parent root = loader.load();
                                ParticipVolController rc = loader.getController();
                                participer.getScene().setRoot(root);
                                
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }
                });
                 annuler.setOnAction((event) -> {
                     ServiceVolontaire sra= new ServiceVolontaire();
                   sra.supprimervolontaire(Session.getCurrentSession().getEmail(), e1.getNomEvnet());
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("");
             alert.setHeaderText("PARTICIPATION");
             alert.setContentText("Vous avez annulé votre participation !!");
             
             alert.showAndWait();
 participer.setVisible(true);
                   
                    annuler.setVisible(false);
                });
                  Details.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
            public void handle(ActionEvent event) {
                
                Label secondLabel = new Label();
                secondLabel.setText("Description : \n"+e1.getDescription());
 
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(secondLabel);
 
                Scene secondScene = new Scene(secondaryLayout, 230, 100);
 
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Description de l'événement ");
                newWindow.setScene(secondScene);
               // final Stage primaryStage;
                // Set position of second window, related to primary window.
                //newWindow.setX(primaryStage.getX() + 200);
                //newWindow.setY(primaryStage.getY() + 100);
 
                newWindow.show();
            }
        });
                    i++;
                                   

                    
            }
  
}
 




