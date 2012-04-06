package com.google.code.ts3query.model.entity;

import java.util.Date;

import com.google.code.ts3query.model.Entity;

public class ClientIdentity extends Entity {

  private String client_unique_identifier; // client UID
  private int cldbid; // client database ID

  private Date client_created;
  private Date client_lastconnected;

  private String client_nickname;
  private String client_description;

  public Date getCreated() {
    return client_created;
  }

  public int getDatabaseId() {
    return cldbid;
  }

  public String getUid() {
    return client_unique_identifier;
  }

  public Date getLastConnected() {
    return client_lastconnected;
  }

  public String getNickname() {
    return client_nickname;
  }

  public String getDescription() {
    return client_description;
  }

}
