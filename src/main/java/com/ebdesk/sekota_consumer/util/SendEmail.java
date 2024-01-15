/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.*;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 
 */
public class SendEmail {

  private final StringUtil stringUtil = new StringUtil();
  private final Logger logger = LoggerFactory.getLogger(SendEmail.class);

  public void EmailActivation(String subject, String content, String email) {

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props,
      new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("id.indicator@gmail.com", "4dm1n12345");
      }
    });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("id.indicator@gmail.com"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
      message.setSubject(subject);

      MimeMultipart mp = new MimeMultipart();
      MimeBodyPart mbp1 = new MimeBodyPart();

      mbp1.setContent(content, "text/html");
      mp.addBodyPart(mbp1);
      message.setContent(mp);
      Transport.send(message);
      logger.info("email has been send to : " + email);
    } catch (MessagingException e) {
//      throw new RuntimeException(e);
      logger.error(stringUtil.getError(e));
    }
  }

  public void EmailActivation(Map emailConfig, String subject, String content, String emailReceiver) {
//    Map mEmailConfig = globalService.getGetEmailConfig(location);
    Properties props = new Properties();
    props.put("mail.smtp.host", emailConfig.get("mail_smtp_host"));
    props.put("mail.smtp.socketFactory.port", emailConfig.get("mail_smtp_socketFactory_port"));
    props.put("mail.smtp.socketFactory.class", emailConfig.get("mail_smtp_socketFactory_class"));
    props.put("mail.smtp.auth", emailConfig.get("mail_smtp_auth"));
    props.put("mail.smtp.port", emailConfig.get("mail_smtp_port"));

    try {
      String emailSender = decrypt(emailConfig.get("email").toString(), "rahasia");
      String pass = decrypt(emailConfig.get("password").toString(), "rahasia");
      Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(emailSender, pass);
        }
      });

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(emailSender));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailReceiver));
      message.setSubject(subject);

      MimeMultipart mp = new MimeMultipart();
      MimeBodyPart mbp1 = new MimeBodyPart();

      mbp1.setContent(content, "text/html");
      mp.addBodyPart(mbp1);
      message.setContent(mp);
      Transport.send(message);
      logger.info("email has been send to : " + emailReceiver);
    } catch (MessagingException e) {
//      throw new RuntimeException(e);
      logger.error(stringUtil.getError(e));
    } catch (Exception ex) {
      logger.error(stringUtil.getError(ex));
    }
  }

  public static String decrypt(String strEncrypted, String strKey) throws Exception {
    String strData = "";

    try {
      SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
      Cipher cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.DECRYPT_MODE, skeyspec);
      byte[] decoded = decodeBase64(strEncrypted.getBytes());
      byte[] decrypted = cipher.doFinal(decoded);
      strData = new String(decrypted);

    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
      e.printStackTrace();
      throw new Exception(e);
    }
    return strData;
  }
}
