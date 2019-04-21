package com.gmail.erofeev.st.alexei.thirdweek.service.exception;

public class WrongNumberOfReturnValuesException extends ServiceException {

    public WrongNumberOfReturnValuesException(String message, Exception e) {
        super(message, e);
    }

    public WrongNumberOfReturnValuesException() {
        super();
    }
}
