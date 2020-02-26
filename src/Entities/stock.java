/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.TextField;

/**
 *
 * @author Mohamed
 */
public class stock {
    
    
private int idStock,qteStock ;

private String typeStock,nomproduit ;

/*private String dateStock = sdf.format(dt);  */  
    



public stock (int idStock,String typeStock, int qteStock, String nomproduit) {
        this.idStock = idStock;
        this.typeStock = typeStock;
        this.qteStock = qteStock;
       this.nomproduit = nomproduit; 
        
    }

    public stock(int idStock, int qteStock, String typeStock) {
        this.idStock = idStock;
        this.qteStock = qteStock;
        this.typeStock = typeStock;
    }




    public int getIdStock() {
        return idStock;
    }

    public String getTypeStock() {
        return typeStock;
    }

        public int getQteStock() {
        return qteStock;
    }
        
        public int getNomProduit() {
        return qteStock;
    }     
    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public void setTypeStock(String typeStock) {
        this.typeStock = typeStock;
    }
      public int setQteStock() {
        return qteStock;
    }
      
      
    public void setNomProduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }
     
 @Override
    public String toString() {
        return "Stock{" + "idStock= " + idStock + ", typeStock= " + typeStock + ", qteStock= " + qteStock + '}';
    }


}