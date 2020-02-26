/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author hp
 */
public class Donneur {
        private int id;
    private int cin;
    private String nom;
    private String prenom;
    private String mail;
    private int don;
    private int numcarte;

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getDon() {
        return don;
    }

    public void setDon(int don) {
        this.don = don;
    }

    public int getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(int numcarte) {
        this.numcarte = numcarte;
    }

    public Donneur(int id, int cin, String nom, String prenom, String mail, int don, int numcarte) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.don = don;
        this.numcarte = numcarte;
    }
    public Donneur(){}
    
    @Override
    public String toString() {
        return "Donneur{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", don=" + don + ", numcarte=" + numcarte + '}';
    }

  

   
    
    
    
    
}

