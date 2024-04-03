package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.TrainerOpinions;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link TrainerEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public final class TrainerEntityDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private final @NotEmpty String firstName;
    private final @NotEmpty String lastName;
    private final String identifier;
    private final String phoneNumber;
    private final String email;
    private final Float studentsPassRate;
    private final List<TrainerOpinions> trainerOpinions;


    @Override
    public String toString() {
        return "TrainerEntityDto[" +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "identifier=" + identifier + ", " +
                "phoneNumber=" + phoneNumber + ", " +
                "email=" + email + ", " +
                "studentsPassRate=" + studentsPassRate + ", " +
                "trainerOpinions=" + trainerOpinions + ']';
    }

}