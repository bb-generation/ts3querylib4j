package com.google.code.ts3query;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TeamspeakConnection implements Closeable {

	private static final Logger log = Logger.getLogger(TeamspeakConnection.class.getName());

	/**
	 * The signature line sent by the server to verify the connection.
	 */
	private static final String SERVER_SIGNATURE = "TS3";

	/**
	 * The various escape sequences for (un)escaping string values.
	 */
	private static final String[][] ESCAPES = {
	// from the TeamSpeak 3 ServerQuery Manual (2009-12-26)
			{ "\\", "\\\\" }, // backslash
			{ "/", "\\/" }, // slash
			{ " ", "\\s" }, // whitespace
			{ "|", "\\p" }, // pipe
			{ "\007", "\\a" }, // bell (octal for unsupported \a)
			{ "\b", "\\b" }, // backsapce
			{ "\f", "\\f" }, // formfeed
			{ "\n", "\\n" }, // newline
			{ "\r", "\\r" }, // carriage return
			{ "\t", "\\t" }, // horizontal tab
			{ "\013", "\\v" }, // vertical tab (octal for unsupported \v)
	};

	/**
	 * The default server query port.
	 */
	public static final int DEFAULT_PORT = 10011;

	private final Socket socket;
	private final BufferedReader input;
	private final PrintStream output;

	/**
	 * Creates a new connection to a host on the default server query port
	 * {@value #DEFAULT_PORT}.
	 * 
	 * @param host
	 *            the host to connect to
	 * @throws IOException
	 */
	public TeamspeakConnection(String host) throws IOException {
		this(host, DEFAULT_PORT);
	}

	/**
	 * Creates a new connection to a host on a custom server query port.
	 * 
	 * @param host
	 *            the host to connect to
	 * @param port
	 *            a custom server query port
	 * @throws IOException
	 */
	public TeamspeakConnection(String host, int port) throws IOException {
		this.socket = new Socket(host, port);
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.output = new PrintStream(socket.getOutputStream());

		// verify server signature
		String sig = input.readLine();
		if (!SERVER_SIGNATURE.equals(sig)) {
			throw new IOException("Invalid server signature: " + sig);
		}
	}

	/**
	 * Sends a command to the server and parses the response as either
	 * {@link TeamspeakResponse} or {@link TeamspeakException}.
	 * 
	 * @param command
	 *            the command to send to the server
	 * @return the response by the server upon successful execution
	 * @throws IOException
	 *             an exception upon failed execution
	 * @throws TeamspeakException
	 */
	public synchronized TeamspeakResponse execute(TeamspeakCommand command) throws IOException,
			TeamspeakException {
		output.println(command);

		List<SortedMap<String, String>> result = null;

		String line;
		while ((line = input.readLine()) != null) {
			if (StringUtils.isEmpty(line)) {
				continue;
			}

			// System.err.println("<< " + line);

			List<SortedMap<String, String>> parsed = parseLine(line);

			Map<String, String> first = parsed.get(0);
			if (first.containsKey("error")) {
				int id = Integer.parseInt(first.get("id"));
				if (id == 0) {
					break; // no error, stop reading
				} else {
					// error, throw an exception
					TeamspeakException ex = new TeamspeakException(id, first.get("msg"), first.get("extra_msg"));
					log.log(Level.SEVERE, String.valueOf(command), ex);
					throw ex;
				}
			} else {
				result = parsed;
			}
		}

		if (result == null) {
			return null;
		} else {
			return new TeamspeakResponse(result);
		}
	}

	/**
	 * Parses a line of "block|block|block" into a list.
	 */
	private List<SortedMap<String, String>> parseLine(String line) {
		List<SortedMap<String, String>> blocks = new ArrayList<SortedMap<String, String>>();
		for (String block : line.split("\\|")) {
			blocks.add(parseBlock(block));
		}
		return blocks;
	}

	/**
	 * Parses a block of "a=b c=d" into a map.
	 */
	private SortedMap<String, String> parseBlock(String block) {
		SortedMap<String, String> params = new TreeMap<String, String>();
		for (String param : block.split(" ")) {
			if (param.contains("=")) {
				String[] tokens = param.split("=");
				String key = tokens[0];
				String value = unescape(tokens[1]);
				params.put(key, value);
			} else {
				params.put(param, null);
			}
		}
		return params;
	}

	/**
	 * Escapes a string for sending it to the server.
	 * 
	 * @param input
	 *            the plain/unescaped input string
	 * @return the escaped string
	 */
	public static String escape(String input) {
		if (input == null) {
			return null;
		}

		for (String[] escape : ESCAPES) {
			input = input.replace(escape[0], escape[1]);
		}
		return input;
	}

	/**
	 * Unescapes a string received from the server.
	 * 
	 * @param input
	 *            the escaped input string
	 * @return the plain/unescaped string
	 */
	public static String unescape(String input) {
		if (input == null) {
			return null;
		}

		for (String[] escape : ESCAPES) {
			input = input.replace(escape[1], escape[0]);
		}
		return input;
	}

	/**
	 * Closes the connection and releases any resources.
	 */
	@Override
	public void close() throws IOException {
		closeQuietly(input);
		closeQuietly(output);
		if (socket != null) {
			socket.close();
		}
	}

	private void closeQuietly(Closeable c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (IOException e) {
			// ignore
		}
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("host", socket.getInetAddress()).append("port", socket.getPort())
				.toString();
	}
}
