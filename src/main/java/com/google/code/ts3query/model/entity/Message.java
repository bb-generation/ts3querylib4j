package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.Messages;

public class Message extends ManagedEntity<Messages> {

	private int msgid;
	private String cluid;
	private String subject;
	private String message;

	public int getId() {
		return msgid;
	}

	public String getClientUID() {
		return cluid;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public void delete() {
		manager.delete(msgid);
	}

	public void setFlag(boolean read) {
		manager.updateFlag(msgid, read);
	}
}
