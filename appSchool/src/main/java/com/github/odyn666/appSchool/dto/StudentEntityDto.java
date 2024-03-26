package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.StudentEntity;

import java.io.Serializable;

/**
 * DTO for {@link StudentEntity}
 */
public record StudentEntityDto(String firstName, String lastName, String email, String phoneNumber, Integer hoursLeft,
                               Integer hoursDriven) implements Serializable {
}