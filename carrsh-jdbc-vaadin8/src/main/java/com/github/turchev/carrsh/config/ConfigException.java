package com.github.turchev.carrsh.config;

@SuppressWarnings("serial")
public class ConfigException extends Exception {

	public ConfigException(Throwable throwable) {
		super(throwable);
	}

	public ConfigException(String string) {
		super(string);
	}
}
