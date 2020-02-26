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
 * @author USER
 */
public class Utilisateur {
    private static int id;
    private static String nom;
    private static String prenom;
    private static String email;
    private static String password;
    private static String age;
    private static String numero; 
     private static String photo ;
      private static String sexe;
    private static String role;
    private int enabled;

    public Utilisateur(int aInt, String string) {
        
    }

    public int isEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
    
    Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Utilisateur(String nom, String prenom, String email, String age, String numero, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.numero = numero;
        this.sexe = sexe;
        this.enabled=1;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String age, String numero, String photo, Image image, String sexe, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.age = age;
        this.numero = numero;
        this.photo = photo;
        this.image = image;
        this.sexe = sexe;
        this.role = role;
        this.enabled=1;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
        try {
            BufferedImage bf = ImageIO.read(new File("C:\\wamp\\www\\Pidev1\\web\\uploads\\user\\photo\\" + photo));
            BufferedImage bf1 = Scalr.resize(bf, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    250, 100, Scalr.OP_ANTIALIAS);
            image = SwingFXUtils.toFXImage(bf1, null);
        } catch (IOException ex) {
            // NO PHOTO A AJOUTER
            //System.out.println("entities.AnnounceRep.setUrlPhoto()");;
        }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
   

    
    public Utilisateur( String nom, String prenom, String email, String password ,String age, String numero,String sexe, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.age = age;
        this.numero = numero;
        this.sexe= sexe;
        this.role = role;
        this.enabled=1;
       
        
    }

    public Utilisateur() {
       
    }

  

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age + ", numero=" + numero + ", role=" + role + ", password=" + password + ", sexe=" + sexe + '}';
    }
    
    
}
