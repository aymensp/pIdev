/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author aymen
 */
public class Sponsors {
    
    private int IDsponsors;
    private String nom;
    private String adresse;
    private String type;
    private String numtel;

    public int getIDsponsors() {
        return IDsponsors;
    }

    public void setIDsponsors(int IDsponsors) {
        this.IDsponsors = IDsponsors;
    }

    public Sponsors(String nom, String adresse, String type, String numtel) {
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.numtel = numtel;
    }

    public Sponsors(int IDsponsors, String nom, String adresse, String type, String numtel) {
        this.IDsponsors = IDsponsors;
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "Sponsors{" + "IDsponsors=" + IDsponsors + ", nom=" + nom + ", adresse=" + adresse + ", type=" + type + ", numtel=" + numtel + '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }
    
    
}
