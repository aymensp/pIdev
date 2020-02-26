/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import IServices.IServicePanierProduit;
import IServices.IServiceProduit;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AllProduitController implements Initializable {
@FXML
    private TextField search;
    @FXML
    private FlowPane flow;
    @FXML
    private BorderPane container;
  

     
    private IServiceProduit annonceService;
    private IServicePanierProduit panierService;
   // private ICategorieAnnonceService categorieAnnonceService;
    public static ObservableList<Produit> obsl;
    //public static ObservableList<Annonce> obsDate;
    public static ObservableList<Produit> prixasc;
    public static ObservableList<Produit> prixdesc;
    public static ObservableList<Produit> myannonces;
    //public static ObservableList<Annonce> myannoncesCAt;
    public static ObservableList<Produit> likedAnnonce;
    public static ObservableList<Produit> ViwedAnnonce;
    public static List<Produit> listsearch;
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
    private Button btn_prixcr;
       @FXML
    private Button btn_prixdec;
    @FXML
    private Button btn_my1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       AfficherCards();
    }    
    
     @FXML
    private void prixasc(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
      //  prixasc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixASC());
        indice = 3;
        CardsProduitController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        container.setCenter(root);

    }

    @FXML
    private void prixdesc(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
      //  prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 2;
        CardsProduitController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        container.setCenter(root);
    }

    @FXML
    private void search(ActionEvent event) throws IOException {
        listsearch = obsl.stream().filter(e -> e.getNomProd().contains(search.getText())).collect(Collectors.toList());
        indice = 1;
        CardsProduitController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        container.setCenter(root);
    }

/*

    @FXML
    private void MesAnnonces(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
        myannonces = FXCollections.observableArrayList((ArrayList) annonceService.GetByUser());
        indice = 4;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }

   */

    public void AfficherCards() {

        annonceService = new ServiceProduit();
        
       
        CardsProduitController.i = 0;
        ArrayList<Produit> annonces = new ArrayList<>();
        annonces = (ArrayList) annonceService.getall();
        obsl = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("CardsProduit.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     /* panierService = new PanierService();
        int nbr=panierService.LonguerPanier();
        nombre_article.setText("("+nbr+")");*/
        
    }
    
    @FXML
    private void gettall(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
        obsl = FXCollections.observableArrayList((ArrayList) annonceService.getall());
        indice = 0;
        CardsProduitController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        container.setCenter(root);
    }

  

    @FXML
    private void getliked(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
        likedAnnonce = FXCollections.observableArrayList((ArrayList) annonceService.GetMostLikes());
        indice = 2;
        CardsProduitController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        container.setCenter(root);
    }
    @FXML
  private void getviewed(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
        ViwedAnnonce = FXCollections.observableArrayList((ArrayList) annonceService.GetMostLikes());
        indice = 3;
        CardsProduitController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        container.setCenter(root);
    }

    

    @FXML
    private void AffichagePanier(MouseEvent event) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Panier.fxml"));
                /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              
                stage.setTitle("Affichage du Panier ");
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
    
    public static void update_nb_annonce()
    {
        
    }
    
    
     
    
    
    
    @FXML
    private void Stat(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new ServiceProduit();
        Parent root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
        container.setCenter(root);
    }
}
