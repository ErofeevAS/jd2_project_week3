package com.gmail.erofeev.st.alexei.thirdweek.service.exception;

public class ItemNotFoundException extends ServiceException {
    public ItemNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
