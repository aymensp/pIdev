package IServices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Services.*;
import Entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
public interface IUtilisateurService {
    
    public void addUtilisateur(Utilisateur p) throws SQLException;

    public List<Utilisateur> getUtilisateurs() throws SQLException;

    public Utilisateur getById(int id) throws SQLException;

    public void deleteUtilisateur(Utilisateur p) throws SQLException;

    public void deleteUtilisateur_ById(int id) throws SQLException;

    public void updateUtilisateur(Utilisateur p) throws SQLException;
    

    
    public List<Utilisateur> RechercherUtilisateur(String nom) throws SQLException;

}
