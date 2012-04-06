package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.FileTransfers;

public class FileTransfer extends ManagedEntity<FileTransfers> {

  private long clid;
  private long cldbid;
  private String path;
  private String name;
  private long size;
  private long sizedone;
  private long clientftfid;
  private long serverftfid;
  private long sender;
  private long status;
  private double current_speed;
  private double average_speed;
  private long runtime;

  public long getClientId() {
    return clid;
  }

  public long getClientDatabaseId() {
    return cldbid;
  }

  public String getPath() {
    return path;
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    return size;
  }

  public long getSizeDone() {
    return sizedone;
  }

  public long getClientFileTransferId() {
    return clientftfid;
  }

  public long getServerFileTransferId() {
    return serverftfid;
  }

  public long getSender() {
    return sender;
  }

  public long getStatus() {
    return status;
  }

  public double getCurrentSpeed() {
    return current_speed;
  }

  public double getAverageSpeed() {
    return average_speed;
  }

  public long getRuntime() {
    return runtime;
  }

  /**
   * Stops this running file transfer.
   * 
   * @param delete
   *          <code>true</code> to delete the file on the server
   */
  public void stop(boolean delete) {
    manager.stop(getServerFileTransferId(), delete);
  }
}
