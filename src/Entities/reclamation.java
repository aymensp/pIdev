/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Mohamed
 */
public class reclamation {
    

    private int id,etat;
    private String type,mail , description;

    public reclamation(int id,String mail, String type, String description,int etat) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.etat = etat;
        this.mail=mail;
        
    }

    public reclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMail() {
        return mail;
    }
    
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
public void setMail(String mail) {
        this.mail = mail;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }
    
      public int getEtat() {
        return etat;
    }
      
      
    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id= " + id + ", mail= " + mail + ", type= " + type + ", description= " + description + ", etat= " + etat + '}';
    }
    
    
}
