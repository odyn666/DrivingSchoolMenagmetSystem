package com.github.odyn666.appSchool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonRequestDto {
    private Long trainerId;
    private Long studentId;
    private String date;
    private String startingHour;
    private String endingHour;
}
