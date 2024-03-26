package com.github.odyn666.appSchool.exception.exceptions;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
        super("Lesson not found!");
    }
}
