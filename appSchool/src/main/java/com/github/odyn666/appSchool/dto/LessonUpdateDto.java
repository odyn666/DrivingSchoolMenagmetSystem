package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.enums.LessonStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LessonUpdateDto {
    private Long id;
    private Long trainerId;
    private Long studentId;
    private String date;
    private String startingHour;
    private String endingHour;
    private LessonStatus status;
}
