/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author arafe
 */
public class copyImages {

    public static void deplacerVers(Text txtField, String path, String copyTo) {
        if (!(txtField.getText().equals("")) ){
            try {
                String[] args = { "CMD", "/C", "COPY", "/Y", path, copyTo };
                Process p = Runtime.getRuntime().exec(args);
                p.waitFor();
                System.out.println("executé");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    
    }
    
}
