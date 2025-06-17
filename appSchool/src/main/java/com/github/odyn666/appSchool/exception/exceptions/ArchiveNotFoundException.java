package com.github.odyn666.appSchool.exception.exceptions;

public class ArchiveNotFoundException extends RuntimeException{
    public ArchiveNotFoundException() {
        super("NO ARCHIVE WAS FOUND");
    }

    public ArchiveNotFoundException(String message) {
        super(message);
    }
}
