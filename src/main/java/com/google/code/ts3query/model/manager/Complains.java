package com.google.code.ts3query.model.manager;

import java.util.Iterator;
import java.util.List;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.Complain;

public class Complains extends Entity implements Iterable<Complain> {

  /**
   * Submits a complaint about the client with database ID tcldbid to the
   * server.
   * 
   * @param targetClientDBID
   * @param message
   */
  public void add(long targetClientDBID, String message) {
    send(command("complainadd").with("tcldbid", targetClientDBID).with("message", message));
  }

  /**
   * Deletes the complaint about the client with database ID tcldbid submitted
   * by the client with database ID fcldbid from the server.
   * 
   * @param targetClientDBID
   * @param fromClientDBID
   */
  public void delete(long targetClientDBID, long fromClientDBID) {
    send(command("complaindel").with("tcldbid", targetClientDBID).with("fcldbid", fromClientDBID));
  }

  /**
   * Deletes all complaints about the client with database ID tcldbid from the
   * server.
   * 
   * @param targetClientDBID
   */
  public void deleteAll(long targetClientDBID) {
    send(command("complaindelall").with("tcldbid", targetClientDBID));
  }

  public List<Complain> getList() {
    return getList(null);
  }

  public List<Complain> getList(Long targetClientDBID) {
    return send(command("complainlist").with("tcldbid", targetClientDBID)).asManagedList(
        Complain.class, this);
  }

  @Override
  public Iterator<Complain> iterator() {
    return getList().iterator();
  }
}
