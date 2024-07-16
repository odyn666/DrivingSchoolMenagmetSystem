package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.entity.TrainerOpinions;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link TrainerEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public final class TrainerEntityDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String identifier;
    private String phoneNumber;
    private String email;
    private Float studentsPassRate;
    private String password;
    private List<TrainerOpinions> trainerOpinions;


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