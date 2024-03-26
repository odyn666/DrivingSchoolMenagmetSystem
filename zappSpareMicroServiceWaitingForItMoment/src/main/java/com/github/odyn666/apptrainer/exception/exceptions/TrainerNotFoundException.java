package com.github.odyn666.apptrainer.exception.exceptions;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not found!");
    }
}
