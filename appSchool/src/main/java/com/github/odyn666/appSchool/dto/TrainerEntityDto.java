package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.TrainerOpinions;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link TrainerEntity}
 */
public record TrainerEntityDto(@NotEmpty String firstName, @NotEmpty String lastName, String identifier,
                               String phoneNumber, String email, Integer studentsPassRate,
                               List<TrainerOpinions> trainerOpinions) implements Serializable {
}