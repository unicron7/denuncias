/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.mail;

import denuncia.dto.DenunciaDto;
import denuncia.entity.Usuario;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mejiaw
 */
public class MailSender {
    
    private Properties getProperties(ConnectionData datos) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", datos.getHost());
        props.put("mail.smtp.port", datos.getPort());
        return props;
    }
    
    private Session getSession(final ConnectionData datos) {
        Session session = Session.getInstance(getProperties(datos),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(datos.getUserName(), datos.getPassword());
                    }
                });
        return session;
    }
    
    public String getNewDenunciaMsg(DenunciaDto d) {
        String textHTML= "<p>";
        if(d.getTipoDenuncia().equals("Mineria ilegal")) {
            textHTML+="<strong>Nombre denunciante: </strong><em> " + d.getNombreDenunciante() +" </em><br>"
                + "<strong>Telefono denunciante: </strong><em> " + d.getTelefonoDenunciante() +" </em><br>"
                + "<strong>Correo denunciante: </strong><em> " + d.getCorreoDenunciante() +" </em><br>"
                + "<strong>Tipo denuncia: </strong><em> " + d.getTipoDenuncia() +" </em><br>"
                + "<strong>Provincia: </strong><em> " + d.getProvincia() +" </em><br>"
                + "<strong>Cantón: </strong><em> " + d.getCanton() +" </em><br>"
                + "<strong>Parroquia: </strong><em> " + d.getParroquia() +" </em><br>"
                + "<strong>Sector: </strong><em> " + d.getSector().toUpperCase() +" </em><br>"
                + "<strong>Detalle denuncia: </strong><em> " + d.getDetalleDenuncia() +" </em><br>"
                + "<strong>Fecha: </strong><em> " + d.getFechaHoraEnvio() +" </em><br>"
                + "<strong>Adjuntos: </strong><em> " + listarAdjuntos(d.getAdjuntos()) +" </em><br>"
                + "</p>";
        } else {
            textHTML+="<strong>Nombre denunciante: </strong><em> " + d.getNombreDenunciante() +" </em><br>"
                + "<strong>Telefono denunciante: </strong><em> " + d.getTelefonoDenunciante() +" </em><br>"
                + "<strong>Correo denunciante: </strong><em> " + d.getCorreoDenunciante() +" </em><br>"
                + "<strong>Tipo denuncia: </strong><em> " + d.getTipoDenuncia() +" </em><br>"
                + "<strong>Nombre denunciado: </strong><em> " + d.getNombreDenunciado() +" </em><br>"
                + "<strong>Detalle denuncia: </strong><em> " + d.getDetalleDenuncia() +" </em><br>"
                + "<strong>Fecha: </strong><em> " + d.getFechaHoraEnvio() +" </em><br>"
                + "<strong>Adjuntos: </strong><br><em> " + listarAdjuntos(d.getAdjuntos()) +" </em><br>"
                + "</p>";
        }
        return textHTML;
    }
    
    private String listarAdjuntos(List<String> adjuntos) {
        String lista= "";
        if(adjuntos!=null) {
            for(String s : adjuntos) {
                lista+= s + "; <br>";
            }
        }
        return lista;
    }
    
    public Long sendMailHTML(String asunto, String contenido, String destinatario) {
        Long result= (long) 0;
        ConnectionData datos= new ConnectionData();
        try {
            Message message = new MimeMessage(getSession(datos));
            message.setFrom(new InternetAddress(datos.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            Transport.send(message);
            result= (long) 1;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    public Long sendMailHTML(String asunto, String contenido, Usuario destinatario) {
        Long result= (long) 0;
        
        ConnectionData datos= new ConnectionData();
        try {
            Message message = new MimeMessage(getSession(datos));
            message.setFrom(new InternetAddress(datos.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario.getCorreoElectronico()));
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            Transport.send(message);
            result= (long) 1;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    public Long sendMailHTML(String asunto, String contenido, List<Usuario> destinatarios) {
        Long result= (long) 0;
        
        ConnectionData datos= new ConnectionData();
        try {
            Message message = new MimeMessage(getSession(datos));
            message.setFrom(new InternetAddress(datos.getFrom()));
            message.setRecipients(Message.RecipientType.TO, obtenerDestinatarios(destinatarios));
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            Transport.send(message);
            result= (long) 1;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    private InternetAddress[] obtenerDestinatarios(List<Usuario> destinatarios) {
        String correos= "";
        for(Usuario u : destinatarios) {
            correos+= u.getCorreoElectronico() + ",";
        }
        
        if(correos.length()>0) {
            try {
                return InternetAddress.parse(correos);
            } catch (AddressException ex) {
                System.out.println("Ocurrio un error al obtener las direcciones de correo");
            }
        }
        return null;
    }
}