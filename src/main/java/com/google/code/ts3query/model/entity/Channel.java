package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.Entity;
import com.google.code.ts3query.option.TargetMode;

public class Channel extends Entity {

	private int channel_codec;
	private int channel_codec_quality;
	private boolean channel_flag_default;
	private boolean channel_flag_password;
	private boolean channel_flag_permanent;
	private boolean channel_flag_semi_permanent;
	private String channel_name;
	private int channel_needed_subscribe_power;
	private int channel_needed_talk_power;
	private int channel_order;
	private String channel_topic;
	private int cid;
	private int pid;
	private int total_clients;

	public int getCodec() {
		return channel_codec;
	}

	public int getCodecQuality() {
		return channel_codec_quality;
	}

	public boolean isDefault() {
		return channel_flag_default;
	}

	public boolean isPassword() {
		return channel_flag_password;
	}

	public boolean isPermanent() {
		return channel_flag_permanent;
	}

	public boolean isSemiPermanent() {
		return channel_flag_semi_permanent;
	}

	public String getName() {
		return channel_name;
	}

	public int getNeededSubscribePower() {
		return channel_needed_subscribe_power;
	}

	public int getNeededTalkPower() {
		return channel_needed_talk_power;
	}

	public int getOrder() {
		return channel_order;
	}

	public String getTopic() {
		return channel_topic;
	}

	public int getId() {
		return cid;
	}

	public int getPid() {
		return pid;
	}

	public int getTotalClients() {
		return total_clients;
	}

	/**
	 * Send a message to this channel.
	 */
	public void sendMessage(final String message) {
		server.sendTextMessage(TargetMode.channel, getId(), message);
	}
}
