/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
/**
 *
 * @author aymen
 */
public class Annonces {
    private int IDannonce;
    private String adresse;
    private String type;
    private String description;
    private Timestamp datePub;

    public Annonces(String adresse, String type, String description) {
        
        this.adresse = adresse;
        this.type = type;
        this.description = description;
    }
    public Annonces(int ID,String adresse, String type, String description,Timestamp date) {
        
        this.IDannonce = ID;
        this.adresse = adresse;
        this.type = type;
        this.description = description;
        this.datePub=date;
    }

    public Timestamp getDatePub() {
        return datePub;
    }

    public void setDatePub(Timestamp datePub) {
        this.datePub = datePub;
    }

    public Annonces(int IDannonce, String adresse, String type, String description) {
        this.IDannonce = IDannonce;
        this.adresse = adresse;
        this.type = type;
        this.description = description;
    }

    public int getIDannonce() {
        return IDannonce;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setIDannonce(int IDannonce) {
        this.IDannonce = IDannonce;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Annonces{" + "adresse=" + adresse + ", type=" + type + ", description=" + description + ", datePub=" + datePub + '}';
    }

    
}
