
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author AMINE
 */
public class Camps {

    private String idCamp;
    private String nomCamp;
    private String adresse;
    private String nbrmax;

    public Camps() {
        }

    

    

    public String getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(String idCamp) {
        this.idCamp = idCamp;
    }

    public Camps(String idCamp, String nomCamp, String adresse, String nbrmax) {
        this.idCamp = idCamp;
        this.nomCamp = nomCamp;
        this.adresse = adresse;
        this.nbrmax = nbrmax;
        
    }

   

   /* public Camps() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public String getNomCamp() {
        return nomCamp;
    }

    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNbrmax() {
        return nbrmax;
    }

    public void setNbrmax(String nbrmax) {
        this.nbrmax = nbrmax;
    }

    @Override
    public String toString() {
        return "Camps{" + "idCamp=" + idCamp + ", nomCamp=" + nomCamp + ", adresse=" + adresse + ", nbrmax=" + nbrmax + '}';
    }

    public Double getLatitude() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Double getLongitude() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

   