package com.github.odyn666.apptrainer.dto;

import com.github.odyn666.apptrainer.entity.StudentEntity;

import java.io.Serializable;

/**
 * DTO for {@link StudentEntity}
 */
public record StudentEntityDto(String firstName, String lastName, String email, String phoneNumber, Integer hoursLeft,
                               Integer hoursDriven) implements Serializable {
}