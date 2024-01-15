/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.config;

import com.ebdesk.sekota_consumer.util.PropertyConf;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * 
 */
@Configuration
@PropertySource("file:${global.conf}/global.properties")
public class Config {

  @Autowired
  private Environment env;
  
  @Autowired
  private void dataSource() {
    PropertyConf.setEnv(env);
  }
  
  
  @Bean
  public MongoClient mongoClient() {
    return new MongoClient(new MongoClientURI(PropertyConf.getProperty("mongo.url")));
  }

  @Primary
  @Bean(name = "mongoTemplateConfig")
  public MongoTemplate mongoTemplateConfig() {
    MongoTemplate mongoTemplateConfig = new MongoTemplate(mongoClient(), PropertyConf.getProperty("mongo.config"));

    return mongoTemplateConfig;
  }

}
