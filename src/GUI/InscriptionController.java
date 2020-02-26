/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Utilisateur;
import Services.UserService;
import com.jfoenix.controls.JFXComboBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import tray.notification.TrayNotification;
import static tray.notification.NotificationType.SUCCESS;
import Utils.ControlleSaisie;
import Utils.JavaMail;
import Utils.copyImages;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomUser;
    @FXML
    private TextField prenomUser;
    @FXML
    private TextField emailUser;
    @FXML
    private TextField numeroUser;
    @FXML
    private TextField ageUser;
    @FXML
    private TextField sexeUser;
    @FXML
    private PasswordField mdpUser;
    @FXML
    private PasswordField confirmationMdpUser;
    @FXML
    private Button btnAddUser;
    
    
    
    Stage dialogStage = new Stage();
    Scene scene;
            
    private static Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    @FXML
    private Button btnPhotoUser;
    @FXML
    private Text txtPhotoUser;
    
    private String absolutePathPhotoUser;
    @FXML
    ComboBox<String> comboRole;
    ObservableList<String> list=FXCollections.observableArrayList("Présent","Absent");
    @FXML
    ComboBox<String> comboSexe;
    ObservableList<String> lists=FXCollections.observableArrayList("Male","Female");

    @FXML
    void goToLogin(ActionEvent event) throws SQLException, IOException, Exception {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Login.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();   
    }
    
    @FXML
    private void photoUserChooser(ActionEvent event){
    FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        btnPhotoUser.setOnAction(e-> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoUser = choix.getAbsolutePath();
                txtPhotoUser.setText(choix.getName());
             } else {
                System.out.println("Image introuvable");
            }
        });
    }
    
    
    @FXML 
    void addUser(ActionEvent event) throws SQLException, IOException, Exception {
    if ( !(ControlleSaisie.estVide(nomUser, "nom")) 
            && !(ControlleSaisie.estVide(prenomUser, "prenom")) 
            && !(ControlleSaisie.estVide(emailUser, "email"))
            && !(ControlleSaisie.estVide(ageUser, "age"))  
            && !(ControlleSaisie.estVide(numeroUser, " numero ")) 
            && !(txtPhotoUser.getText().equals(""))
            && !(ControlleSaisie.estVide(mdpUser, "mot de passe")) 
            && !(ControlleSaisie.estVide(confirmationMdpUser, "confirmation mdp")) 
            && !(ControlleSaisie.sontConforme( mdpUser, "mot de passe", confirmationMdpUser, "confirmation de mot de passe "))
            && (ControlleSaisie.estEmailValide(emailUser)))
        
        {
            Utilisateur u = new Utilisateur();

            u.setNom(nomUser.getText());
            u.setPrenom(prenomUser.getText());
            u.setEmail(emailUser.getText());
            u.setAge(ageUser.getText());
            u.setNumero(numeroUser.getText());
            u.setPhoto(txtPhotoUser.getText());
            u.setSexe(comboSexe.getValue());
            u.setPassword(mdpUser.getText());
            u.setEnabled(0);
            u.setRole(comboRole.getValue());
            
            copyImages.deplacerVers(txtPhotoUser, absolutePathPhotoUser,"C:\\Pidev1\\src\\res\\upload\\user\\");
            copyImages.deplacerVers(txtPhotoUser, absolutePathPhotoUser,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\user\\photo\\");
            
            UserService.Inscription(u);
           

            TrayNotification tray = new TrayNotification("succès", " ajouté", SUCCESS);
            tray.showAndWait();
        }
     JavaMail.sendMail(emailUser.getText());
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numeroUser.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
       ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("Admin", "Utilisateur");
        comboRole.getItems().addAll(status);
        ObservableList<String> statu = FXCollections.observableArrayList();
        statu.addAll("Male", "Female");
        comboSexe.getItems().addAll(statu);
        }  
    
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();                
                if (txt_TextField.getText().length() >= max_Lengh) {                    
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){ 
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume(); 
                    }
                }else{
                    e.consume();
                }
            }
        };
    }   

    public static boolean valideEmail(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
