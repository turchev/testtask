package com.haulmont.testtask.view;

@SuppressWarnings("serial")
public class UiException extends Exception {

	public UiException(Throwable throwable) {
		super(throwable);
	}

	public UiException(String string) {
		super(string);
	}
}
