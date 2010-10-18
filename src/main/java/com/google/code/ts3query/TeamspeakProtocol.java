package com.google.code.ts3query;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class TeamspeakProtocol {

	public static TeamspeakCommand command(final String keyword) {
		return new TeamspeakCommand(keyword);
	}

	private final TeamspeakConnection server;

	public TeamspeakProtocol(final TeamspeakConnection server) {
		this.server = server;
	}

	/**
	 * Displays a list of IP addresses used by the server instance on
	 * multi-homed machines.
	 * 
	 * @return <code>ip=0.0.0.0</code>
	 */
	public TeamspeakResponse bindinglist() {
		return send(command("bindinglist"));
	}

	/**
	 * Deletes a clients properties from the database.
	 * 
	 * @param clientDBID
	 */
	public void clientdbdelete(final int clientDBID) {
		send(command("clientdbdelete").with("cldbid", clientDBID));
	}

	/**
	 * Changes a clients settings using given properties.
	 * 
	 * @param clientDBID
	 * @param parameters
	 */
	public void clientdbedit(final int clientDBID, final Map<String, ? extends Object> parameters) {
		send(command("clientdbedit").with("cldbid", clientDBID).with(parameters));
	}

	/**
	 * Displays a list of client identities known by the server including their
	 * database ID, last nickname, etc.
	 * 
	 * @return
	 * 
	 *         <code>cldbid=7 client_unique_identifier=DZhdQU58qyooEK4Fr8Ly738hEmc= client_nickname=MuhChy client_created=1259147468 client_lastconnected=1259421233</code>
	 */
	public TeamspeakResponse clientdblist() { // ClientIdentity
		return send(command("clientdblist"));
	}

	/**
	 * Removes a set of specified permissions from a client. Multiple
	 * permissions can be removed at once.
	 * 
	 * @param clientDBID
	 * @param permID
	 */
	public void clientdelperm(final int clientDBID, final int... permID) {
		send(command("clientdelperm").with("cldbid", clientDBID).with("permid", permID));
	}

	/**
	 * Changes a clients settings using given properties. For detailed
	 * information, see Client Properties.
	 * 
	 * @param clientID
	 * @param parameters
	 */
	public void clientedit(final int clientID, final Map<String, ? extends Object> parameters) {
		send(command("clientedit").with("clid", clientID).with(parameters));
	}

	/**
	 * Displays a list of clients matching a given name pattern.
	 * 
	 * @param clientName
	 * @return <code>clid=7 client_nickname=Sven</code>
	 */
	public TeamspeakResponse clientfind(final String clientName) {
		return send(command("clientfind").with("pattern", clientName));
	}

	/**
	 * Displays the database ID matching the unique identifier specified by
	 * cluid.
	 * 
	 * @param clientUID
	 * @return <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= cldbid=32</code>
	 */
	public TeamspeakResponse clientgetdbidfromuid(final String clientUID) {
		return send(command("clientgetdbidfromuid").with("cluid", clientUID));
	}

	/**
	 * Displays all client IDs matching the unique identifier specified by
	 * cluid.
	 * 
	 * @param clientUID
	 * @return <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= clid=1 name=Janko</code>
	 */
	public TeamspeakResponse clientgetids(final String clientUID) {
		return send(command("clientgetids").with("cluid", clientUID));
	}

	/**
	 * Displays the unique identifier and nickname matching the database ID
	 * specified by cldbid.
	 * 
	 * @param clientDBID
	 * @return
	 *         <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= cldbid=32 name=Janko</code>
	 */
	public TeamspeakResponse clientgetnamefromdbid(final int clientDBID) {
		return send(command("clientgetnamefromdbid").with("cldbid", clientDBID));
	}

	/**
	 * Displays the database ID and nickname matching the unique identifier
	 * specified by cluid.
	 * 
	 * @param clientUID
	 * @return
	 *         <code>cluid=dyjxkshZP6bz0n3bnwFQ1CkwZOM= cldbid=32 name=Janko</code>
	 */
	public TeamspeakResponse clientgetnamefromuid(final String clientUID) {
		return send(command("clientgetnamefromuid").with("cluid", clientUID));
	}

	/**
	 * Displays detailed configuration information about a client including
	 * unique ID, nickname, client version, etc.
	 * 
	 * @param clientId
	 * @return
	 * 
	 *         <code>client_unique_identifier=P5H2hrN6+gpQI4n\/dXp3p17vtY0= client_nickname=Rabe85 client_version=3.0.0-alpha24\s[Build:\s8785]\s(UI:\s8785) ...</code>
	 */
	public TeamspeakResponse clientinfo(final int clientId) {
		return send(command("clientinfo").with("clid", clientId));
	}

	/**
	 * Kicks one or more clients specified with clid from their currently joined
	 * channel or from the server, depending on reasonid. The reasonmsg
	 * parameter specifies a text message sent to the kicked clients. This
	 * parameter is optional and may only have a maximum of 40 characters.
	 * 
	 * @param reasonid
	 *            4=channel or 5=server
	 * @param reasonmsg
	 *            an optional message, use <code>null</code> to skip
	 * @param clientID
	 *            any number of client IDs to kick
	 */
	public void clientkick(final int reasonid, final String reasonmsg, final int... clientID) {
		// FIXME doesn't work right yet
		send(command("clientkick").with("reasonid", reasonid).with("reasonmsg", reasonmsg).with("clid", clientID));
	}

	/**
	 * Moves one or more clients specified with clid to the channel with ID cid.
	 * If the target channel has a password, it needs to be specified with cpw.
	 * If the channel has no password, the parameter can be omitted.
	 * 
	 * @param channelID
	 * @param channelPassword
	 *            optional channel password, <code>null</code> to skip
	 * @param clientID
	 */
	public void clientmove(final int channelID, final String channelPassword, final int... clientID) {
		send(command("clientmove").with("cid", channelID).with("cpw", channelPassword).with("clid", clientID));
	}

	/**
	 * Displays a list of permissions defined for a client.
	 * 
	 * @param clientDBID
	 * @return
	 * 
	 *         <code>cldbid=2 permid=4353 permvalue=1 permnegated=0 permskip=0|permid=17276 permvalue=50 permnegated=0 permskip=0|permid=21415 ...</code>
	 */
	public TeamspeakResponse clientpermlist(final int clientDBID) {
		return send(command("clientpermlist").with("cldbid", clientDBID));
	}

	/**
	 * Sends a poke message to the client specified with clid.
	 * 
	 * @param msg
	 * @param clid
	 */
	public void clientpoke(final String msg, final int... clid) {
		send(command("clientpoke").with("msg", msg).with("clid", clid));
	}

	/**
	 * Updates your own ServerQuery login credentials using a specified
	 * username. The password will be auto-generated.
	 * 
	 * @param username
	 * @return the auto-generated password
	 */
	public String clientsetserverquerylogin(final String username) {
		return send(command("clientsetserverquerylogin").with("client_login_name", username)).getFirstResponse().get(
				"client_login_password");
	}

	/**
	 * Change your ServerQuery clients settings using given properties.
	 * 
	 * @param parameters
	 */
	public void clientupdate(final Map<String, ? extends Object> parameters) {
		send(command("clientupdate").with(parameters));
	}

	/**
	 * Sends a text message to all clients on all virtual servers in the
	 * TeamSpeak 3 Server instance.
	 * 
	 * @param msg
	 */
	public void gm(final String msg) {
		send(command("gm").with("msg", msg));
	}

	/**
	 * Displays detailed configuration information about the server instance
	 * including uptime, number of virtual servers online, traffic information,
	 * etc.
	 * 
	 * @return
	 *         <code>instance_uptime=1903203 host_timestamp_utc=1259337246 virtualservers_running_total=1 connection_filetransfer_bandwidth_sent=0 ...</code>
	 */
	public TeamspeakResponse hostinfo() {
		return send(command("hostinfo"));
	}

	/**
	 * Injects this server instance into any fields of matching type in another
	 * object.
	 * 
	 * @param <T>
	 *            the type of the object
	 * @param object
	 *            the instance to inject this server into
	 * @return the object with this server injected
	 */
	public <T> T inject(final T object) {
		// look at both class and super class of object
		final Class<? extends Object> clazz = object.getClass();
		inject(object, clazz.getDeclaredFields());
		inject(object, clazz.getSuperclass().getDeclaredFields());
		return object;
	}

	private <T> void inject(final T object, final Field[] fields) {
		for (final Field field : fields) {
			if (field.getType().equals(getClass())) {
				try {
					field.setAccessible(true);
					field.set(object, this);
				} catch (final Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	/**
	 * Changes the server instance configuration using given properties.
	 * 
	 * @param parameters
	 */
	public void instanceedit(final Map<String, ? extends Object> parameters) {
		send(command("clientupdate").with(parameters));
	}

	/**
	 * Displays the server instance configuration including database revision
	 * number, the file transfer port, default group IDs, etc.
	 * 
	 * @return
	 * 
	 *         <code>serverinstance_database_version=11 serverinstance_filetransfer_port=30033 serverinstance_template_guest_serverquery_group=1 serverinstance_template_serveradmin_group=3...</code>
	 */
	public TeamspeakResponse instanceinfo() {
		return send(command("instanceinfo"));
	}

	/**
	 * Authenticates with the TeamSpeak 3 Server instance using given
	 * ServerQuery login credentials.
	 * 
	 * @param username
	 * @param password
	 */
	public void login(final String username, final String password) {
		send(command("login").with("client_login_name", username).with("client_login_password", password));
	}

	/**
	 * Deselects the active virtual server and logs out from the server
	 * instance.
	 */
	public void logout() {
		send(command("logout"));
	}

	/**
	 * Closes the ServerQuery connection to the TeamSpeak 3 Server instance.
	 */
	public void quit() {
		send(command("quit"));
	}

	public TeamspeakResponse send(final TeamspeakCommand command) {
		try {
			return server.execute(command);
		} catch (final IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Selects the virtual server specified with sid to allow further
	 * interaction. The ServerQuery client will appear on the virtual server and
	 * acts like a real TeamSpeak 3 Client, except it's unable to send or
	 * receive voice data.
	 * 
	 * @param serverId
	 *            serverID
	 */
	public void use(final int serverId) {
		send(command("use").with("sid", serverId));
	}

	/**
	 * Displays the servers version information including platform and build
	 * number.
	 * 
	 * @return <code>version=3.0.0-alpha03 build=8779 platform=Linux</code>
	 */
	public TeamspeakResponse version() {
		return send(command("version"));
	}

	/**
	 * Displays information about your current ServerQuery connection including
	 * the ID of the selected virtual server, your loginname, etc.
	 * 
	 * @return
	 *         <code>virtualserver_status=online virtualserver_id=1 virtualserver_unique_identifier=zrPkjznB1tMnRwj01xx7RxXjqeY= client_channel_id=2 ...</code>
	 */
	public TeamspeakResponse whoami() {
		return send(command("whoami"));
	}
}
