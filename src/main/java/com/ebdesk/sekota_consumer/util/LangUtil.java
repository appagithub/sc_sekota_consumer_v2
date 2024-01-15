/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.sekota_consumer.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 
 */
public class LangUtil {

  public String emailSubjectRegist(String lang, String app_name) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = app_name + " - " + "Pendaftaran";
    } else if (lang.toLowerCase().equals("en")) {
      result = app_name + " - " + "Registration";
    }

    return result;
  }

  public String emailSubjectResetPassword(String lang, String app_name) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = app_name + " - Reset Kata Sandi";
    } else if (lang.toLowerCase().equals("en")) {
      result = app_name + " - Reset Password";
    }

    return result;
  }

  public String emailSubjectChangeEmail(String lang, String app_name) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = app_name + " - Ubah Email";
    } else if (lang.toLowerCase().equals("en")) {
      result = app_name + " - Change Email";
    }

    return result;
  }

  public String emailSubjectAspiration(String lang, String app_name) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = app_name + " - " + "Aspirasi";
    } else if (lang.toLowerCase().equals("en")) {
      result = app_name + " - " + "Aspiration";
    }

    return result;
  }

  public Map emailRegistration(String lang) {
    Map result = new LinkedHashMap();

    if (lang.toLowerCase().equals("id")) {
      result.put("title", "Selamat Datang");
      result.put("content_1", "Terima kasih telah melakukan pendaftaran.");
      result.put("content_2", "Silahkan klik link di bawah ini untuk proses verifikasi.");
      result.put("activation_button", "AKTIFKAN AKUN");
    } else if (lang.toLowerCase().equals("en")) {
      result.put("title", "Welcome");
      result.put("content_1", "Thank you for registering.");
      result.put("content_2", "Please click the link below for the verification process.");
      result.put("activation_button", "ACTIVATE ACCOUNT");
    }

    return result;
  }

  public Map emailForgotPassword(String lang) {
    Map result = new LinkedHashMap();

    if (lang.toLowerCase().equals("id")) {
      result.put("title", "Selamat Datang");
      result.put("content_1", "Untuk melakukan reset kata sandi,");
      result.put("content_2", "Silahkan klik link di bawah ini.");
      result.put("activation_button", "RESET KATA SANDI");
    } else if (lang.toLowerCase().equals("en")) {
      result.put("title", "Welcome");
      result.put("content_1", "To reset the password,");
      result.put("content_2", "Please click the link below.");
      result.put("activation_button", "RESET PASSWORD");
    }

    return result;
  }

  public Map emailChangeEmail(String lang) {
    Map result = new LinkedHashMap();

    if (lang.toLowerCase().equals("id")) {
      result.put("title", "Selamat Datang");
      result.put("content_1", "Untuk melakukan ubah email,");
      result.put("content_2", "Silahkan klik link di bawah ini.");
      result.put("text_button", "UBAH EMAIL");
    } else if (lang.toLowerCase().equals("en")) {
      result.put("title", "Welcome");
      result.put("content_1", "To change your email,");
      result.put("content_2", "Please click the link below.");
      result.put("text_button", "CHANGE EMAIL");
    }

    return result;
  }

  public Map emailAspiration(String lang, String tanggal, String nama, String aspiration_id, String type, String desc) {
    Map result = new LinkedHashMap();

    if (type.equals("user")) {
      if (lang.toLowerCase().equals("id")) {
        result.put("title_1", "Terima Kasih");
        result.put("title_2", "anda telah menggunakan aplikasi smartcity.");
        result.put("content_1", "Keluhan anda dengan");
        result.put("content_2", "No. Aspirasi&#160;&#160;&#160;&#160;&#160;: " + aspiration_id);
        result.put("content_3", "Deskripsi&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;: " + desc);
        result.put("content_4", "Tanggal&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;: " + tanggal);
        result.put("content_5", "telah berhasil disimpan.");
        result.put("content_6", "Petugas kami akan segera merespon keluhan anda.");
      } else if (lang.toLowerCase().equals("en")) {
        result.put("title_1", "Thank You");
        result.put("title_2", "for using the smartcity application.");
        result.put("content_1", "Your Aspiration with : ");
        result.put("content_2", "Aspiration ID&#160;&#160;&#160;&#160;&#160;: " + aspiration_id);
        result.put("content_3", "Description&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;: " + desc);
        result.put("content_4", "has been saved successfully.");
        result.put("content_5", "Our officers will immediately respond to your aspiration.");
      }
    } else {
      if (lang.toLowerCase().equals("id")) {
        result.put("content_1", "User berikut telah melaporkan aspirasi pada aplikasi smartcity.");
        result.put("content_2", "Tanggal&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; :" + tanggal);
        result.put("content_3", "Nama&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;: " + nama);
        result.put("content_4", "No. Aspirasi&#160;&#160;&#160;&#160;&#160;&#160;: " + aspiration_id);
        result.put("content_5", "Deskripsi&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;: " + desc);
        result.put("content_6", "untuk lebih jelas silahkan masuk ke halaman smartcity monitoring.");
        result.put("content_7", "Terima kasih.");
      } else if (lang.toLowerCase().equals("en")) {
        result.put("title_1", "Thank You");
        result.put("title_2", "for using the smartcity application.");
        result.put("content_1", "Your Aspiration with : ");
        result.put("content_2", "Aspiration ID&#160;&#160;&#160;&#160;&#160;: " + aspiration_id);
        result.put("content_3", "Description&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;: " + desc);
        result.put("content_4", "has been saved successfully.");
        result.put("content_5", "Our officers will immediately respond to your aspiration.");
      }
    }

    return result;
  }

  public Map emailAspirationProgress(String lang, int u_id, String nama, String aspiration_id,
      String desc, String response, String status, String tanggal, String type) {
    Map result = new LinkedHashMap();
    if (type.equals("user")) {
      if (lang.toLowerCase().equals("id")) {
        result.put("title_1", "Terima Kasih");
        result.put("content_1", "Anda telah menggunakan Aplikasi SmartCity.");
        result.put("content_2", "Keluhan anda,");
        result.put("content_3", "No. Aspirasi&#160;&#160;&#160;&#160;&#160; :" + aspiration_id);
        result.put("content_4", "Deskripsi&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; : " + desc);
        result.put("content_5", "sedang diproses oleh petugas kami.");
        result.put("content_6", "Untuk lebih jelas silahkan masuk ke aplikasi smartcity.");
      } else if (lang.toLowerCase().equals("en")) {
        result.put("title_1", "Thank You");
        result.put("content_1", "for using the SmartCity Application.");
        result.put("content_2", "Your Aspiration,");
        result.put("content_3", "Aspiration&#160; &#160; &#160; &#160; &#160; : " + desc);
        result.put("content_4", "is being processed by our officers.");
        result.put("content_5", "Please check the details in the SmartCity Application.");
      }
    } else {
      if (lang.toLowerCase().equals("id")) {
        result.put("content_1", "Petugas OPD");
        result.put("content_2", "Nama&#160;&#160;&#160;&#160;&#160;&#160; : " + nama);
        result.put("content_3", "Tanggal&#160;&#160;&#160; : " + tanggal);
        result.put("content_4", "Telah menanggapi keluhan");
        result.put("content_5", "No. Aspirasi&#160;&#160;&#160;&#160;&#160; :" + aspiration_id);
        result.put("content_6", "Deskripsi&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; : " + desc);
        result.put("content_7", "Response&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; : " + response);
        result.put("content_8", "Status&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; : " + status);
        result.put("content_9", "Untuk lebih jelas silahkan masuk ke halaman smartcity monitoring.");
        result.put("content_10", "Terima kasih.");
      } else if (lang.toLowerCase().equals("en")) {
        result.put("title_1", "Thank You");
        result.put("content_1", "for using the SmartCity Application.");
        result.put("content_2", "Your Aspiration,");
        result.put("content_3", "Aspiration&#160; &#160; &#160; &#160; &#160; : " + desc);
        result.put("content_4", "is being processed by our officers.");
        result.put("content_5", "Please check the details in the SmartCity Application.");
      }
    }

    return result;
  }

  public String smsContentRegist(String lang, String app_name, String activation_code) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = "Token pendaftaran " + app_name + " anda sbb :  " + activation_code;
    } else if (lang.toLowerCase().equals("en")) {
      result = "Your " + app_name + " registration token is " + activation_code;
    }

    return result;
  }

  public String smsContentForgotPassword(String lang, String app_name, String activation_code) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = "Token ubah kata sandi " + app_name + " anda sbb : " + activation_code;
    } else if (lang.toLowerCase().equals("en")) {
      result = "Your " + app_name + " change password token is " + activation_code;
    }

    return result;
  }

  public String smsContentChangePhone(String lang, String app_name, String activation_code) {
    String result = "";

    if (lang.toLowerCase().equals("id")) {
      result = "Token Validasi " + app_name + " anda sbb :  " + activation_code;
    } else if (lang.toLowerCase().equals("en")) {
      result = "Your " + app_name + " validation token is " + activation_code;
    }

    return result;
  }

}
