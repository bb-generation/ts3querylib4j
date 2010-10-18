package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.Entity;

public class Client extends Entity {

	private int client_type;
	private int clid;
	private int client_database_id;
	private String client_nickname;
	private int cid;

	public int getType() {
		return client_type;
	}

	public int getClientId() {
		return clid;
	}

	public int getDatabaseId() {
		return client_database_id;
	}

	public String getNickname() {
		return client_nickname;
	}

	public int getChannelId() {
		return cid;
	}

}
