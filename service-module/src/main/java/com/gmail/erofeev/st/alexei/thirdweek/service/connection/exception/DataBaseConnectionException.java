package com.gmail.erofeev.st.alexei.thirdweek.service.connection.exception;

public class DataBaseConnectionException extends RuntimeException {

    public DataBaseConnectionException(String message) {
        super(message);
    }

    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
