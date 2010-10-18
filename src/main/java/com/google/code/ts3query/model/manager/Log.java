package com.google.code.ts3query.model.manager;

import java.util.List;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.LogEntry;
import com.google.code.ts3query.option.LogLevel;

public class Log extends Entity {

	/**
	 * Writes a custom entry into the servers log. Depending on your
	 * permissions, you'll be able to add entries into the server instance log
	 * and/or your virtual servers log. The loglevel parameter specifies the
	 * type of the entry.
	 * 
	 * @param loglevel
	 * @param logmsg
	 */
	public void add(final LogLevel level, final String logmsg) {
		send(command("logadd").with("loglevel", 1 + level.ordinal()).with("logmsg", logmsg));
	}

	/**
	 * Displays a specified number of entries from the servers log. Depending on
	 * your permissions, you'll receive entries from the server instance log
	 * and/or your virtual server log. Using a combination of the comparator and
	 * timestamp parameters allows you to filter for log entries based on a
	 * specific date/time.
	 * 
	 * @param limitcount
	 */
	public List<LogEntry> getList(final int limitcount) {
		return send(command("logview").with("limitcount", limitcount)).asList(LogEntry.class);
	}

}
