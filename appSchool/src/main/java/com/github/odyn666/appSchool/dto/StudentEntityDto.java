package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.StudentEntity;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link StudentEntity}
 */

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public final class StudentEntityDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final Integer hoursLeft;
    private final Integer hoursDriven;

    @Override
    public String toString() {
        return "StudentEntityDto[" +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "email=" + email + ", " +
                "phoneNumber=" + phoneNumber + ", " +
                "hoursLeft=" + hoursLeft + ", " +
                "hoursDriven=" + hoursDriven + ']';
    }

}