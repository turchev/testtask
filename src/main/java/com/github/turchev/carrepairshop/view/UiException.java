package com.github.turchev.carrepairshop.view;

public class UiException extends Exception {

    public UiException(Throwable throwable) {
        super(throwable);
    }

    public UiException(String string) {
        super(string);
    }
}
