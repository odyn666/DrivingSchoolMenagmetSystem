package com.github.odyn666.appSchool.exception.exceptions;

public class StudentWasBlocked extends RuntimeException{
    public StudentWasBlocked() {
        super("STUDENT WAS ALREADY BLOCKED");
    }

    public StudentWasBlocked(String message) {
        super(message);
    }
}
