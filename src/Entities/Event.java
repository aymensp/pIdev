/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import javafx.scene.image.Image;

/**
 *
 * @author user
 */
public class Event {
    private int id_event;
    private String nomEvnet;
    private String adresse;
    private String date;
    private String description;
    private String image;
    
    private int nbrMax;
    


    public Event(String nomEvnet, String adresse, String date, String description,int nbrMax,String image) {
     
        this.nomEvnet = nomEvnet;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
       
        this.nbrMax=nbrMax;
        this.image =image;   }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Event() {
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public int getId_event() {
        return id_event;
    }

    public String getNomEvnet() {
        return nomEvnet;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNomEvnet(String nomEvnet) {
        this.nomEvnet = nomEvnet;
    }

   

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

   

    
    
   
    
}
