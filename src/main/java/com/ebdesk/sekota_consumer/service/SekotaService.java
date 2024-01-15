/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.service;

import java.util.Map;

/**
 *
 * 
 */

public interface SekotaService {
  
  void sendMailNotif(Map message);
  
  void sendSMSNotif(Map message);
  
}
