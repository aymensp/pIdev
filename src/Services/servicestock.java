/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Utils.ConnexionBD;
import Entities.stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.ConnexionBD;
/**
 *
 * @author Mohamed
 */
public class servicestock {
    
    
    Connection c= ConnexionBD.getinstance().getcnx();
    public void ajouterstock (stock cl)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into stock values("+cl.getIdStock()+",'"+cl.getTypeStock()+"',"+cl.getQteStock()+",'"+cl.getNomProduit()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierTypeStock(stock cl, String typeStock)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update stock set typeStock=? where idStock=?");
            pt.setString(1,typeStock);
            pt.setInt(2,cl.getIdStock());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public void modifierQteStock(stock cl, int qteStock)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update stock set qteStock=? where idStock=?");
            pt.setInt(1,qteStock);
            pt.setInt(2,cl.getIdStock());
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
      public void modifierQte(int id, int qte)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update stock set qteStock=? where idStock=?");
            pt.setInt(1,qte);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public void afficherStock()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from stock");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("stock [ idStock: " +rs.getInt(1) + " typeStock " + rs.getString(2) + " qteStock : " + rs.getInt(3) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerStock (stock cl)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from stock where idStock=?");
            pt.setInt(1,cl.getIdStock());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /*
    public void TrierStock_up()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from stock ORDER BY qteStock ASC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("stock [ idStock: " +rs.getInt(1) + " typeStock " + rs.getString(2) + " qteStock : " + rs.getInt(3) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void TrierStock_down()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from stock ORDER BY qteStock DESC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("stock [ idStock: " +rs.getInt(1) + " typeStock " + rs.getString(2) + " qteStock : " + rs.getInt(3) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    
    public List<stock> readAll() throws SQLException {
            List<stock> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from stock");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String type=rs.getString(2);
                int quantite=rs.getInt(3);
                String nom=rs.getString(4);
               
               stock c=new stock(id,type,quantite,nom);
     arr.add(c);
     }
    return arr;
    }
    
    
    
    /*
   public int getById(String type,stock cl) throws SQLException{
       // Utilisateur a=new Utilisateur();
         int  a;
        try {
           // String req="SELECT * FROM stock where typeStock='"+type+"'";
            PreparedStatement pt = c.prepareStatement("SELECT * FROM stock where typeStock=?");
            pt.setInt(1,cl.getIdStock());
            pt.executeUpdate(); 
            a=setIdStock(pt.getInt("id"));
            
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
*/

    public void supprimerStock (int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from stock where idStock=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
