package com.google.code.ts3query.model.entity;

import com.google.code.ts3query.model.Entity;

public class Version extends Entity {

	private String platform;
	private String build;
	private String version;

	public String getPlatform() {
		return platform;
	}

	public String getBuild() {
		return build;
	}

	public String getVersion() {
		return version;
	}
}
