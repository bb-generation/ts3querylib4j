package com.google.code.ts3query.model.entity;

import java.util.Date;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.Bans;

public class Ban extends ManagedEntity<Bans> {

  private int banid;
  private Date created;
  private int enforcements;
  private int invokercldbid;
  private String invokername;
  private String invokeruid;
  private String name;
  private String reason;

  public int getBanId() {
    return banid;
  }

  public Date getCreated() {
    return created;
  }

  public int getEnforcements() {
    return enforcements;
  }

  public int getInvokerClientDatabaseId() {
    return invokercldbid;
  }

  public String getInvokerName() {
    return invokername;
  }

  public String getInvokerUID() {
    return invokeruid;
  }

  public String getName() {
    return name;
  }

  public String getReason() {
    return reason;
  }

  public void delete() {
    manager.delete(getBanId());
  }
}
