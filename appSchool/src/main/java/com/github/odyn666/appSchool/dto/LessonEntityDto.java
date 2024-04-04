package com.github.odyn666.appSchool.dto;

import com.github.odyn666.appSchool.entity.enums.LessonStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LessonEntityDto {
    private Long id;
    private TrainerEntityDto trainer;
    private StudentEntityDto student;
    private Date date;
    private String startingHour;
    private String endingHour;
    private LessonStatus status;

}
