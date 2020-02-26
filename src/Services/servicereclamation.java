/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Session;
import Entities.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.ConnexionBD;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class servicereclamation {
    

    
    Connection c= ConnexionBD.getinstance().getcnx();
    public void ajouterReclamation (reclamation cl)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into reclamation values("+cl.getId()+",'"+cl.getMail()+"','"+cl.getType()+"','"+cl.getDescription()+"',"+cl.getEtat()+")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierType(int id, String type)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update reclamation set type=? where id=?");
            pt.setString(1,type);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    /**
     *
     * @param id
     * @param mail
     */
    public void modifierMail(int id, String mail)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update reclamation set mailCli=? where id=?");
            pt.setString(1,mail);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public void modifierEtat(int id, int etat)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update reclamation set etat=? where id=?");
            pt.setInt(1,etat);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public void modifierDescription(int id, String description)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update reclamation set description=? where id=?");
            pt.setString(1,description);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void afficherReclamation()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from reclamation");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("reclamation [ id: " +rs.getInt(1) + " mail " + rs.getString(2) + " type : " + rs.getString(3) + " description:  " + rs.getString(4)+ " etat: " +rs.getInt(5) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public static  List<reclamation> getAllReclamation() 
    
    {
        List<reclamation> list = new ArrayList<reclamation>();
        Connection cn = ConnexionBD.getinstance().getcnx();
        PreparedStatement pt;
        try {
            String sql = "select * from reclamation ";
            pt = cn.prepareStatement(sql);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                reclamation Reclamation = new reclamation();

                Reclamation.setId(resultSet.getInt("id"));
                Reclamation.setMail(resultSet.getString("mail"));
                Reclamation.setType(resultSet.getString("type"));
                Reclamation.setDescription(resultSet.getString("Description"));
                Reclamation.setEtat(resultSet.getInt("Etat"));
                
                list.add(Reclamation);
                Reclamation.toString();
            }
            cn.close();
        } catch (SQLException e) {
        }
        return list;
     }
     // hathi ili sta3mltha bech nrecuperi les liste
    public List<reclamation> readAll() throws SQLException {
            List<reclamation> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String mail=rs.getString(2);
               String type=rs.getString(3);
               String description=rs.getString(4);
               int etat=rs.getInt(5);
               
               
               reclamation c=new reclamation(id,mail,type,description,etat);
     arr.add(c);
     }
    return arr;
    }
    
/*
public List<reclamation> getReclamation()  throws SQLException {
  
        List<reclamation> listu = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM reclamation";
            Statement s;
            s = ConnexionBD.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            reclamation a = new reclamation();
            a.setId(rs.getInt("id"));
            a.setMail(rs.getString("mailCli"));
            a.setType(rs.getString("type"));        
            a.setDescription(rs.getString("description"));
            a.setEtat(rs.getInt("etat"));
            
                listu.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listu;
    }

    
*/

public List<reclamation> readAllUser() throws SQLException {
            List<reclamation> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation WHERE mailCli='"+Session.getCurrentSession().getEmail()+"'");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String mail=rs.getString(2);
               String type=rs.getString(3);
               String description=rs.getString(4);
               int etat=rs.getInt(5);
               
               
               reclamation c=new reclamation(id,mail,type,description,etat);
     arr.add(c);
     }
    return arr;
    }
    

public List<reclamation> RechercherUtilisateur(String mail)  throws SQLException {
  
        List<reclamation> listu = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="select * from reclamation where mailCli=qq@cc.fr ";
                      //  PreparedStatement pt = c.prepareStatement("select * from reclamation where mailCli= ");

                       // pt.setString(1,mail);

            Statement s;
            s = ConnexionBD.getinstance().getcnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            reclamation a = new reclamation();
            a.setId(rs.getInt("id"));
            a.setMail(rs.getString("mailCli"));
            a.setType(rs.getString("type"));        
            a.setDescription(rs.getString("description"));
            a.setEtat(rs.getInt("etat"));
            
                listu.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listu;
    }





    public void RechercherReclamation_traite()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from reclamation where etat=1");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("reclamation [ id: " +rs.getInt(1) + " mail " + rs.getString(2) + " type : " + rs.getString(3) + " description:  " + rs.getString(4)+ " etat: " +rs.getInt(5) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void RechercherReclamation_Non_traite()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from reclamation where etat=0");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("reclamation [ id: " +rs.getInt(1) + " mail " + rs.getString(2) + " type : " + rs.getString(3) + " description:  " + rs.getString(4)+ " etat: " +rs.getInt(5) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void TrierReclamation_up()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from reclamation ORDER BY type ASC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("reclamation [ id: " +rs.getInt(1) + " mail " + rs.getString(2) + " type : " + rs.getString(3) + " description:  " + rs.getString(4)+ " etat: " +rs.getInt(5) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void TrierReclamation_down()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from reclamation ORDER BY type DESC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("reclamation [ id: " +rs.getInt(1) + " mail " + rs.getString(2) + " type : " + rs.getString(3) + " description:  " + rs.getString(4)+ " etat: " +rs.getInt(5) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerReclamation (int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from reclamation where id=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicereclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
