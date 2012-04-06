package com.google.code.ts3query.model.entity;

public class WhoAmI {

  private int virtualserver_id;
  private String client_unique_identifier;
  private String virtualserver_unique_identifier;
  private String virtualserver_status;
  private String client_login_name;
  private int client_database_id;
  private String client_nickname;
  private int client_id;
  private int client_channel_id;

  public int getVirtualServerId() {
    return virtualserver_id;
  }

  public String getClientUniqueIdentifier() {
    return client_unique_identifier;
  }

  public String getVirtualServerUniqueIdentifier() {
    return virtualserver_unique_identifier;
  }

  public String getVirtualServerStatus() {
    return virtualserver_status;
  }

  public String getClientLoginName() {
    return client_login_name;
  }

  public int getClientDatabaseId() {
    return client_database_id;
  }

  public String getClientNickname() {
    return client_nickname;
  }

  public int getClientId() {
    return client_id;
  }

  public int getClientChannelId() {
    return client_channel_id;
  }
}
