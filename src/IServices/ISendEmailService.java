/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Services.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author Aziz
 */
public interface ISendEmailService {
    public void SendE_mail(String email , String messageS,String Subject) throws AddressException, MessagingException ;
}
