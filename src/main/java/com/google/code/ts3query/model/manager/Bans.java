package com.google.code.ts3query.model.manager;

import java.util.Iterator;
import java.util.List;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.Ban;

public class Bans extends Entity implements Iterable<Ban> {

  /**
   * Displays a list of active bans on the selected virtual server.
   */
  public List<Ban> getList() {
    return send(command("banlist")).asList(Ban.class);
  }

  @Override
  public Iterator<Ban> iterator() {
    return getList().iterator();
  }

  /**
   * Bans the client specified with ID clid from the server.
   * 
   * @param clientID
   * @param timeInSeconds
   *          optional, <code>null</code> to skip
   * @param reason
   *          optional, <code>null</code> to skip
   */
  public void ban(int clientID, Long timeInSeconds, String reason) {
    send(command("banclient")
        .with("clid", clientID)
        .with("time", timeInSeconds)
        .with("banreason", reason));
  }

  /**
   * Adds a new ban rule on the selected virtual server. All parameters are
   * optional but at least one of the following must be set: ip, name, or uid.
   * 
   * @param ipRegex
   * @param nameRegex
   * @param clientUID
   * @param timeInSeconds
   * @param reason
   */
  public void add(String ipRegex, String nameRegex, String clientUID, Long timeInSeconds,
      String reason) {
    send(command("banadd")
        .with("ip", ipRegex)
        .with("name", nameRegex)
        .with("uid", clientUID)
        .with("time", timeInSeconds)
        .with("banreason", reason));
  }

  /**
   * Deletes the ban rule with ID banid from the server.
   * 
   * @param banID
   */
  public void delete(int banID) {
    send(command("bandel").with("banid", banID));
  }

  /**
   * Deletes all active ban rules from the server.
   */
  public void deleteAll() {
    send(command("bandelall"));
  }
}
