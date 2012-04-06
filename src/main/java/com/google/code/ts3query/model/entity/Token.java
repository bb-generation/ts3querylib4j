package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.Tokens;

public class Token extends ManagedEntity<Tokens> {

  private String token;
  private int token_id1;
  private int token_id2;
  private int token_type;

  public String getToken() {
    return token;
  }

  public int getId1() {
    return token_id1;
  }

  public int getId2() {
    return token_id2;
  }

  public int getType() {
    return token_type;
  }

  public void delete() {
    manager.delete(getToken());
  }

  public void use() {
    manager.use(getToken());
  }
}
