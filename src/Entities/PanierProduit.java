/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.Image;

/**
 *
 * @author aymen
 */
public class PanierProduit {
    private int id ;
    private String idProd;
    private String nomProd;
    Image image;
    private double prix;
    private String photo;
    public PanierProduit(){
        
    }

    public PanierProduit(int id, String idProd, String nomProd, Image image, double prix, String photo) {
        this.id = id;
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.image = image;
        this.prix = prix;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "PanierProduit{" + "id=" + id + ", idProd=" + idProd + ", nomProd=" + nomProd + ", image=" + image + ", prix=" + prix + ", photo=" + photo + '}';
    }
    
}
