package com.github.odyn666.appstudnet.exception.exceptions;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not found!");
    }
}
