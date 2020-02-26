/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import IServices.IServiceProduit;
import Services.ServiceProduit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;


/**
 * FXML Controller class
 *
 * @author anasc
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<?, ?> all;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private PieChart piecharCAt;

    private IServiceProduit annonceService;
    @FXML
    private PieChart piecharLike;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherStat();
        //getSataCat();
        //getStatLiks();
    }

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
        annonceService = new ServiceProduit();
        la = annonceService.StatByCat();
        ObservableList<PieChart.Data> piecharts = FXCollections.observableArrayList();
        for (Produit l : la) {
            piecharts.add(new PieChart.Data(l.getNomCat(), l.getNb_cat()));
        }

        piecharCAt.setData(piecharts);
    }
*/
    /*
    private void getStatLiks() {
        List<Produit> las = new ArrayList<>();
        annonceService = new ServiceProduit();
        las = annonceService.StatByMyProduit();
        ObservableList<PieChart.Data> piecharts = FXCollections.observableArrayList();
        for (Produit l : las) {
            piecharts.add(new PieChart.Data(l.getNomCat(), l.getNb_cat()));
        }

       piecharLike.setData(piecharts);
    }*/
}
