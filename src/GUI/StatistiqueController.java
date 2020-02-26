/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.servicestock;
import Utils.ConnexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author OrdiOne
 */
public class StatistiqueController implements Initializable {
   Connection c = ConnexionBD.getinstance().getcnx();
      private servicestock serv = new servicestock();

    @FXML
    private BarChart<String, Integer> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String req =" SELECT typeStock,qteStock from stock ";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
            PreparedStatement ste = (PreparedStatement) c.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            barChart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
