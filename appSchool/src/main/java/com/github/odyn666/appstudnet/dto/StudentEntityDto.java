package com.github.odyn666.appstudnet.dto;

import com.github.odyn666.appstudnet.entity.StudentEntity;

import java.io.Serializable;

/**
 * DTO for {@link StudentEntity}
 */
public record StudentEntityDto(String firstName, String lastName, String email, String phoneNumber, Integer hoursLeft,
                               Integer hoursDriven) implements Serializable {
}