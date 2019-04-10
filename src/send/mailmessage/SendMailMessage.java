/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.mailmessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jungoliver
 */
public class SendMailMessage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        
        //Get Arguments
        String from = ""; 
        String to = "";
        String bcc;
        String cc; 
        String subject = "";
        String body = "";
        
        for (int i=0;i<args.length;i++)
        {
            switch(args[i])
            {
                case "-from":
                    from =args[i+1];
                    break;
                case "-to":
                    to =args[i+1];
                    break;
                case "-cc":
                    cc =args[i+1];
                    break;    
                case "-bcc":
                    bcc =args[i+1];
                    break;  
                case "-subject":
                    subject =args[i+1];
                    break;
                case "-body":
                    body =args[i+1];
                    break;    
            }
        }
        
        if (!(from.isEmpty())&& !(to.isEmpty()))
        {
        final Properties props = System.getProperties();
               
        props.put("mail.smtp.host", "smtp.intra.schwaebischer-verlag.de");
        props.put("mail.smtp.port", "");
        props.put("mail.transport.protocol","smtp");

        Session session = Session.getDefaultInstance(props);
        body = body.replace("\\n", "\n");
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress((from)));
        if (to.contains(";"))
        {
            for (int i=0;i<to.split(";").length;i++)
            {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to.split(";")[i]));
            }
        }
        else
        {
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        }
        //msg.addRecipient(Message.RecipientType.TO, new InternetAddress("msg_vertrieb@msgmediaservice.de"));
        System.out.println(body);
        msg.setSubject(subject);
        msg.setText(body);
        msg.saveChanges();
        Transport.send(msg);
        }
    }
    
}
