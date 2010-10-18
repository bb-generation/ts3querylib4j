package com.google.code.ts3query.model.entity;

import java.util.Date;

import com.google.code.ts3query.model.Entity;

public class LogEntry extends Entity {

	private Date timestamp;
	private int level;
	private String msg;
	private String channel;

	public Date getTimestamp() {
		return timestamp;
	}

	public int getLevel() {
		return level;
	}

	public String getChannel() {
		return channel;
	}

	public String getMsg() {
		return msg;
	}
}
