/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.serviceImpl;

import com.ebdesk.sekota_consumer.util.PropertyConf;
import com.ebdesk.sekota_consumer.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.ebdesk.sekota_consumer.service.SekotaService;

/**
 *
 * 
 */
@Service
public class KafkaConsumer {

  @Autowired
  SekotaService mongodbService;

  private final StringUtil stringUtil = new StringUtil();
  private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "topic_sekota_crawler")
  public void listen(@Payload String message) {
    try {
      logger.info("========== new message ==========");
      logger.info("Message : " + message);

      Map valMap = new Gson().fromJson(message, Map.class);
      String status = valMap.get("status").toString();
      if (status.equals("email_notif")) {
        mongodbService.sendMailNotif(valMap);
      } else if (status.equals("sms_notif")) {
        mongodbService.sendSMSNotif(valMap);
      }
    } catch (JsonSyntaxException e) {
      logger.error(stringUtil.getError(e));
    } finally {
      logger.info("========== message finished ==========");
    }
  }

}
