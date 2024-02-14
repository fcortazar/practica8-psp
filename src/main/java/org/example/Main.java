package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Datos del remitente
        String remitente = "pruebajavacorreo1234@gmail.com";
        String password = "opnc lfna erxj qjvu";

        // Datos del destinatario
        String destinatario = "fcd0005@alu.medac.es";

        // Configuración de las propiedades para el servidor SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Autenticación del remitente
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remitente, password);
                    }
                });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject("Prueba de JavaMail");
            message.setText("Hola,\n\nEste es un correo de prueba enviado desde Java.");

            // Envío del mensaje
            Transport.send(message);

            System.out.println("El correo ha sido enviado exitosamente.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
