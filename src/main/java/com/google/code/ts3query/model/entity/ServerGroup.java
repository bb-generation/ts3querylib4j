package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.Entity;

public class ServerGroup extends Entity {

  private int sgid;
  private String name;
  private int iconid;
  private boolean savedb;
  private int type;

  public int getId() {
    return sgid;
  }

  public String getName() {
    return name;
  }

  public int getIconId() {
    return iconid;
  }

  public boolean isSavedb() {
    return savedb;
  }

  public int getType() {
    return type;
  }

}
