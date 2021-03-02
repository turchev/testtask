package com.github.turchev.carrepairshop.config;

public class ConfigException extends Exception {

	public ConfigException(Throwable throwable) {
		super(throwable);
	}

	public ConfigException(String string) {
		super(string);
	}
}
