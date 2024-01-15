/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ebdesk.sekota_consumer.util.EmailTemplate;
import com.ebdesk.sekota_consumer.util.LangUtil;
import com.ebdesk.sekota_consumer.util.PropertyConf;
import com.ebdesk.sekota_consumer.util.SendEmail;
import com.ebdesk.sekota_consumer.util.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.ebdesk.sekota_consumer.service.SekotaService;
import com.ebdesk.sekota_consumer.util.PhoneActivation;

/**
 *
 * 
 */
@Service
public class SekotaServiceImpl implements SekotaService {

  @Autowired
  MongoTemplate mongoTemplateConfig;
  @Autowired
  EmailTemplate emailTemplate;
//  private final String app_secret = PropertyConf.getProperty("jwt.app.secret");
  private final StringUtil stringUtil = new StringUtil();
  private final LangUtil langUtil = new LangUtil();
//  private final EmailTemplate emailTemplate = new EmailTemplate();
  private final SendEmail sendEmail = new SendEmail();
  private final PhoneActivation phoneActivation = new PhoneActivation();
  private final Logger logger = LoggerFactory.getLogger(SekotaServiceImpl.class);

  @Override
  public void sendMailNotif(Map data) {
    try {
      double u_id = (double) data.get("u_id");
      String u_email = (String) data.get("u_email");
      String type = (String) data.get("type");
      String location = (String) data.get("location");
      String server_location = (String) data.get("server_location");

      Map workspace = getDataConfig(location, "city_id", 2, "workspace");

      String app_name = workspace.get("app_name") == null ? null : workspace.get("app_name").toString();
      app_name = StringUtils.capitalize(app_name);
      String app_banner1 = workspace.get("banner1") == null ? null : workspace.get("banner1").toString();
      String app_banner2 = workspace.get("banner2") == null ? null : workspace.get("banner2").toString();
      if (!u_email.isEmpty()) {
        String token = createJWT(u_id, u_email, "user", "smart-city", 3600, location);
        String subject = "";
        String content = "";

        if (type.equals("email_activation")) {
          logger.info("Send Email Activation to " + u_email + " | " + location);
          subject = langUtil.emailSubjectRegist("id", app_name);
          content = emailTemplate.emailActivationV2(token, app_banner1, app_banner2, "id", location, server_location);
        } else if (type.equals("email_forgot")) {
          logger.info("Send Email Forgot to " + u_email);
          subject = langUtil.emailSubjectResetPassword("id", app_name);
          content = emailTemplate.emailForgotPasswordV2(token, app_banner1, app_banner2, "id", location);
        } else if (type.equals("email_change")) {
          logger.info("Send Email Change to " + u_email);
          subject = langUtil.emailSubjectChangeEmail("id", app_name);
          content = emailTemplate.emailChangeMailV2(token, app_banner1, app_banner2, "id", location);
        }
        sendEmail.EmailActivation(getEmailConfig(workspace), subject, content, u_email);
      }
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    }
  }

  @Override
  public void sendSMSNotif(Map message) {
    try {
      String type = message.get("type").toString();
      String u_phone_number = message.get("u_handphone").toString();
      String token = message.get("token").toString();
      String location = message.get("location").toString();

      Map workspace = getDataConfig(location, "city_id", 2, "workspace");
      String app_name = workspace.get("app_name") == null ? null : workspace.get("app_name").toString();
      app_name = StringUtils.capitalize(app_name);

      if (type.equals("sms_activation")) {
        logger.info("Send SMS Activation to " + u_phone_number);
        phoneActivation.PhoneActivation(u_phone_number, langUtil.smsContentRegist("id", app_name, token));
      } else if (type.equals("sms_forgot")) {
        logger.info("Send SMS Forgot to " + u_phone_number);
        phoneActivation.PhoneActivation(u_phone_number, langUtil.smsContentForgotPassword("id", app_name, token));
      } else if (type.equals("sms_change_number")) {
        logger.info("Send SMS Change Number to " + u_phone_number);
        phoneActivation.PhoneActivation(u_phone_number, langUtil.smsContentChangePhone("id", app_name, token));
      }
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    }
  }

  public Map getDataConfig(String _id, String key, int type, String collection) {

    Query query = new Query();
    switch (type) {
      case 1:
        query.addCriteria(Criteria.where(key).is(new ObjectId(_id)));
        break;
      case 2:
        query.addCriteria(Criteria.where(key).is(_id));
        break;
      case 3:
        query.addCriteria(Criteria.where(key).is(Integer.valueOf(_id)));
        break;
      default:
        break;
    }

    List<Map> data = mongoTemplateConfig.find(query, Map.class, "workspace");
    return data != null && !data.isEmpty() ? data.get(0) : new HashMap();
  }

  public Map getEmailConfig(Map workspace) {
    Map mOther = (Map) workspace.get("others");

    return (Map) mOther.get("email_config");
  }

  public String createJWT(double id, String email, String issuer, String subject, long expireTime, String location) {
    String jwtToken = "";
    try {
      Algorithm algorithm = Algorithm.HMAC256(PropertyConf.getProperty("jwt.app.secret"));
      Date date = new Date();
      Date expTime = new Date((expireTime * 1000L) + date.getTime());
      jwtToken = JWT.create()
        .withIssuer(issuer)
        .withSubject(subject)
        .withClaim("u_id", id)
        .withClaim("u_email", email)
        .withClaim("city_code", location)
        .withIssuedAt(date)
        .withExpiresAt(expTime)
        .sign(algorithm);
    } catch (IllegalArgumentException | UnsupportedEncodingException e) {
      logger.error(stringUtil.getError(e));
    }
    return jwtToken;
  }

}
