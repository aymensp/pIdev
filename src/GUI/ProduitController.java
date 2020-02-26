/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class ProduitController implements Initializable {

    @FXML
    private FlowPane flow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int size = 0;
        if (AllProduitController.indice == 0) {
            size = AllProduitController.obsl.size();
        } else if (AllProduitController.indice == 1) {
            size = AllProduitController.listsearch.size();}
            else if (AllProduitController.indice == 2) {
            size = AllProduitController.likedAnnonce.size();}
                    else if (AllProduitController.indice == 3) {
            size = AllProduitController.ViwedAnnonce.size();
        }

  
            Node[] nodes = new Node[size];

            for (int i = 0; i < nodes.length; i++) {
                try {

                    final int j = i;
                    nodes[i] = FXMLLoader.load(getClass().getResource("CardsProduit.fxml"));
                    flow.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
