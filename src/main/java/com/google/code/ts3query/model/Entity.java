package com.google.code.ts3query.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.ts3query.TeamspeakCommand;
import com.google.code.ts3query.TeamspeakProtocol;
import com.google.code.ts3query.TeamspeakResponse;

public abstract class Entity {

	protected transient TeamspeakProtocol server;

	/**
	 * Sets up an entity to be served by a server.
	 * 
	 * @param <E>
	 *            the type of the entity
	 * @param entity
	 *            the entity instance to be served
	 * @param server
	 *            the server instance to serve
	 * @return the served entity
	 */
	public <E extends Entity> E serve(final E entity) {
		entity.server = server;
		return entity;
	}

	protected static TeamspeakCommand command(final String keyword) {
		return new TeamspeakCommand(keyword);
	}

	protected TeamspeakResponse send(final TeamspeakCommand command) {
		return server.send(command);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
