/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Session;
import IServices.IServiceProduit;
import Services.ServiceProduit;
import Utils.ControlleSaisie;
import Utils.copyImages;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.Parent;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author anasc
 */
public class ProduitAdminController implements Initializable {

    @FXML
    private TableView<Produit> ListeAnnonce;
    @FXML
    private TableColumn<Produit, String> titre;

    @FXML
    private TableColumn<Produit, String> Description;

    @FXML
    private TableColumn<Produit, Date> date_creation;

    @FXML
    private TableColumn<Produit, Double> prix;

    @FXML
    private TableColumn<Produit, String> region;

    @FXML
    private TableColumn<Produit, String> etat;

    @FXML
    private TableColumn<Produit, Image> photo;

    @FXML
    private TableColumn<Produit, Integer> likes;

    @FXML
    private TableColumn<Produit, Integer> views;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_Edit;

    @FXML
    private JFXButton btn_Delete;

    @FXML
    private JFXButton btn_Clear;

    @FXML
    private JFXTextField txt_Titre;

    @FXML
    private JFXTextField txt_discription;

    @FXML
    private JFXTextField txt_prix;
    @FXML
    private JFXTextField txt_prix1;
    @FXML
    private ComboBox<String> cmb_region;

    @FXML
    private Button btn_photo_img;

    @FXML
    private ImageView img_photo;

    @FXML
    private ComboBox<String> cmb_cat;

    private String absolutePathPhotoAnnonce;

    @FXML
    private Text txtAnnoncephoto;
    @FXML
    private TextField rechTF;

    private IServiceProduit annonceService;

  

    private Image img;
    @FXML
    private TextField rechTF1;
    @FXML
    private JFXTextField txt_lib;
    @FXML
    private JFXButton btn_add_Cat;
    @FXML
    private JFXButton btn_Delet_Cat;
    @FXML
    private JFXButton btn_Edit_Cat;
    @FXML
    private JFXButton btn_Clear_Cat;
  

    @FXML
    private BarChart<?, ?> all;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private PieChart piecharCAt;

    /**
     * Initializes the controller class.
     */
    private void AfficherAll() {

        annonceService = new ServiceProduit();
        ArrayList arrayList = (ArrayList) annonceService.getall();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        ListeAnnonce.setItems(observableList);
        titre.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_creation.setCellValueFactory(new PropertyValueFactory<>("nomRef"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        etat.setCellValueFactory(new PropertyValueFactory<>("dispo"));
       
        photo.setCellValueFactory(new PropertyValueFactory<>("img"));
        likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
        views.setCellValueFactory(new PropertyValueFactory<>("views"));
      

    }



    private void AfficherCombo() {
    

        ObservableList<String> reg = FXCollections.observableArrayList("NonDisponible", "Disponible");
        cmb_cat.getItems().addAll(reg);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AfficherAll();
        AfficherCombo();
     
        ListeAnnonce.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails();
        });
       
        FilterRech();
        ImageClicked();
       // AfficherStat();
    }

    private void FilterRech() {
        annonceService = new ServiceProduit();
        ArrayList annonces = (ArrayList) annonceService.getall();
        ObservableList Oannonces = FXCollections.observableArrayList(annonces);
        FilteredList<Produit> filtred_an = new FilteredList<>(Oannonces, e -> true);
        rechTF.setOnKeyReleased(e -> {
            rechTF.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_an.setPredicate((Predicate<? super Produit>) ann -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((ann.getNomProd().toLowerCase().contains(toLowerCaseNewValue)) || (ann.getDescription().toLowerCase().contains(toLowerCaseNewValue)) || (ann.getNomRef().toLowerCase().contains(toLowerCaseNewValue))) {
                        return true;

                    }

                    return false;
                });
            });
        });
        ListeAnnonce.setItems(filtred_an);
    }


    @FXML
    private void photoAnnonceChooser(ActionEvent event
    ) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        btn_photo_img.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoAnnonce = choix.getAbsolutePath();
                txtAnnoncephoto.setText(choix.getName());
            } else {
                System.out.println("Image introuvable");
            }
        });
    }

    @FXML
    private void ajouterAction(ActionEvent event
    ) {
        annonceService = new ServiceProduit();
        if (!(ControlleSaisie.estVide(txt_Titre, "titre"))
                && !(ControlleSaisie.estVide(txt_discription, "descritpion"))
                && !(ControlleSaisie.estVide(txt_prix, "prix"))
                && !(ControlleSaisie.estVidePhoto(txtAnnoncephoto, "image"))
           
                && !(ControlleSaisie.estVideCombo(cmb_cat, "Etat"))) {
            
            Produit a = new Produit(
                    txt_Titre.getText(),
                   txt_prix1.getText(),
                    txtAnnoncephoto.getText(),
                     cmb_cat.getValue(),
                    txt_discription.getText(),
                    Double.parseDouble(txt_prix.getText()));
                
                    
                   
                   
            copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\");
            copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\");
             annonceService.add(a);

        }
        AfficherAll();

    }

    

    @FXML
    public void supprimerAction(ActionEvent event
    ) {
        annonceService = new ServiceProduit();
        int index = ListeAnnonce.getSelectionModel().getSelectedItem().getIdProd();
        //System.out.println(index);
        annonceService.delete(index);
        AfficherAll();
    }


    @FXML
    void modifierAction(ActionEvent event) {

        annonceService = new ServiceProduit();
        int index = ListeAnnonce.getSelectionModel().getSelectedItem().getIdProd();
        System.out.println(index);
        String a_Titre = txt_Titre.getText();
        String a_Description = txt_discription.getText();
             String a_Prix = txt_prix.getText();
        String a_nomref = txt_prix1.getText();

        String a_Photo = txtAnnoncephoto.getText();
       
        String a_Cate = cmb_cat.getValue();
   
       Produit a = new Produit(a_Titre, a_nomref, a_Photo,a_Cate, a_Description, Double.parseDouble(a_Prix));
        annonceService.update(a, index);
        AfficherAll();

    }

   
    @FXML
    void annulerAction(ActionEvent event) {
        txt_Titre.setText("");
        txt_discription.setText("");
        txt_prix.setText("");
        txt_prix1.setText("");
        txtAnnoncephoto.setText("");
        cmb_cat.setValue(null);

    }

    private void showDetails() {
        txt_Titre.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getNomProd());
        txt_discription.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getDescription());
        txt_prix1.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getNomRef());
      //  txt_prix.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getPrix());
        
        txtAnnoncephoto.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getImg());
        cmb_cat.setValue(ListeAnnonce.getSelectionModel().getSelectedItem().getDispo());
       
      
        img = new Image("file:C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\" + txtAnnoncephoto.getText());
        img_photo.setImage(img);
    }

    

    private void ImageClicked() {
        img_photo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("j'ai clicker ici");
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/GUI/ImageshowAnn.fxml"));
                try {
                    Loader.load();
                } catch (IOException e) {
                    System.out.println(e);
                }
                ImageshowAnnController display = Loader.getController();
                display.getImage(img);
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
                event.consume();
            }

        });
    }
/*
    private void AfficherStat() {
        annonceService = new ServiceProduit();
        ArrayList<Integer> Stat = new ArrayList<Integer>();
        Stat = (ArrayList<Integer>) annonceService.Stat();
        ArrayList<String> months = new ArrayList<>();
        months.add("janvier");
        months.add("février");
        months.add("mars");
        months.add("avril");
        months.add("mai");
        months.add("juin");
        months.add("juillet");
        months.add("janvier");
        months.add("août");
        months.add("septembre");
        months.add("octobre");
        months.add("décembre");
        XYChart.Series set1 = new XYChart.Series<>();

        for (int i = 0; i < Stat.size(); i++) {
            set1.getData().add(new XYChart.Data(months.get(i), Stat.get(i)));

        }

        all.getData().addAll(set1);
    }
/*
    private void getSataCat() {
        List<Produit> la = new ArrayList<>();
        annonceService = new AnnonceService();
        la = annonceService.StatByCat();
        ObservableList<PieChart.Data> piecharts = FXCollections.observableArrayList();
        for (Produit l : la) {
            piecharts.add(new PieChart.Data(l.getNomCat(), l.getNb_cat()));
        }

        piecharCAt.setData(piecharts);
    }
*/
}
