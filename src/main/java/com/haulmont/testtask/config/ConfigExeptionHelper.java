package com.haulmont.testtask.config;

public final class ConfigExeptionHelper {

	@SuppressWarnings("serial")
	public static class LogLoadExeption extends Exception {
		public LogLoadExeption(Throwable throwable) {
			super(throwable);
		}
	}

	@SuppressWarnings("serial")
	public static class DBLoadExeption extends Exception{
		public DBLoadExeption(Throwable throwable) {
			super(throwable);				
		}	
	}
}
