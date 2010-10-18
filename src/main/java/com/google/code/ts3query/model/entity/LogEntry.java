package com.google.code.ts3query.model.entity;

import java.util.Date;

public class LogEntry {

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
