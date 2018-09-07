package com.haulmont.testtask.data.dao.exeption;

@SuppressWarnings("serial")
public class DaoException extends Exception{

	public DaoException(Throwable throwable) {
        super(throwable);
    }
}
