package com.github.odyn666.appSchool.exception.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student not found!");
    }
}
