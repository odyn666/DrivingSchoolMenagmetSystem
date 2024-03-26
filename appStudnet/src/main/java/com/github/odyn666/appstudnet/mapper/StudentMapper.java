package com.github.odyn666.appstudnet.mapper;

import com.github.odyn666.appstudnet.dto.StudentEntityDto;
import com.github.odyn666.appstudnet.entity.StudentEntity;
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
