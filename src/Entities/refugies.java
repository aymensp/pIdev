/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class refugies {
    private int idRef;
    private String nom;
    private String prenom;
        private int age;
    private String pays;
    

    public refugies(int idRef, String nom, String prenom, int age, String pays) {
        this.idRef = idRef;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.pays = pays;
    }

   
    public refugies() {      
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "refugies{" + "idRef=" + idRef + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", pays=" + pays + '}';
    }


    
}
