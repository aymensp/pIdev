/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Tunfugees extends Application {
     public static Boolean isSplashLoaded = false;
    @Override
    public void start(Stage Stage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    Stage.setTitle("Tunfugees");
                    Stage.setScene(scene);
                    Stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Tunfugees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
    