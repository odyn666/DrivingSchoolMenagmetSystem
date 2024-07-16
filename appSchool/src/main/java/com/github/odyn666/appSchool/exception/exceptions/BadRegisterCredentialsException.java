package com.github.odyn666.appSchool.exception.exceptions;

public class BadRegisterCredentialsException extends RuntimeException {
    public BadRegisterCredentialsException() {
        super("wystąpił błąd poczas rejestracji. Upewnij się że wszystkie pola są uzupełnione");
    }

    public BadRegisterCredentialsException(String cause){
        super(cause);
    }
}
