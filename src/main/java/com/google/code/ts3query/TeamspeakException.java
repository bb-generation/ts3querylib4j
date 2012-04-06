package com.google.code.ts3query;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An exception caused while executing a server query command on the server.
 */
public class TeamspeakException extends IOException {

	private final int id;
	private final String msg;
	private final String extra_msg;

	protected TeamspeakException(int id, String msg, String extra_msg) {
		this.id = id;
		this.msg = msg;
		this.extra_msg = extra_msg;
	}

	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}
	
	public String getExtraMsg() {
		return extra_msg;
	}
	
	/**
	 * @return full error message containing {@link #id}, {@link #msg} and if
	 *         available {@link #extra_msg}.
	 */
	@Override
	public String getMessage() {
		String message = "#" + id + ": " + msg;
		if (StringUtils.isNotEmpty(extra_msg)) {
			return message + " (" + extra_msg + ")";
		} else {
			return message;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("msg", getMessage()).toString();
	}
}
