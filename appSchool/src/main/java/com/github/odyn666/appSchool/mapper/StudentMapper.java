package com.github.odyn666.appSchool.mapper;

import com.github.odyn666.appSchool.dto.StudentEntityDto;
import com.github.odyn666.appSchool.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentEntityDto toDto(StudentEntity student) {
        StudentEntityDto dto = new StudentEntityDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getHoursLeft(),
                student.getHoursDriven()
        );
        return dto;
    }
}
