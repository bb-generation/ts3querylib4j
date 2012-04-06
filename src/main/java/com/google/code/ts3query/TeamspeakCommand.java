package com.google.code.ts3query;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * A server query command consisting of a keyword, any number of parameters and
 * any number of options.
 */
public class TeamspeakCommand {

	private final String keyword;
	private final Map<String, Object> parameters;
	private final Set<Object> options;

	/**
	 * Creates a new command with a keyword.
	 * 
	 * @param keyword
	 *            the keyword
	 */
	public TeamspeakCommand(String keyword) {
		this.keyword = keyword;
		this.parameters = new HashMap<String, Object>();
		this.options = new HashSet<Object>();
	}

	/**
	 * Adds a parameter to this command if neither key nor value are
	 * <code>null</code>.
	 * 
	 * @param key
	 *            a non-null key
	 * @param value
	 *            a non-null value
	 * @return this command for chaining
	 */
	public TeamspeakCommand with(String key, Object value) {
		if (key == null || value == null) {
			return this;
		}
		parameters.put(key, value);
		return this;
	}

	/**
	 * Adds all parameters from the map, if the map is not <code>null</code>.
	 * Behaves like calling {@link #with(String, Object)} with every entry of
	 * the map.
	 * 
	 * @param <V>
	 * @param parameters
	 *            a non-null map
	 * @return this for command chaining
	 */
	public <V extends Object> TeamspeakCommand with(Map<String, V> parameters) {
		if (parameters != null) {
			for (Entry<String, V> entry : parameters.entrySet()) {
				with(entry.getKey(), entry.getValue());
			}
		}
		return this;
	}

	/**
	 * Adds any number of options to this command if it isn't <code>null</code>
	 * 
	 * @param options
	 *            a non-null option
	 * @return this command for chaining
	 */
	public TeamspeakCommand option(Object... options) {
		if (options == null) {
			return this;
		}
		Collections.addAll(this.options, options);
		return this;
	}

	/**
	 * Renders this command for transfer to the query server.
	 */
	@Override
	public String toString() {
		StringBuilder command = new StringBuilder(keyword);
		for (Entry<String, Object> param : parameters.entrySet()) {
			String key = param.getKey();
			Object value = param.getValue();

			if (value.getClass().isArray()) {
				int length = Array.getLength(value);
				for (int i = 0; i < length; i++) {
					String v = TeamspeakConnection.escape(String.valueOf(Array.get(value, i)));
					command.append(i == 0 ? ' ' : '|').append(key).append('=').append(v);
				}
			} else {
				String v = TeamspeakConnection.escape(String.valueOf(value));
				command.append(' ').append(key).append('=').append(v);
			}
		}

		for (Object option : options) {
			command.append(' ').append('-').append(TeamspeakConnection.escape(String.valueOf(option)));
		}

		return command.toString();
	}
}
