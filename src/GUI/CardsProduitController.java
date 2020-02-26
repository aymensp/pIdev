/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import IServices.IServiceLigneCommande;
import IServices.IServicePanierProduit;
import IServices.IServiceProduit;
import Services.ServiceProduit;
import Services.ServicePanierProduit;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class CardsProduitController implements Initializable {

    @FXML
    private ImageView img_annoce;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label id_annonce;
    @FXML

    private Button consulter;
    @FXML
    private Label lbl_prix;
    @FXML
    private Button likes;
    @FXML
    private Button signaler;
    @FXML
    private Button panier;
    @FXML
    private VBox vbox;
    private IServiceProduit annonceService;
    private IServicePanierProduit popo;
    private IServiceLigneCommande lignecommandeService;
    static int i;
    public int t;
    public int iduser;
    public double xx;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    @FXML
    private Label lbl_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_annonce.setVisible(false);
        // TODO
      //  lignecommandeService = new LigneCommandeService();
        if (AllProduitController.indice == 0) {

                lbl_titre.setText(AllProduitController.obsl.get(i).getNomProd());
                lbl_prix.setText("Dt" + AllProduitController.obsl.get(i).getPrix());
                lbl_id.setText(Integer.toString(AllProduitController.obsl.get(i).getIdProd()));
                t = AllProduitController.obsl.get(i).getIdProd();
                Image imag = new Image("file:C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\" + AllProduitController.obsl.get(i).getImg());
                img_annoce.setImage(imag);
                i++;
            
        }   else if (AllProduitController.indice == 1) {
            
                lbl_titre.setText(AllProduitController.listsearch.get(i).getNomProd());
                lbl_prix.setText("DT" + AllProduitController.listsearch.get(i).getPrix());
                lbl_id.setText(Integer.toString(AllProduitController.listsearch.get(i).getIdProd()));
                t = AllProduitController.listsearch.get(i).getIdProd();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllProduitController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\" + AllProduitController.listsearch.get(i).getImg());
                img_annoce.setImage(imag);
                i++;
            
        }
else if (AllProduitController.indice == 2) {
            
                lbl_titre.setText(AllProduitController.likedAnnonce.get(i).getNomProd());
                lbl_prix.setText("DT" + AllProduitController.likedAnnonce.get(i).getPrix());
                lbl_id.setText(Integer.toString(AllProduitController.likedAnnonce.get(i).getIdProd()));
                t = AllProduitController.likedAnnonce.get(i).getIdProd();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllProduitController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\" + AllProduitController.likedAnnonce.get(i).getImg());
                img_annoce.setImage(imag);
                i++;
            
        }  else if (AllProduitController.indice == 3) {
            
                lbl_titre.setText(AllProduitController.ViwedAnnonce.get(i).getNomProd());
                lbl_prix.setText("DT" + AllProduitController.ViwedAnnonce.get(i).getPrix());
                lbl_id.setText(Integer.toString(AllProduitController.ViwedAnnonce.get(i).getIdProd()));
                t = AllProduitController.ViwedAnnonce.get(i).getIdProd();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllProduitController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\" + AllProduitController.ViwedAnnonce.get(i).getImg());
                img_annoce.setImage(imag);
                i++;
            
        }   
else if (AllProduitController.indice == 7) {
           

                lbl_titre.setText(AllProduitController.likedAnnonce.get(i).getNomProd());
                lbl_prix.setText("Dt" + AllProduitController.likedAnnonce.get(i).getPrix());
              
                lbl_id.setText(Integer.toString(AllProduitController.likedAnnonce.get(i).getIdProd()));
                t = AllProduitController.likedAnnonce.get(i).getIdProd();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllProduitController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\" + AllProduitController.likedAnnonce.get(i).getImg());
                img_annoce.setImage(imag);
                i++;
            
        }
    }

    @FXML
    private void likes(ActionEvent event) {
        annonceService = new ServiceProduit();
        int index = Integer.parseInt(lbl_id.getText());
        annonceService.updateLikes(index);
        likes.setVisible(false);
    }

   

    @FXML
    private void ajouterpanier(ActionEvent event) {

    }

    @FXML
    private void consulter(ActionEvent event) throws IOException {
        annonceService = new ServiceProduit();
        DetaileProduitController1.idd = Integer.parseInt(lbl_id.getText());
        annonceService.updateViews(Integer.parseInt(lbl_id.getText()));
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("DetaileProduit1.fxml"));
        try {
            Loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        event.consume();
    }

    @FXML
    private void ajouterAupanier(ActionEvent event) {
        int ida = Integer.parseInt(lbl_id.getText());
        System.out.println("id : " + ida);
        
        popo= new ServicePanierProduit();
        System.out.println("Test de fonction ::: " + popo.existProduit(ida));

        if (popo.existProduit(ida) == 0) {
            Produit a = popo.RecupererProduit(ida);
            System.out.println(a.toString());
            popo.AjouterAuPanier(a);
            System.out.println("Ajouté au Panier");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CheckedPanier.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();

                stage.setTitle("Panier");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
            
            
        } else {
            System.out.println("Erreur Ajout");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ErreurPanier.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();

                stage.setTitle("Panier");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        }
    }
}
