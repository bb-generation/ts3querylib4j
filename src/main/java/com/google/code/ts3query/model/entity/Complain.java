package com.google.code.ts3query.model.entity;

import java.util.Date;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.Complains;

public class Complain extends ManagedEntity<Complains> {

	private long fcldbid;
	private String fname;

	private long tcldbid;
	private String tname;

	private Date timestamp;
	private String message;

	public long getFromClientDBID() {
		return fcldbid;
	}

	public String getFromName() {
		return fname;
	}

	public long getTargetClientDBID() {
		return tcldbid;
	}

	public String getTargetName() {
		return tname;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void delete() {
		manager.delete(tcldbid, fcldbid);
	}

}
