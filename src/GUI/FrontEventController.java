/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontEventController implements Initializable {
@FXML
    private TextField search;
    @FXML
    private FlowPane flow;
    @FXML
    private BorderPane container;
    @FXML 
    private Button accueil;
  

     
   
   // private ICategorieAnnonceService categorieAnnonceService;
    public static ObservableList<Event> obsl;
    //public static ObservableList<Annonce> obsDate;
    public static ObservableList<Event> prixasc;
    public static ObservableList<Event> prixdesc;
    public static ObservableList<Event> myannonces;
    //public static ObservableList<Annonce> myannoncesCAt;
    public static ObservableList<Event> likedAnnonce;
   // public static ObservableList<Annonce> ViwedAnnonce;
    public static List<Event> listsearch;
    public static int indice;
    public static int x=0;
    public int nbProduit;
    
    @FXML
    private ImageView panier;
    
   

    @FXML
     private Label nombre_article;

   
    @FXML
    private Button btn_my;
    @FXML
    private Button btnall;
    
    //private ComboBox<Categorie_Annonce> cmb_cat;
    //private ISignalAnnonceService signalAnnonceService;
    @FXML
    private Button btn_liked;
    @FXML
    private Button btn_viwed;
    @FXML
    private Button btn_my1;
    public static Event e ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      AfficherCards();
    }  
    public void AfficherCards() {

        ServiceEvent srv = new ServiceEvent();
        
       
        EventController.i = 0;
        ArrayList<Event> annonces = new ArrayList<>();
        annonces = (ArrayList) srv.afficherEvents2();
        obsl = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("event.fxml"));
               //e=FrontEventController.obsl.get(i);
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     /* panierService = new PanierService();
        int nbr=panierService.LonguerPanier();
        nombre_article.setText("("+nbr+")");*/
        
    }
    public void directAccueil(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/mainuserscreen.fxml"));
            Parent root= loader.load();
            
            MainuserscreenController r= loader.getController();
           accueil.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
