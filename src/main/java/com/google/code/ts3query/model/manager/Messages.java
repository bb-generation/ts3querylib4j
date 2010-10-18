package com.google.code.ts3query.model.manager;

import java.util.Iterator;
import java.util.List;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.Message;

public class Messages extends Entity implements Iterable<Message> {

	/**
	 * Sends an offline message to the client specified by cluid.
	 * 
	 * @param clientUID
	 *            recipient
	 * @param subject
	 * @param message
	 */
	public void add(final String clientUID, final String subject, final String message) {
		send(command("messageadd").with("cluid", clientUID).with("subject", subject).with("message", message));
	}

	/**
	 * Deletes an existing offline message with ID msgid from your inbox.
	 * 
	 * @param messageID
	 */
	public void delete(final int messageID) {
		send(command("messagedel").with("msgid", messageID));
	}

	/**
	 * Displays an existing offline message with ID msgid from your inbox.
	 * Please note that this does not automatically set the flag_read property
	 * of the message.
	 * 
	 * @param messageID
	 */
	public Message get(final int messageID) {
		return send(command("messageget").with("msgid", messageID)).as(Message.class);
	}

	/**
	 * Displays a list of offline messages you've received. The output contains
	 * the senders unique identifier, the messages subject, etc.
	 */
	public List<Message> getList() {
		return send(command("messagelist")).asList(Message.class);
	}

	@Override
	public Iterator<Message> iterator() {
		return getList().iterator();
	}

	/**
	 * Updates the flag_read property of the offline message specified with
	 * msgid. If flag is set to 1, the message will be marked as read.
	 * 
	 * @param messageID
	 * @param read
	 */
	public void updateFlag(final int messageID, final boolean read) {
		// TODO handle boolean in with()?
		send(command("messageupdateflag").with("msgid", messageID).with("flag", read ? 1 : 0));
	}
}
