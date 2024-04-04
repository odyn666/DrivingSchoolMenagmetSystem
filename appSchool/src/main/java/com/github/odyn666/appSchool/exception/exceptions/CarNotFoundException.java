package com.github.odyn666.appSchool.exception.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super("Car not found!");
    }
}
