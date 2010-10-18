package com.google.code.ts3query.model.manager;

import java.util.List;
import java.util.Map;

import com.google.code.ts3query.TeamspeakCommand;
import com.google.code.ts3query.TeamspeakResponse;
import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.model.entity.ServerGroup;
import com.google.code.ts3query.option.Event;

public class VirtualServers extends Entity {

	/**
	 * Creates a new virtual server using the given properties and displays its
	 * ID and initial administrator token. If virtualserver_port is not
	 * specified, the server will test for the first unused UDP port.
	 * 
	 * @param name
	 * @param parameters
	 * @return
	 *         <code>sid=5 token=sbJ0PhLvQfbyEREresjYgCidWN52XKPYARSf7n60></code>
	 */
	public TeamspeakResponse create(final String name, final Map<String, ? extends Object> parameters) {
		return send(command("servercreate").with("virtualserver_name", name).with(parameters));
	}

	/**
	 * Changes the selected virtual servers configuration using given
	 * properties.
	 * 
	 * @param parameters
	 */
	public void edit(final Map<String, ? extends Object> parameters) {
		send(command("serveredit").with(parameters));
	}

	/**
	 * Displays a list of server groups available. Depending on your
	 * permissions, the output may also contain global ServerQuery groups and
	 * template groups.
	 * 
	 * @return
	 *         <code>sgid=9 name=Server\sAdmin type=1 iconid=300 savedb=1|sgid=10 name=Normal type=1 iconid=0 savedb=1|sgid=11 ...</code>
	 */
	public List<ServerGroup> getServerGroups() {
		return send(command("servergrouplist")).asList(ServerGroup.class);
	}

	/**
	 * Displays the database ID of the virtual server running on the UDP port
	 * specified by virtualserver_port.
	 * 
	 * @param port
	 * @return the server id
	 */
	public int getServerIdByPort(final int port) {
		final TeamspeakCommand cmd = command("serveridgetbyport").with("virtualserver_port", port);
		return Integer.parseInt(send(cmd).getFirstResponse().get("server_id"));
	}

	/**
	 * Displays detailed configuration information about the selected virtual
	 * server including unique ID, number of clients online, configuration, etc.
	 * 
	 * @return
	 *         <code>virtualserver_port=9987 virtualserver_unique_identifier=zrPkjznB1tMnRwj01xx7RxXjqeY= virtualserver_name=TeamSpeak\s]I[\sServer ...</code>
	 */
	public TeamspeakResponse getInfo() { // TODO move to VirtualServer
		return send(command("serverinfo"));
	}

	/**
	 * Displays a list of virtual servers including their ID, status, number of
	 * clients online, etc. If you're using the -all option, the server will
	 * list all virtual servers stored in the database. This can be useful when
	 * multiple server instances with different machine IDs are using the same
	 * database.
	 * 
	 * @param options
	 * @return
	 *         <code>virtualserver_id=1 virtualserver_port=9987 virtualserver_status=online virtualserver_clientsonline=6 ...</code>
	 */
	// public List<VirtualServer> serverlist(final ServerListOption... options)
	// {
	// return send(command("serverlist").option((Object[])
	// options)).asList(VirtualServer.class);
	// } TODO
	
	public TeamspeakResponse getList() {
		return send(command("serverlist"));
	}

	/**
	 * Registers for a specified category of events on a virtual server to
	 * receive notification messages. Depending on the notifications you've
	 * registered for, the server will send you a message on every event in the
	 * view of your ServerQuery client (e.g. clients joining your channel,
	 * incoming text messages, server configuration changes, etc). The event
	 * source is declared by the event parameter while id can be used to limit
	 * the notifications to a specific channel.
	 * 
	 * @param event
	 * @param channelID
	 *            an optional channel ID, <code>null</code> to skip
	 */
	public void register(final Event event, final Integer channelID) {
		send(command("servernotifyregister").with("event", event).with("id", channelID));
	}

	/**
	 * Unregisters all events previously registered with servernotifyregister so
	 * you will no longer receive notification messages.
	 */
	public void unregister() {
		send(command("servernotifyunregister"));
	}

	/**
	 * Stops the entire TeamSpeak 3 Server instance by shutting down the
	 * process.
	 * 
	 * 
	 */
	public void stopProcess() {
		send(command("serverprocessstop"));
	}

	/**
	 * Displays detailed connection information about the selected virtual
	 * server including uptime, traffic information, etc.
	 * 
	 * @return
	 *         <code>connection_filetransfer_bandwidth_sent=0 connection_filetransfer_bandwidth_received=0 connection_packets_sent_total=241454 ...</code>
	 */
	public TeamspeakResponse getConnectionInfo() {
		return send(command("serverrequestconnectioninfo"));
	}

}
