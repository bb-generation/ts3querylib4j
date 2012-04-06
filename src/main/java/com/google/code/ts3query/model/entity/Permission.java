package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.Entity;

public class Permission extends Entity {

  private int permid;
  private String permname;
  private String permdesc;

  public int getId() {
    return permid;
  }

  public String getName() {
    return permname;
  }

  public String getDescription() {
    return permdesc;
  }
}
