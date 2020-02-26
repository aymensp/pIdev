/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class CheckedPanierController implements Initializable {

    @FXML
    private Button fermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       @FXML
    void close(ActionEvent event) {
Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        
       /* try {
              FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/AnimationPanier.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
           
            
                stage.setOpacity(0.3);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                
                
                
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }*/
    }

    
}
