package com.google.code.ts3query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;

import com.google.code.ts3query.model.ManagedEntity;

/**
 * A response by the server upon successful execution of a command.
 */
public class TeamspeakResponse implements Iterable<SortedMap<String, String>> {

	private final List<SortedMap<String, String>> response;

	/**
	 * Creates a new response from the parsed data received from a server.
	 * 
	 * @param response
	 *            the parsed data received from a server.
	 */
	protected TeamspeakResponse(final List<SortedMap<String, String>> response) {
		this.response = Collections.unmodifiableList(response);
	}

	public List<SortedMap<String, String>> getResponse() {
		return response;
	}

	public Map<String, String> getFirstResponse() {
		return response.get(0);
	}

	@Override
	public Iterator<SortedMap<String, String>> iterator() {
		return response.iterator();
	}

	@Override
	public String toString() {
		if (response == null) {
			return null;
		}

		final StringBuilder string = new StringBuilder();
		for (final Map<String, String> line : response) {
			for (final Entry<String, String> entry : line.entrySet()) {
				string.append(entry.getKey()).append("=").append(entry.getValue()).append("\r\n");
			}
			string.append("--------").append("\r\n");
		}

		return string.toString();
	}

	/**
	 * Converts this response into a list of instances of a model class.
	 * 
	 * @param <T>
	 *            the type of the model class
	 * @param clazz
	 *            the model class
	 * 
	 * @param keysToIgnore
	 *            name of keys to ignore when popuating the object
	 * 
	 * @return a list of instances of the model class populated with data from
	 *         this response
	 */
	public <T> List<T> asList(final Class<T> clazz, final String... keysToIgnore) {
		final List<T> result = new ArrayList<T>(response == null ? 0 : response.size());

		if (response == null) {
			return result;
		}

		for (final Map<String, String> entries : response) {
			result.add(create(clazz, entries, keysToIgnore));
		}

		return result;
	}

	public <E extends ManagedEntity<Manager>, Manager> List<E> asManagedList(final Class<E> clazz, final Manager manager, final String... keysToIgnore) {
		final List<E> result = new ArrayList<E>(response == null ? 0 : response.size());

		if (response == null) {
			return result;
		}

		for (final Map<String, String> entries : response) {
			final E entity = create(clazz, entries, keysToIgnore);
			result.add(ManagedEntity.manage(entity, manager));
		}

		return result;
	}

	/**
	 * Converts this <em>single-line</em> response into an instance of a model
	 * class.
	 * 
	 * @throws IllegalStateException
	 *             if this response has more than one line
	 * @param <T>
	 *            the type of the model class
	 * @param clazz
	 *            the model class
	 * @param keysToIgnore
	 *            name of keys to ignore when popuating the object
	 * 
	 * @return an instance of the model class populated with data from this
	 *         response
	 */
	public <T> T as(final Class<T> clazz, final String... keysToIgnore) {
		if (response == null) {
			return null;
		}

		final int lines = response.size();
		if (lines > 1) {
			throw new IllegalStateException("There are  " + lines + " lines");
		}
		return create(clazz, getFirstResponse(), keysToIgnore);
	}

	/**
	 * Creates an instance of a class and populates its fields using reflection.
	 * 
	 * @param <T>
	 *            the type of the class
	 * @param clazz
	 *            the class
	 * @param entries
	 *            a mapping of field names to values
	 * @param keysToIgnore
	 *            name of keys to ignore when popuating the object
	 * 
	 * @return an instance of the class populated with the provided data
	 */
	private <T> T create(final Class<T> clazz, final Map<String, String> entries, final String... keysToIgnore) {
		try {
			return populate(clazz.newInstance(), entries, keysToIgnore);
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Populates the fields of an object using a mapping of field names to
	 * values.
	 * <p>
	 * Data types supported by the server query protocol will be automatically
	 * parsed/casted into fields of the appropriate type ({@link String} , int,
	 * long, boolean, {@link Date}).
	 * 
	 * @param <T>
	 *            the type of the object
	 * @param obj
	 *            the object to populate
	 * @param entries
	 *            the mapping of field names to values
	 * 
	 * @param keysToIgnore
	 *            name of keys to ignore when popuating the object
	 * 
	 * @return the populated object
	 * 
	 * @throws IllegalArgumentException
	 *             a field cannot be populated
	 * @throws Exception
	 *             when reflection doesn't work as expected
	 */
	private <T> T populate(final T obj, final Map<String, String> entries, final String... keysToIgnore)
			throws Exception {
		final Class<?> clazz = obj.getClass();
		for (final Entry<String, String> entry : entries.entrySet()) {
			final String key = entry.getKey();
			final String value = entry.getValue();

			if (ArrayUtils.contains(keysToIgnore, key)) {
				continue;
			}

			final Field field = clazz.getDeclaredField(key);
			field.setAccessible(true);

			final Class<?> type = field.getType();
			if (type.equals(String.class)) {
				field.set(obj, value);
			} else if (type.equals(Integer.TYPE)) {
				field.setInt(obj, Integer.parseInt(value));
			} else if (type.equals(Long.TYPE)) {
				field.setLong(obj, Long.parseLong(value));
			} else if (type.equals(Boolean.TYPE)) {
				// 1=true, 0=false
				field.setBoolean(obj, Integer.parseInt(value) == 1);
			} else if (type.equals(Date.class)) {
				// value is seconds since epoch
				field.set(obj, new Date(Long.parseLong(value) * 1000));
			} else {
				throw new IllegalArgumentException("Field " + field.getName() + " in class " + clazz
						+ " has unknown type " + type + " for value " + value);
			}
		}
		return obj;
	}
}
