package com.google.code.ts3query.model.entity;

import java.util.List;

import org.apache.commons.lang.time.DurationFormatUtils;

import com.google.code.ts3query.model.ManagedEntity;
import com.google.code.ts3query.model.manager.Complains;
import com.google.code.ts3query.model.manager.VirtualServers;
import com.google.code.ts3query.option.TargetMode;

public class VirtualServer extends ManagedEntity<VirtualServers> {

	private int virtualserver_id;
	private String virtualserver_name;
	private long virtualserver_uptime;
	private String virtualserver_status;
	private boolean virtualserver_autostart;
	private int virtualserver_clientsonline;
	private int virtualserver_queryclientsonline;
	private int virtualserver_maxclients;
	private String virtualserver_machine_id;
	private int virtualserver_port;

	public String getName() {
		return virtualserver_name;
	}

	public int getId() {
		return virtualserver_id;
	}

	public int getClientsOnline() {
		return virtualserver_clientsonline;
	}

	public String getUptime() {
		String uptime = DurationFormatUtils.formatDuration(virtualserver_uptime, "d'd' H'h' m'm' ss's'");
		while (uptime.startsWith("0")) {
			uptime = uptime.substring(3);
		}
		return uptime;
	}

	public String getStatus() {
		return virtualserver_status;
	}

	public boolean doAutostart() {
		return virtualserver_autostart;
	}

	public int getMaxClients() {
		return virtualserver_maxclients;
	}

	public String getMachineId() {
		return virtualserver_machine_id;
	}

	public int getQueryClientsOnline() {
		return virtualserver_queryclientsonline;
	}

	public int getPort() {
		return virtualserver_port;
	}

	public void start() {
		send(command("serverstart").with("sid", virtualserver_id));
	}

	public void stop() {
		send(command("serverstop").with("sid", virtualserver_id));
	}

	public void delete() {
		send(command("serverdelete").with("sid", virtualserver_id));
	}

	/**
	 * Displays a list of server groups available. Depending on your
	 * permissions, the output may also contain global ServerQuery groups and
	 * template groups.
	 */
	public List<ServerGroup> getServerGroups() {
		return send(command("servergrouplist")).asList(ServerGroup.class);
	}

	/**
	 * Displays a list of clients online on a virtual server including their ID,
	 * nickname, status flags, etc. The output can be modified using several
	 * command options. Please note that the output will only contain clients
	 * which are currently in channels you're able to subscribe to.
	 */
	public List<Client> getClients() {
		// TODO add options
		return send(command("clientlist")).asList(Client.class);
	}

	/**
	 * Displays a list of channels created on a virtual server including their
	 * ID, order, name, etc. The output can be modified using several command
	 * options.
	 */
	public List<Channel> getChannels() {
		// TODO add options [-topic] [-flags] [-voice] [-limits]
		return send(command("channellist").option("topic", "voice", "flags")).asList(Channel.class);
	}
	
	public Complains getComplains() {
		return serve(new Complains());
	}
	
	/**
	 * Send a message to this server.
	 */
	public void sendMessage(String message) {
		server.sendTextMessage(TargetMode.server, getId(), message);
	}
}
