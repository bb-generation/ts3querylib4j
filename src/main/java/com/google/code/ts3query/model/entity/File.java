package com.google.code.ts3query.model.entity;

import java.util.Date;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.FileTransfers;
import com.google.code.ts3query.option.FileType;

public class File extends ManagedEntity<FileTransfers> {

  private Date datetime;
  private String name;
  private long size;
  private FileType type;

  public Date getDatetime() {
    return datetime;
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    return size;
  }

  public FileType getType() {
    return type;
  }

  public void delete() {
    // TODO where to get channel ID and password from?
    // manager.deleteFile(channelID, channelPassword, filePath)
  }
}
