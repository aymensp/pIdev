/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author aymen
 */
public class Produit {
    private  int idProd;
    private String nomProd,nomRef,img,dispo,description;
    private double prix;
    private int likes;
    private int views;
    Image image;
   
    public Produit()
    {
        
    }

    public Produit(int idProd, String nomProd, String nomRef, String dispo, String description, double prix) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.nomRef = nomRef;
        this.dispo = dispo;
        this.description = description;
        this.prix = prix;
    }

    public Produit(int idProd, String nomProd, String nomRef, double prix ,String description, int likes) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.nomRef = nomRef;
       this.prix = prix;
        this.description = description;
        this.likes=likes;
       
    }
    
    

    public Produit(String nomProd, String nomRef, String img, String description, double prix) {
        
        this.nomProd = nomProd;
        this.nomRef = nomRef;
        this.img = img;
        this.description = description;
        this.prix = prix;
    }

    public Produit(String nomProd, String nomRef, String img, String dispo, String description, double prix) {
        this.nomProd = nomProd;
        this.nomRef = nomRef;
        this.img = img;
        this.dispo = dispo;
        this.description = description;
        this.prix = prix;
    }

    public Produit(int idProd, String nomProd, String nomRef, String img, String dispo,String description, double prix, int likes,int views, Image image) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.nomRef = nomRef;
        this.img = img;
        this.dispo = "disponible";
        this.description=description;
        this.prix = prix;
        this.likes = likes;
        this.views=views;
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Image getImage() {
        return image;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setImage(Image image) {
 this.image = image;
      }

   

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getNomRef() {
        return nomRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
        this.img = img;
         try {
            BufferedImage bf = ImageIO.read(new File("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\" + img));
            BufferedImage bf1 = Scalr.resize(bf, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    250, 100, Scalr.OP_ANTIALIAS);
            image = SwingFXUtils.toFXImage(bf1, null);
        } catch (IOException ex) {
            // NO PHOTO A AJOUTER
            //System.out.println("entities.AnnounceRep.setUrlPhoto()");;
        }
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

   

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProd=" + idProd + ", nomProd=" + nomProd + ", nomRef=" + nomRef + ", img=" + img + ", dispo=" + dispo + ", prix=" + prix + ", likes=" + likes + ", image=" + image + '}';
    }

           
    
}
