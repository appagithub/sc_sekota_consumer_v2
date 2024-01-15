/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.util;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 
 */
public class PhoneActivation {

  private final Logger logger = LoggerFactory.getLogger(PhoneActivation.class);
  private final StringUtil stringUtil = new StringUtil();

  public void PhoneActivation(String phone_number, String message) {
    OkHttpClient client = new OkHttpClient();
    Response response = null;
    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
    try {
      String url = "https://reguler.zenziva.net/apps/smsapi.php?userkey=f2ztgh&passkey=f2ztghdevel&nohp=" + phone_number + "&pesan=" + message;
      Request request = new Request.Builder().url(url).build();
      response = client.newCall(request).execute();
      logger.info("url : " + url);
      logger.info("send message success to : " + phone_number);
      logger.info(body.toString());
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    } finally {
      if (response != null) {
        response.close();
        response.body().close();
      }
    }
  }
}
