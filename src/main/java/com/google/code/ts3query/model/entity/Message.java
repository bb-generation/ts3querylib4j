package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.manager.Messages;

public class Message extends Entity {

	private transient Messages messages;

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
		messages.del(msgid);
	}

	public void setFlag(final boolean read) {
		messages.updateFlag(msgid, read);
	}
}
